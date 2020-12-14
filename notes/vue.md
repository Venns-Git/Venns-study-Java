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
- v-bind 可以简写为 `:`

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

采用v-on,可以简写为`@`

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

v-model

```html
<body>
    <div id="app">
        输入的文本：<input type="text" v-model="message">{{message}}
        <br />
        性别:
        <input type="radio" name="sex" value="男" v-model="sex">男
        <input type="radio" name="sex" value="女" v-model="sex">女
        <p>
            您的性别是: {{sex}}
        </p>
        <select v-model="selectVal">
            <option value="" disabled>--请选择--</option>
            <option>a</option>
            <option>b</option>
            <option>c</option>
        </select>
        <span>value: {{selectVal}}</span>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        var vm = new Vue({
            el: "#app",
            data: {
                message: '',
                sex: '',
                selectVal: ''
            }
        });
    </script>
</body>
```

# Vue组件

组件是可复用的Vue实例，就是一组可以重复使用的模板

demo：

```html
<body>
    <div id="app">
        <!--组件：传递给组件中的值-->
        <venns v-for="item in items" v-bind:couname="item"></venns>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        //定义一个vue组件component
        Vue.component("venns",{
            props: ['couname'],
            template: '<li>{{couname}}</li>'
        });
        var vm = new Vue({
            el: "#app",
            data: {
                items: ["java","vue","spring"]
            }
        });
    </script>
</body>
```

- Vue.component：定义一个组件，第一个参数为组件名，第二个为组件对象
- props：自定义参数，**不能有大写**
- template：组件模板

# Axios异步通信

Axios是一个可以用在浏览器端和NodeJS的异步通信框架，主要作用就是实现AJAX异步通信

data.json

```json
{
    "name": "venns",
    "url": "http://www.venns.cn/",
    "page": "1",
    "isNonProfit":"true",
    "address": {
      "city": "四川成都",
      "country": "中国"
    },
    "links": [
      {
        "name": "B站",
        "url": "https://www.bilibili.com/"
      },
      {
        "name": "venns",
        "url": "http://www.venns.cn/"
      },
      {
        "name": "百度",
        "url": "https://www.baidu.com/"
      }
    ]
  }
```

demo

```html
<body>
    
    <div id="vue" v-clock>
        <div>{{info.address.city}}</div>
        <a  v-bind:href="info.url">点击</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>
        var vm = new Vue({
            el: "#vue",
            
            //data:属性，data():函数
            data () {
                return {
                    //请求的返回参数格式，必须和json字符串一样
                    info: {
                        name: null,
                        url: null,
                        address: {
                            country: null,
                            city: null
                        }
                    }
                }
            },
            mounted () {
                //钩子函数 ES6新特性
                axios.get('../data.json').then(response=>(this.info = response.data));
            }
        })
    </script>
</body>
```

# Vue计算属性

计算出来的结果，保存在属性中，类似于缓存

```html
<body>
    <div id="app">
       <p>currentTime1: {{currentTime1()}}</p>
       <p>currentTime2: {{currentTime2}}</p>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        var vm = new Vue({
            el: "#app",
            data: {
                message: "hello",
            },
            methods: {
                currentTime1: function(){
                    return Date.now(); //返回时间戳
                }
            },
            computed: {
                //注意: methods 和 computed 中的方法名不能重名,重名只会调用methods中的方法
                currentTime2: function(){
                    return Date.now();
                }
            }
        });
    </script>
</body>
```

- mothods： 定义方法，调用方法使用currentTime1(),需要带括号，调用一次执行一次
- computed：定义计算属性，调用属性使用currentTime2，不带括号，只有计算属性发生变化时，才会重新执行

**计算属性的主要特性就是为了将不经常变化的计算结果进行缓存，以节约我们的系统开销**

# 插槽slot

```html
<body>
    <div id="app">
        <todo>
            <todo-title slot="todo-title" :title="title"></todo-title>
            <todo-item slot="todo-item" v-for="item in todoItems" :item="item"></todo-item>
        </todo>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        Vue.component("todo",{
            template: 
            '<div>\
                <slot name="todo-title"></slot>\
                <ul>\
                    <slot name="todo-item"></slot>\
                </ul>\
            </div>'
        })
        Vue.component("todo-title",{
            props: ['title'],
            template: '<div>{{title}}</div>'
        })
        Vue.component("todo-item",{
            props: ['item'],
            template: '<li>{{item}}</li>'
        })
        var vm = new Vue({
            el: "#app",
            data: {
                title: "书籍列表",
                todoItems: ['java','vue','linux']
            }
        });
    </script>
</body>
```

# 自定义事件分发

```this.$emit:自定义事件分发```

```html
<body>
    <div id="app">
        <todo>
            <todo-title slot="todo-title" :title="title"></todo-title>
            <todo-item slot="todo-item" v-for="(item,index) in todoItems" 
                    :item="item" :index="index" @bbb="removeItems(index)"></todo-item>
        </todo>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <script>
        Vue.component("todo",{
            template: 
            '<div>\
                <slot name="todo-title"></slot>\
                <ul>\
                    <slot name="todo-item"></slot>\
                </ul>\
            </div>'
        })
        Vue.component("todo-title",{
            props: ['title'],
            template: '<div>{{title}}</div>'
        })
        Vue.component("todo-item",{
            props: ['item','index'],
            template: '<li>{{item}}<button @click="aaa">delete</button></li>',
            methods: {
                aaa: function(index){
                    //this.$emit:自定义事件分发
                    this.$emit('bbb',index);
                }
            }
        })
        var vm = new Vue({
            el: "#app",
            data: {
                title: "书籍列表",
                todoItems: ['java','vue','linux']
            },
            methods: {
                removeItems: function(index){
                    this.todoItems.splice(index,1);//一次删除一个元素
                }
            }
        });
    </script>
</body>
```

# Vue-cli

vue官方提供的脚手架，用于快速生成一个vue的项目模板

**主要功能**

- 统一的目录结构
- 本地调试
- 热部署
- 单元测试
- 集成打包上线

**需要的环境**

- Node.js
- Git

