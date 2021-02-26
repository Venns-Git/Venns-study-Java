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

在node环境中，一个.js文件就称之为一个模块

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

