# VUE

是一套用于构建用户界面的渐进式框架

## MVVM模式的实现者

 **MVVM**

软件架构设计模式，事件驱动编程方式

- Model：模型层【js对象】
- View：视图层【DOM】
- ViewModel：连接视图和数据的中间件
	- 能够观察到数据的变化，并对视图对应的内容进行更新
	- 能够监听视图的变化，并能通知数据发生改变

# 第一个Vue程序

1. 导入Vue.js

	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>

2. ```html
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Document</title>
	</head>
	<body>
	    <!-- view层 模板-->
	    <div id="app">
	        {{message}}
	    </div>
	
	    <!-- 导入Vue.js -->
	    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
	    <script>
	        var vm = new Vue({
	            el: "#app",
	            //Model: 数据
	            data: {
	                message: "hello,Vue!"
	            }
	        });
	    </script>
	</body>
	</html>
	```

# Vue基本语法

## v-bind

```html
<body>
    <!-- view层 模板-->
    <div id="app">
        <span v-bind:title="message">
            鼠标悬停查看提示信息
        </span>
    </div>

    <!-- 导入Vue.js -->
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        var vm = new Vue({
            el: "#app",
            //Model: 数据
            data: {
                message: "hello,Vue!"
            }
        });
    </script>
</body>
```

- v-bind等级称为指令，表示将这个元素节点的title特性和vue实例的message属性保持一致
- 带有 v- 的前缀表示是vue提供的特殊特性，会在渲染的DOM上应用特殊的响应式行为

## v-if,v-else

条件判断语句

```html
<body>
    <div id="app">
        <h1 v-if="type === 'A'">A</h1>
        <h1 v-else-if="type === 'B'">B</h1>
        <h1 v-else>C</h1>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        var vm = new Vue({
            el: "#app",
            data: {
                type: 'A'
            }
        });
    </script>
</body>
```

## v-for

循环语句

```html
<body>
    <div id="app">
        <li v-for="item in items">
            {{item.message}}
        </li>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        var vm = new Vue({
            el: "#app",
            data: {
                items: [
                    {message: "venns1"},
                    {message: "venns2"},
                    {message: "venns3"}
                ]
            }
        });
    </script>
</body>
```

- 遍历时还可以加上下标参数(item,index) in items,下标从0开始

# Vue绑定事件

采用v-on

```html
<body>
    <div id="app">
        <button v-on:click="sayHi">点击触发事件</button>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        var vm = new Vue({
            el: "#app",
            data: {
                message: "venns"
            },
            methods: {
                //方法必须定义在vue的methods对象中
                sayHi: function(){
                    alert(this.message);
                }
            }
        });
    </script>
</body>
```

# Vue双向绑定

即数据发生变化的时候，视图也就发生变化，当视图发生变化的时候，数据也会跟着同步变化

