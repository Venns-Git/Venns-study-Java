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

# Webpack

- 创建项目

- 创建一个modules文件夹

- modoules创建一个hello.js

	```js
	//暴露一个方法
	exports.sayHi = function(){
	    document.write("<h1>venns,hello world</h1>");
	};
	```

- 再创建一个main.js引入hello

	```javascript
	var hello = require("./hello");
	hello.sayHi();
	```

- 在项目目录下创建webpack.config.js

	```javascript
	module.exports = {
	    entry: './modules/main.js',
	    output: {
	        filename: "./js/bundle.js",
	    }
	};
	```

	- entry:主入口
	- output -> filename :打包后的文件地址

- 在项目目录路径运行webpack，生成dist/js/bundle.js

- 创建index.html,引入dist/js/bundle.js即可

	```html
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Document</title>
	    <script src="dist/js/bundle.js"></script>
	</head>
	```

# Vue-router路由

配置路由 ./router/index.vue

```vue
import Vue from 'vue'
import VueRouter from 'vue-router'
import content from '../components/content'
import main from '../components/main'

//安装路由
Vue.use(VueRouter);

//配置导入路由
export default new VueRouter({
    routes: [
        {
            //路由路径
            path: '/content',
            name: 'content',
            //跳转的组件
            component: content
        },
        {
            path: '/main',
            name: 'main',
            component: main
        }
    ]
});
```

在main.js里面配置路由

```vue
import Vue from 'vue'
import App from './App'
import router from './router' //自动扫描里面的路由配置

Vue.config.productionTip = false;

new Vue({
  el: '#app',
  //配置路由
  router,
  components: { App },
  template: '<App/>'
})
```

在App.vue中安装路由

```html
<template>
  <div id="app">
      <h1>Vue-Router</h1>
      <router-link to="main">首页</router-link>
      <router-link to="content">内容页</router-link>
      <h1>下面为路由跳转页面</h1>
      <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
```

测试运行

# Vue+elementUI

1. 创建一个名为hello-vue的工程：```vue init webpack hello-vue```
2. 进入目录工程：```cd hello-vue```
3. 安装vue-router：```npm install vue-router --save-dev```
4. 安装element-ui：```nmp i element-ui -S```
5. 安装依赖：```npm install```
6. 安装SASS加载器：```cnpm install sass-loader node-sass --save-dev```
7. 启动测试：```npm run dev```

## npm命令解释

- ```npm install moduleName```：安装模块到项目目录下
- ```npm install -g moduleName```：-g 的意思将模块安装到全局，具体安装到磁盘哪个位置，要看npm config prefix 的位置
- ```npm install -save moduleName```：--save 的意思是将模块安装到项目目录下，并在package 文件 dependencies 节点写入依赖，-S为该命令的缩写
- ```npm install -save-dev moduleName```：--save-dev 的意思将模块安装到项目目录下，并在package 文件的devDependencies 节点写入依赖，-D为改命令的缩写

## 步骤

创建views视图文件夹，并在里面创建main首页和login登录页

main.vue

```vue
<template>
    <h1>首页</h1>
</template>

<script>
export default {
    name: "Main"
}
</script>

<style scoped>

</style>
```

login.vue

```vue
<template>
    <div>
        <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
            <h3 class="login-title">欢迎登录</h3>
            <el-form-item label="账号" prop="username">
                <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" v-on:click="onSubmit('loginForm')">登录</el-button>
            </el-form-item>
        </el-form>
        <el-dialog
        title="温馨提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
            <span>请输入账号和密码</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    export  default {
        name:"Login",
        data(){
        return {
            form:{
            username: '',
            password: ''
            },
            //表单验证，需要再el-form-item 元素中增加prop属性
            rules:{
            username:[
                {required:true,message:'账号不能为空',trigger:'blur'}
            ],
            password:[
                {required: true,message: '密码不能为空',trigger:'blur'}
            ]
            },
            //对话框显示和隐藏
            dialogVisible:false
        }
        },
        methods:{
        onSubmit(formName) {
            //为表单绑定验证功能
            this.$refs[formName].validate((valid) =>{
            if (valid){
                //使用 vue-router路由到指定页面，该方式称之为编程式导航
                this.$router.push("/main");
            } else {
                this.dialogVisible = true;
                return false;
            }
            });
        }
    }
  }
</script>
<style lang="scss" scoped>
  .login-box{
    border: 1px solid #DCDFE6;
    width: 350px;
    margin:180px auto;
    padding:35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow:0 0 25px #909399;
  }

  .login-title{
    text-align:center;
    margin:0 auto 40px auto;
    color:#303133;
  }
</style>
```

