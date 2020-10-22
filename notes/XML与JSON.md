# XML简介
> XML是一种数据表示结构，常用于传输和存储数据

书籍示例：
```xml
<?xml version="1.0" encoding="UTF-8">
<!DOCTYPE note SYSTEMN "bool.dtd">
<book id="1">
    <name>平凡的世界</name>
    <autor>路遥</autor>
    <isbn lang="CN">12345</isbn>
    <tags>
        <tag>文学</tag>
        <tag>小说</tag>
    </tags>
</book>
```
特点:
- 1.纯文本，默认使用UTF-8编码
- 2.可嵌套
## XML的结构
- 首行必定是`<?xml version="1.0">`,加上可选的编码
- 可选`<!DOCTYPE note SYSTEM "book.dtd">`声明文档定义类型(DTD),可选,
- 接下来为XML的文档内容,一个XML文档有且仅有一个根元素
- 根元素可以包含多个子元素,子元素可以包含属性,如果为空元素可以用`<tag/>`表示
### 转义字符
由于XML使用了`<`,`>`等标识符，如果内容需要特殊符号，则需要转义,常见转义字符如下:    
|字符|表示
|:---:|:---:|
|<|`&lt;`|
|>|`&gt;`|
|&|`&amp;`|
|"|`&quot;`|
|'|`&apos;`|
### 补充
- 格式正确：指XML格式是正确的，可以被解析器正常读取
- 合法:指不但格式正确，并且数据结构可以被DTD或者XSD验证    

- DTD文档可以指定一系列的规则：  
    - 根元素必须是`book`
    - `book`必须包含`name`,`autor`等指定元素
    - ....    
- 验证XML的正确性：最简单的是通过浏览器(直接将XML拖到浏览器窗口)
- XML技术体系：
    - DTD和XSD：验证XML结构或数据是否有效
    - Namespace:XML节点和属性的名字空间
    - XSLT:把XML转化为另一种文本
    - XPath:一种XML节点查询语言
    - ....
# 使用DOM
>XML标准解析API:
>- DOM:一次性读取XML,并在内存中表示为树形结构
>- SAX:以流的形式读取XML,使用事件回调    

## DOM解析:
```xml
<?xml version="1.0" encoding="UTF-8">
<book id="1">
    <name>平凡的世界</name>
    <autor>路遥</autor>
    <isbn lang="CN">12345</isbn>
    <tags>
        <tag>文学</tag>
        <tag>小说</tag>
    </tags>
</book>
```
将上面XML解析为DOM结构：    
```
     document
         |
        book    
         |
   ______|_______
  |   |    |     |  
name autor isbn tags
                 |
               __|__
              |     |
             tag   tag 
```
- 顶部document代表XML文档，它是真正的根,book是document的一个子节点   

Java提供了DOM API来解析XML,如下对象表示XML的内容  
- Doucment:代表整个XML文档
- Element:代表一个XML元素
- Attribute:代表一个元素的某个属性    

使用DOM API解析XML示例:
```JAVA
InputStream input = Main.class.getResourceAsStream("/book.xml");
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db = dbf.newDocumentBuilder();
Document doc = db.parse(input);
```
- `DocumentBuilder.parse()`应用于解析一个XML,可以接收InputStream,File或者URL,返回值为一个Document对象
- 对于DOM API解析出来的文本,我们从根节点Document出发,可以遍历所有子节点,获取所有元素、属性、文本数据,还可以包括注释，这些节点被统称为Node,每个Node都有自己的Type，根据Type来区分一个Node到底是元素,还是属性,还是文本等等
- DOM解析速度慢,内存占用大
# 使用SAX
特点：边读取边解析,占用内存小   

SAX解析触发事件:   
- startDocument：开始读取XML文档
- startElement：读取到了一个元素，例如`<book>`
- characters：读取到了字符
- endElement：读取到了一个结束的元素，例如`</book>`
- endDocument：读取XML文档结束   

使用SAX APX解析XML示例:
```java
InputStream input = Main.class.getResourceAsStream("/book.xml");
SAXParserFactory spf = SAXParserFactory.newInstance();
SAXParser saxParser = spf.newSAXParser();
saxParser.parse(input, new MyHandler());
```
- `SAXParserFactory.parse()`除了需要传入一个`InputStream`外,还需要传入一个回调对象,这个对象继承`DefaultHandler`
```java
class MyHandler extends DefaultHandler {
    public void startDocument() throws SAXException {
        print("start document");
    }

    public void endDocument() throws SAXException {
        print("end document");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        print("start element:", localName, qName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        print("end element:", localName, qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        print("characters:", new String(ch, start, length));
    }

    public void error(SAXParseException e) throws SAXException {
        print("error:", e);
    }

    void print(Object... objs) {
        for (Object obj : objs) {
            System.out.print(obj);
            System.out.print(" ");
        }
        System.out.println();
    }
}
```
运行结果:
```
start document
start element:  book
characters:
     
start element:  name
characters: 平凡的世界
end element:  name
characters:
     
start element:  author
...
```
# 使用Jackson
无论DOM还是SAX，使用起来都不直观，XML完全可以对应到一个定义号的JavaBean中    


