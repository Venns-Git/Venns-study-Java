# Node.js

>  Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行时

- V8引擎:Chrome浏览器运行JavaScript的引擎，负责把js文件解释给操作系统，简单的可以理解为Java中JVM的作用

## 第一个Node程序

1. 在文本编辑器中输入以下代码，保存为hello.js文件

	```javascript
	`use strict`;
	console.log('hello world');
	```
	
	`use strict`:使用严格模式运行JavaScript代码，避免潜在陷阱

2. 使用命令行运行程序

	```shell
	node hello.js
	```

3. 也可以输入node，进入node交互式环境，在node交互式环境下，直接写JavaScript代码即可，类似浏览器中的console

## 模块

在node环境中，一个.js文件就称之为一个模块,官方提供了非常多的内置模块

使用模块的好处：

- 提高了代码的可维护性
- 避免函数名和变量名冲突

在 hello.js 中创建一个函数 

```javascript
'use strict';
var s = 'Hello';
function greet(name){
    console.log(s + ',' + name + '!');
}
module.exports = greet;
```

- `module.export = greet;`  将greet作为模块的输出暴露出去，这样其他模块就可以使用`greet`

再编写module.js调用hello模块的greet函数

```javascript
'use strict';

// 引入hello模块:
var greet = require('./hello');

var s = 'Venns';

greet(s); // Hello, Venns!
```

- `require('./hello')`:引入hello模块的函数
- 如果只写模块名字，不写路径，node会一次在内置模块，全局模块和当前模块下查找hello.js

## 基本模块

> global

node.js中的唯一全局对象，地位类似于JavaScript中的window对象，但这个对象的属性和方法和window不同，进入node交互式环境，输入global.console

```shell
> global.console
Object [console] {
  log: [Function: log],
  warn: [Function: warn],
  dir: [Function: dir],
  time: [Function: time],
  timeEnd: [Function: timeEnd],
  timeLog: [Function: timeLog],
  trace: [Function: trace],
  assert: [Function: assert],
  clear: [Function: clear],
  count: [Function: count],
  countReset: [Function: countReset],
  group: [Function: group],
  groupEnd: [Function: groupEnd],
  table: [Function: table],
  debug: [Function: debug],
  info: [Function: info],
  dirxml: [Function: dirxml],
  error: [Function: error],
  groupCollapsed: [Function: groupCollapsed],
  Console: [Function: Console],
  profile: [Function: profile],
  profileEnd: [Function: profileEnd],
  timeStamp: [Function: timeStamp],
  context: [Function: context]
}
```

> process

这个对象代表当前node.js进程，通过process对象可以拿到许多信息，包括版本信息，当前目录，系统架构等等，node.js是单线程的，如果我们需要在下一次事件响应中执行代码，可以调用`process.nextTick()`:

```javascript
process.nextTick(function () {
    console.log('nextTick callback!');
});
console.log('nextTick was set!');
```

执行以上代码，会输出

```
nextTick was set!
nextTick callback!
```

node.js进程本事的事件就由```process`对象来处理，如果我们响应exit事件，就可以在程序即将退出的时候执行某个回调函数:

```javascript
process.on('exit', function (code) {
    console.log('about to exit with code: ' + code);
});
```

### fs

> fs模块是文件系统模块，负责读写文件，提供了异步和同步的方法，因为JavaScript的单线程模型，执行IO操作时，JavaScript代码无需等待，而是传入回调函数后，继续执行后续JavaScript代码，同步操作的好处是代码简单，缺点是程序将等待IO操作，在等待时间内，无法响应其它任何事件。而异步读取不用等待IO操作，但代码较麻烦

#### 使用fs模块异步读取文件：

```javascript
'use strict'
let fs = require('fs');
fs.readFile('demo.txt','utf-8',function(err,data){
    if(err){
        console.log(err);
    }else{
        console.log(data);
    }
});
```

tips：demo.txt 文件必须在当前目录下，并且编码为utf-8

- 异步读取文件时，回调函数接收两个参数：`err`和`data`
- 正常读取时，`err`为`null`,`data`参数为读取到的String
- 读取错误时，`err`为错误对象，`data`为undefined
- 读取二进制文件时，不传入文件编码，`data`参数将返回一个`Buffer`对象，是一个包含0个或任意个字节的数组

#### 使用fs模块同步读取文件：

