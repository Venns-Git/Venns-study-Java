# Cookie Session

## 会话

**会话**：用户打开一个浏览器，点击了很多超链接，访问多个web资源，关闭浏览器，这个过程可以叫做会话

**有状态会话**：一个同学来过教师，下次再来，我们会知道这个学生曾经来过，称之为有状态会话

**怎么证明你是本校的学生**（你 和 学校）

1. 发票           学校给你发票

2. 学校登记   学校标记你来过了

**一个网站，怎么证明你来过?**（客户端 和 服务端）

1. 服务端给客户端一个信件，客户端下载访问服务端带上信件就可以了：cookie
2. 服务器登记你来过了，下次你来的时候我来匹配你

## 保存会话的两种技术

### cookie

- 客户端技术（响应，请求）

### session

- 服务器技术，利用这个技术，我们可以保存用户的会话信息？-我们可以把信息或者数据放在Session中。

常见场景:网站登录后，下次不用再登录，第二次访问直接

## Cookie

1. 从请求中拿到cookie信息
2. 服务器响应给客户端

```java
Cookie[] cookies = req.getCookies(); //获得cookie
cookie.getName(); //获得cookie中的key
cookie.getValue(); //获得cookie中的value
new Cookie("lastLoginTime", System.currentTimeMillis() + ""); //新建一个cookie
cookie.setMaxAge(60*60); //设置cookie的有效期
resp.addCookie(cookie);//响应给客户端一个cookie
```

**cookie：一般会保存到本地目录下 appdata**

- 一个cookie只能保存一个信息；
- 一个web站点可以给浏览器发送多个cookie，最多存放20个cookie
- cookie大小有4kb限制
- 浏览器cookie上限大概300个

删除cook

- 不设置有效期，关闭浏览器，自动失效
- 设置有效期时间为0

编码解码：

```java
URLEncoder.encode("中文","utf-8");
URLDecoder.decode(cookie.getValue(),"utf-8");
```

## Session

什么是Session：

- 服务器会给每一个用户(浏览器)创建一个Session对象
- 一个Session独占一个浏览器，只要浏览器没有关闭，这个Session就存在
- 用户登录之后，整个网站它都可以访问 -> 保存用户的信息，保存购物车的信息

Session和cookie的区别

- Cookie是把用户的数据写给用户的浏览器，浏览器保存（可以保存多个）
- Session把用户的数据写到用户独占Session中，服务器端保存（保存重要的信息，减少服务器资源的浪费）
- Session对象由服务器创建

使用场景：

- 保存一个登录用户的信息
- 购物车信息
- 在整个网站中经常会使用的数据，我们将它保存在Session中

使用Session

```java
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到Session
        HttpSession session = req.getSession();
        session.setAttribute("name", new Person("venns",18));

        //获取Session的id
        String id = session.getId();

        //判断Session是不是新创建的
        if (session.isNew()) {
            resp.getWriter().write("Session创建成功 id:"+id);
        }else {
            resp.getWriter().write("Session已经存在,id"+id);
        }

        //Session创建的时候做了什么事情
//        Cookie jsessionid = new Cookie("JSESSIONID", id);
//        resp.addCookie(jsessionid);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
//得到Session
HttpSession session = req.getSession();
Person person = (Person) session.getAttribute("name");
System.out.println(person.toString());
//手动注销Session
HttpSession session = req.getSession();
session.removeAttribute("name");
session.invalidate();
```

会话自动过期：web.xml配置

```xml
<!-- 设置Session默认的失效时间   -->
<session-config>
    <!--  1分钟后失效 以分钟为单位 -->
    <session-timeout>1</session-timeout>
</session-config>
```