例如，book这个XML对应的JavaBean如下:
```java
public class Book{
    public long id;
    public String name;
    public String isbn;
    public List<String> tags;
    public String pubDate;
}
```
Jackson这个开源的第三方库可以轻松做到XML和JavaBean的转换，首先添加Maven依赖:   
- com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.10.1
- org.codehaus.woodstox:woodstox-core-asl:4.4.1   

然后定义好JavaBean,进行解析
```java
InputStream input = Main.class.getResourceAsStream("/book.xml");
JacksonXmlModule module = new JacksonXmlModule();
XmlMapper mapper = new XmlMapper(module);
Book book = mapper.readValue(input, Book.class);
System.out.println(book.id);
System.out.println(book.name);
System.out.println(book.author);
System.out.println(book.isbn);
System.out.println(book.tags);
System.out.println(book.pubDate);
```
- `XMLMapper`为我们需要创建的核心对象，可以用`readValue(InputStream,Class)`直接读取XML并且返回一个JavaBean   
- 如果要解析的数据格式不是Jackson内置的标准格式，需要自定义解析，可以参考[Jackson官方文档](https://github.com/FasterXML/jackson)
# 使用JSON
> 去除了所有的js代码，只保留js的对象格式的数据结构  

示例:
```json
{
    "id": 1,
    "name": "平凡的世界",
    "autor": {
        "firstName": "路",
        "lastName": "遥"
    },
    "isbn": "12345",
    "tags": ["文学","小说"]
}
```

优点：
- 只允许使用UTF-8编码
- 只使用双引号为key，特殊字符用`\`转义
- 浏览器内置JSON支持   

数据结构:
- 键值对: `{"key":value}`
- 数组: `[1,2,3]`
- 字符串: `"abc"`
- 数值: `12`,`3.14`
- 布尔值: `true`,`false`
- 空值: `null`  

常用的解析JSON的第三方库:
- Jackson
- Gson
- Fastjson
- ...  
## 使用Jackson解析JSON
引入Maven依赖
- com.fasterxml.jackson.core:jackson-databind:2.10.0  


代码示例
```java
InputStream input = Main.class.getResourceAsStream("/book.json");
ObjectMapper mapper = new ObjectMapper();
// 反序列化时忽略不存在的JavaBean属性:
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
Book book = mapper.readValue(input, Book.class);
```
- 核心代码是创建一个`ObjectMapper`对象,关闭`DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES`能使得解析时如果JavaBean不存在时不会报错  

JavaBean到JSON序列化：
```java
String json = mapper.wtiteValueAsString(book);
```
也可以将JSON某些值解析为Java对象,例如`LocalDate`:
```json
{
    "name": "平凡的世界",
    "pubDate": "2000-1-1"
}
```
解析为:
```java
public class Book {
    public String name;
    public LoaclDate pubDate;
}
```
只需引入关于Java Time的数据格式定义到即可:
-  com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0  

然后创建`ObjectMapper`时，注册一个新的`JavaTimeMoudule`
```java
ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
```
当内置解析规则不满足我们需求时，我们可以自定义解析   
例如，`Book`类的`isbn`为`BigInteger`,但JSON的数据并不是标准的整形格式,直接解析会报错，我们需要自定义一个`IsbnDeserializer`,用于解析含有非数字的字符串:
```java
public class IsbnDeserializer extends JsonDeserializer<BigInteger> {
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // 读取原始的JSON字符串内容:
        String s = p.getValueAsString();
        if (s != null) {
            try {
                return new BigInteger(s.replace("-", ""));
            } catch (NumberFormatException e) {
                throw new JsonParseException(p, s, e);
            }
        }
        return null;
    }
}
```
然后，在`Book`类中使用注解标注
```java
public class Book {
    public String name;
    // 表示反序列化isbn时使用自定义的IsbnDeserializer:
    @JsonDeserialize(using = IsbnDeserializer.class)
    public BigInteger isbn;
}
```
序列化也同样定义一个`IsbnSerializer`,然后在`Book`类中标注`@JsonSerialize(using = ...)`即可。