````javascript
let fs2 = require('fs');
let data = fs2.readFileSync('demo.txt','utf-8');
console.log(data);
````

同步读取文件没有回调函数，而是直接返回`data`,如果发生错误，需要使用`try...catch`捕获

```javascript
try{
    let data = fs2.readFileSync('data.txt','utf-8');
    console.log(data);
}catch(err){
    //错误处理
}
```

#### 读文件

````javascript
'use strict';
let fs = require('fs');
let data = 'Hello Node.js';
fs.writeFile('demo.txt',data,function(err){
    if(err) console.log(err);
    else console.log('ok');
});
````

和`readFile()`类似，`writeFile()`也有一个同步方法，叫`writeFileSync()`

#### stat

可以用stat方法获取文件或目录的详细信息

```javascript
'use strict';
let fs = require('fs');
fs.stat('demo.txt',function(err,stat){
    if(err){
        console.log(err);
    }else{
        //是否是文件
        console.log('isFile: ' + stat.isFile());
        //是否是目录
        console.log('isDirectory: ' + stat.isDirectory());
        if(stat.isFile()){
            //文件大小
            console.log('size: ' + stat.size);
            //创建时间
            console.log('birth time: ' + stat.birthtime);
            //修改时间
            console.log('modified time: ' + stat.mtime);
        }
    }
})
```

### stream

> 是Node.js提供的又一个仅在服务区端可用的模块,目的是支持“流”这种数据结构。在Node.js中，流也是一个对象，我们只需要响应流的事件就可以了：`data`事件表示流的数据已经可以读取了，`end`事件表示这个流已经到末尾了，没有数据可以读取了，`error`事件表示出错了

示例：从文件流读取文本内容

````javascript
'use strict';

let fs = require('fs');

//打开一个流
let rs = fs.createReadStream('demo.txt','utf-8');

rs.on('data',function(chunk){
    console.log('DATA');
    console.log(chunk);
})
rs.on('end',function(){
    console.log('END');
});
rs.on('error',function(err){
    console.log('ERROR: ' + err);
})
````

注意：`data`事件可能会有多次，每次传递的`chunk`是流的一部分数据

使用流写入文件：

```javascript
'use strict';

var fs = require('fs');

var ws1 = fs.createWriteStream('output1.txt', 'utf-8');
ws1.write('使用Stream写入文本数据...\n');
ws1.write('END.');
ws1.end();

var ws2 = fs.createWriteStream('output2.txt');
ws2.write(new Buffer('使用Stream写入二进制数据...\n', 'utf-8'));
ws2.write(new Buffer('END.', 'utf-8'));
ws2.end();
```

## Node实现请求响应

- `request`对象封装了HTTP请求，我们调用`request`对象的属性和方法就可以拿到所有HTTP请求的信息
- `response`对象封装了HTTP响应，我们操作`response`对象的方法，就可以把HTTP响应返回给浏览器

对于所有响应，返回hello world示例：

```javascript
'use strict'

let http = require('http');

let server = http.createServer(function(request,response){
    //回调函数接收request 和 response 对象
    //获得http请求的method和url
    console.log(request.method + ": " + request.url);
    // 将HTTP响应200写入response, 同时设置Content-Type: text/html:
    response.writeHead(200, {'Content-Type': 'text/html'});
    // 将HTTP响应的HTML内容写入response:
    response.end('<h1>Hello world!</h1>');
});
// 让服务器监听8080端口:
server.listen(8080);

console.log('Server is running at http://127.0.0.1:8080/');
```

这时，打开浏览器输入localhost:8080,即可看到hello world

## Node操作数据库

这里选择采用mysql，因为node.js没有内置mysql模块，需要我们下载mysql依赖

1. 按照mysql依赖

	```javascript
	npm install mysql
	```

2. 操作数据库

	```javascript
	//1. 导入mysql依赖包
	let mysql = require("mysql");
	
	//2. 创建mysql连接
	let conn = mysql.createConnection({
	    //3. 配置数据库连接信息
	    host: '127.0.0.1',
	    port: 3306,
	    user: 'root',
	    password: '123456',
	    database: 'testdb'
	})
	
	//4. 开辟连接
	conn.connect();
	//5. 执行crud
	conn.query('select * from user',function(err,result,fields){
	    //如果查询出错，直接抛出
	    if(err)throw err;
	    else console.log(result);
	});
	
	//6. 关闭连接
	conn.end();
	```

	

