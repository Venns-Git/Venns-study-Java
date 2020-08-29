# H5
## 新元素
- `<canvas>` 图形
- `<audio>` 音频
- `<video>` 视频(video 或者 movie)
- `<source>` 多媒体资源(video 或者 audio)
- `<embed>` 嵌入的内容，比如插件
- `<track>` 为媒介(video,audio...)规定外部文本轨道
- `<datalist>` 选项列表,与input配合使用
- `<keygen>` 规定用于表单的密钥对生成器字段
- `<output>` 定义不同类型的输出，比如脚本的输出
- `<article>` 页面独立的内容区域
- `<aside>` 侧边栏内容
- `<bdi>` 脱离父元素文本方向设置的文本
- `<command>` 命令按钮(单选按钮，复选框、按钮)
- `<details>` 描述文档或文档某个部分的细节
- `<dialog>` 对话框
- `<summary>` 包含details元素的标题
- `<figure>` 独立流内容
- `<figcaption>`  figure元素的标题
- `<footer>` section或document页脚
- `<header>` 文档头部按钮
- `<mark>` 带记号的文本
- `<meter>` 度量衡
- `<nav>` 导航链接部分
- `<progress>` 任何类型的任务和进度
- `<ruby>` 带ruby注释
- `<rt>` 中文注音或字符的解释或发音
- `<rp>` 在ruby中使用，不支持ruby浏览器显示的内容
- `<section>` 文档中的节(section,字段)
- `<time>` 日期或时间
- `<wbr>` 文本中适合添加换行符的位置
## Canvas
- 用于图形的绘制，通过脚本 (通常是JavaScript)来完成   

### 创建画布:
```html
<canvas id="myCanvas" width="200" height="100"></canvas>
```
使用 JavaScript 来绘制图像
- canvas 元素本身是没有绘图能力的。所有的绘制工作必须在 JavaScript 内部完成
```javascript
<script>
    var c = document.getElementById("myCanvas");//获取元素
    var ctx = c.getContext("2d");//创建context对象
    ctx.fillStyle = "#FF0000";
    ctx.fillRect = "(0,0,150,75)"//绘制一个红色的矩形
</script>
```
- `getContext("2d")` HTML5 对象，拥有多种绘制路径、矩形、圆形、字符以及添加图像的方法
- `fillStyle` 可以是CSS颜色，渐变，或图案,默认为黑色
- `fillRect(x,y,width,height) `方法定义了矩形当前的填充方式   

### 路径
```javascript
var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
ctx.moveTo(0,0); //线条开始坐标
ctx.lineTo(200,100); //线条结束坐标
ctx.stroke(); //进行绘制
```
- `moveTo(x,y)` 线条开始坐标
- `lineTo(x,y)` 线条结束坐标  

### 圆形
- arc(x,y,r,start,stop)
```javascript
var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
ctx.beginPath();
ctx.arc(95,50,40,0,2*Math.PI);
ctx.stroke();
```
### 文本
```javascript
var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
ctx.font="30px Arial";
ctx.fillText("Hello World",10,50);
```
- `font`  定义字体
- `fillText(text,x,y)` 绘制实心的文本
- `strokeText(text,x,y)`绘制空心的文本  

### 渐变
- `createLinearGradient(x,y,x1,y1)` 线条渐变
- `createRadialGradient(x,y,r,x1,y1,r1)` 径向/圆渐变
- `addColorStop()` 方法指定颜色停止,参数为坐标，0-1   

线性渐变:
```javascript
var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
 
// 创建渐变
var grd=ctx.createLinearGradient(0,0,200,0);
grd.addColorStop(0,"red");
grd.addColorStop(1,"white");
 
// 填充渐变
ctx.fillStyle=grd;
ctx.fillRect(10,10,150,80)
```
```javascript
var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
 
// 创建渐变
var grd=ctx.createRadialGradient(75,50,5,90,60,100);
grd.addColorStop(0,"red");
grd.addColorStop(1,"white");
 
// 填充渐变
ctx.fillStyle=grd;
ctx.fillRect(10,10,150,80);
```
### 图像
- `drawImage(image,x,y)` 把一幅图像放置到画布上
 ```javascript
 var c=document.getElementById("myCanvas");
var ctx=c.getContext("2d");
var img=document.getElementById("scream");
ctx.drawImage(img,10,10);
```
## 内联SVG
### SVG
- 可伸缩矢量图形
- 用于定义用于网络的基于矢量的图形
- 使用 XML 格式定义图形
- 图像在放大或改变尺寸的情况下其图形质量不会有损失
### 嵌入
```html
<!DOCTYPE html>
<html>
<body>
 
<svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="190">
  <polygon points="100,10 40,180 190,60 10,60 160,180"
  style="fill:lime;stroke:purple;stroke-width:5;fill-rule:evenodd;">
</svg>
 
</body>
</html>
```
## MathML
- 是数学标记语言，是一种基于XML（标准通用标记语言的子集）的标准，用来在互联网上书写数学符号和公式的置标语言
demo
```html
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>菜鸟教程(runoob.com)</title>
   </head>
    
   <body>
    
      <math xmlns="http://www.w3.org/1998/Math/MathML">
        
         <mrow>
            <msup><mi>a</mi><mn>2</mn></msup>
            <mo>+</mo>
                
            <msup><mi>b</mi><mn>2</mn></msup>
            <mo>=</mo>
                
            <msup><mi>c</mi><mn>2</mn></msup>
         </mrow>
            
      </math>
        
   </body>
   </html>
```
效果显示：$a^2 + b^2 = c^2$
## 拖放
- 抓取对象以后拖到另一个位置
### 设置元素为可拖放
```html
<img draggable="true">
```
### 拖动什么:ondragstart 和 setData()
```javascript
function drag(ev)        
{
ev.dataTransfer.setData("Text",ev.target.id);       
}
```
- `drag(event)`,规定了被拖动的数据
- `dataTransfer.setData()` 方法设置被拖数据的数据类型和值
### 放到何处:ondragover
- ondragover 事件规定在何处放置被拖动的数据
- 默认地，无法将数据/元素放置到其他元素中。如果需要设置允许放置，我们必须阻止对元素的默认处理方式
- 通过调用 ondragover 事件的 event.preventDefault() 方法
```javascript
 event.preventDefault()
```
### 进行放置
```javascript
function drop(ev)        
{        
ev.preventDefault();        
var data=ev.dataTransfer.getData("Text");        
ev.target.appendChild(document.getElementById(data));        
}
```
## 地理地位
- 使用`getCurrentPosition()`方法来获得用户的位置,返回经纬度
```javascript
<script>
var x=document.getElementById("demo");
function getLocation()
  {
  if (navigator.geolocation)
    {
    navigator.geolocation.getCurrentPosition(showPosition);
    }
  else{x.innerHTML="该浏览器不支持获取地理位置。";}
  }
function showPosition(position)
  {
  x.innerHTML="维度: " + position.coords.latitude +
  "<br>经度: " + position.coords.longitude;
  }
</script>
```
### 处理错误和拒绝
```javascript
function showError(error)
{
    switch(error.code) 
    {
        case error.PERMISSION_DENIED:
            x.innerHTML="用户拒绝对获取地理位置的请求。"
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML="位置信息是不可用的。"
            break;
        case error.TIMEOUT:
            x.innerHTML="请求用户地理位置超时。"
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML="未知错误。"
            break;
    }
}
```
## 新input类型
- color：颜色
- date：日期
- datetime：utc时间的日期
- datetime：无时区的日期和时间
- email：e-mail地址输入域
- month：月份
- number：数值
- range：滑动条
- search：搜索域
- rel：电话号
- time：无时区时间控制器
- url：url地址输入域
- week：选择周和年
## 表单元素
- datalist 输入域的选项列表，需要和input绑定  

demo
```html
<input list="browsers">
 
<datalist id="browsers">
  <option value="Internet Explorer">
  <option value="Firefox">
  <option value="Chrome">
  <option value="Opera">
  <option value="Safari">
</datalist>
```
- keygen 用于表单的密钥对生成器字段，当提交表单时，会生成两个键，一个是私钥，一个公钥，私钥存储于客户端，公钥则被发送到服务器。公钥可用于之后验证用户的客户端证书
demo
```html
<form action="demo_keygen.asp" method="get">
用户名: <input type="text" name="usr_name">
加密: <keygen name="security">
<input type="submit">
</form>
```
- output 用于不同类型的输出，比如计算或脚本输出
```html
<form oninput="x.value=parseInt(a.value)+parseInt(b.value)">0
<input type="range" id="a" value="50">100 +
<input type="number" id="b" value="50">=
<output name="x" for="a b"></output>
</form>
```
## 表单属性
- `autocomplete `(form,input) 自动填充
- `novalidate`(form) 在提交表单时不验证form或input域
- `autofocus`(input) 页面加载时，域自动获取焦点
- `from`(input) 输入域所属表单
- `formaction`(input) 描述表单提交的url地址
- `fromenctype`(input) 描述表单提交到服务器的数据编码
- `formmethod`(input)  定义表单提交方式
- `formnovalidate`(input) 提交表单时无需验证input
- `formtarget` (input) 指定一个名称或一个关键字来指明表单提交数据接收后的展示
- `heigth width`(input) input的宽高
- `list`(input) 输入选项列表
- `min max`(input) 规定输入的最值
- `multiple`(input) 选择多个值
- `pattern`(input) 正则来验证input的值
- `placeholder`(input) 规定非空
- `step`(input) 规定输入域合法数字间隔
## 语义元素
> 够清楚的描述其意义给浏览器和开发者
- `<section>` 节，区段
- `<article>` 独立内容
- `<nav>` 导航
- `<aside>` 主区域以外的内容(侧边栏...)
- `<header>` 头部
- `<footer>` 底部
- `<figure>` 独立流内容(图像，表格，代码....)
- `<figcaption>` 定义figure标题
## Web存储
> 在本地存储用户的浏览数据
- `localStorage`  用于长久保存整个网站的数据，保存的数据没有过期时间，直到手动去除
- `sessionStorage`  用于临时保存同一窗口(或标签页)的数据，在关闭窗口或标签页之后将会删除这些数据   

检查浏览器是否支持
```javascript
if(typeof(Storage)!=="undefined")
{
    // 是的! 支持 localStorage  sessionStorage 对象!
    // 一些代码.....
} else {
    // 抱歉! 不支持 web 存储。
}
```
### localStorage
```javascript
localStorage.sitename="hello world!";
document.getElementById("result").innerHTML="网站名：" + localStorage.sitename;
```
- 使用 key="sitename" 和 value="菜鸟教程" 创建一个 localStorage 键/值对
- 检索键值为"sitename" 的值然后将数据插入 id="result"的元素中   
- 键/值对通常以字符串存储，你可以按自己的需要转换该格式   

另一种写法:
```javascript
// 存储
localStorage.sitename = "菜鸟教程";
// 查找
document.getElementById("result").innerHTML = localStorage.sitename;
```
常用API(sessionStorage同样适用)
- 保存数据：`localStorage.setItem(key,value)`
- 读取数据：`localStorage.getItem(key)`
- 删除单个数据：`localStorage.removeItem(key)`
- 删除所有数据：`localStorage.clear()`
- 得到某个索引的key：`localStorage.key(index)`
### sessionStorage
```javascript
if (sessionStorage.clickcount)
{
    sessionStorage.clickcount=Number(sessionStorage.clickcount)+1;
}
else
{
    sessionStorage.clickcount=1;
}
document.getElementById("result").innerHTML="在这个会话中你已经点击了该按钮 " + sessionStorage.clickcount + " 次 ";
```
# CSS3
## 边框
- `border-radius` 圆角
- `box-shadow` 阴影
- `border-image` 边框图片
## 背景
- `background-image` 背景图片
- `background-size` 背景图像大小
- `background-origin` 背景图像位置
- `background-clip` 背景剪裁
## 渐变
- 线性渐变（Linear Gradients）- 向下/向上/向左/向右/对角方向
- 径向渐变（Radial Gradients）- 由它们的中心定义  

### 线性渐变  
- 从上到下  
`background-image: linear-gradient(#e66465, #9198e5);`  
- 从左到右  
`background-image: linear-gradient(to right, red , yellow);`  
- 对角(左上到右下)  
`background-image: linear-gradient(to bottom right, red, yellow);`
- 多个颜色   
`background-image: linear-gradient(red, yellow, green);`
- 带透明度(transparent)  
`background-image: linear-gradient(red, yellow, green);`
- 重复   
`background-image: repeating-linear-gradient(red, yellow 10%, green 20%);`
### 径向渐变
- 均匀分布   
`background-image: radial-gradient(red, yellow, green);`
- 不均匀分布  
`background-image: radial-gradient(red 5%, yellow 15%, green 60%);`
- 设置形状(circle 为圆 ellipse 为椭圆)  
`background-image: radial-gradient(circle, red, yellow, green);`
- 重复  
`background-image: repeating-radial-gradient(red, yellow 10%, green 15%);`
- 不同尺寸  
`background-image: radial-gradient(closest-side at 60% 55%, red, yellow, black);`
  1. closest-side
  2. farthest-side
  3. closest-corner
  4. farthest-corner
## 文本效果
- `text-shadow`文本阴影
- `box-shadow` 盒子阴影
- `text-overflow` 文本溢出显示方式(ellipsis为... clip为隐藏)
- 换行(`word-wrap:break-word` 强制文本换行 `word-break:keep-all` 单词换行 `word-break:break-all` 强制换行)
## 字体
- `font-family` 字体名称
- `font-weigth` 字体粗细(100-900)
## 2D转换
### `translate()`
根据左(X轴)和顶部(Y轴)位置给定的参数，从当前元素位置移动
```css
div
{
  transform: translate(50px,100px);
  -ms-transform: translate(50px,100px); /* IE 9 */
  -webkit-transform: translate(50px,100px); /* Safari and Chrome */
}
```
### `rotate()`
在一个给定度数顺时针旋转的元素
```css
div
{
  transform: rotate(30deg);
  -ms-transform: rotate(30deg); /* IE 9 */
  -webkit-transform: rotate(30deg); /* Safari and Chrome */
}
```
### `scale()`
素增加或减少的大小，取决于宽度（X轴）和高度（Y轴）的参数
```css
div
{
  -ms-transform:scale(2,3); /* IE 9 */
  -webkit-transform: scale(2,3); /* Safari */
  transform: scale(2,3); /* 标准语法 */
}
```
### `skew()`
包含两个参数值，分别表示X轴和Y轴倾斜的角度
```css
div
{
  transform: skew(30deg,20deg);
  -ms-transform: skew(30deg,20deg); /* IE 9 */
  -webkit-transform: skew(30deg,20deg); /* Safari and Chrome */
}
```
### `matrix()`
matrix 方法有六个参数，包含旋转，缩放，移动（平移）和倾斜功能
```css
div
{
  transform:matrix(0.866,0.5,-0.5,0.866,0,0);
  -ms-transform:matrix(0.866,0.5,-0.5,0.866,0,0); /* IE 9 */
  -webkit-transform:matrix(0.866,0.5,-0.5,0.866,0,0); /* Safari and Chrome */
}
```
## 3D转换
### `rotateX()`
围绕其在一个给定度数X轴旋转的元素
```css
div
{
  transform: rotateX(120deg);
  -webkit-transform: rotateX(120deg); /* Safari 与 Chrome */
}
```
### `rotateY()`
围绕其在一个给定度数Y轴旋转的元素
```css
div
{
  transform: rotateY(130deg);
  -webkit-transform: rotateY(130deg); /* Safari 与 Chrome */
}
```
## 过渡
- 指定要添加效果的CSS属性
- 指定效果的持续时间  

应用于宽度属性的过渡效果
```css
div
{
  transition: width 2s;
  -webkit-transition: width 2s; /* Safari */
}
div:hover
{
  width:300px;
}
```
多项改变
```css
div
{
  transition: width 2s, height 2s, transform 2s;
  -webkit-transition: width 2s, height 2s, -webkit-transform 2s;
}
```
## 动画
### `@keyframes`
- 创建动画
- 内指定一个 CSS 样式和动画将逐步从目前的样式更改为新的样式   

背景变色动画
```css
@keyframes myfirst
{
    from {background: red;}
    to {background: yellow;}
}
 
@-webkit-keyframes myfirst /* Safari 与 Chrome */
{
    from {background: red;}
    to {background: yellow;}
}
```
把自定义背景变色动画绑定到div
```css
div
{
    animation: myfirst 5s;
    -webkit-animation: myfirst 5s; /* Safari 与 Chrome */
}
```
另一种写法:
```css
@keyframes myfirst
{
    0%   {background: red;}
    25%  {background: yellow;}
    50%  {background: blue;}
    100% {background: green;}
}
 
@-webkit-keyframes myfirst /* Safari 与 Chrome */
{
    0%   {background: red;}
    25%  {background: yellow;}
    50%  {background: blue;}
    100% {background: green;}
}
```
- %代表动画时间
## 多列
### 创建多列(`column-count`)
将div分为3列
```css
div {
    -webkit-column-count: 3; /* Chrome, Safari, Opera */
    -moz-column-count: 3; /* Firefox */
    column-count: 3;
}
```
### 多列间隙(`column-gap`)
指定div列与列间隙为40px
```css
div {
    -webkit-column-gap: 40px; /* Chrome, Safari, Opera */
    -moz-column-gap: 40px; /* Firefox */
    column-gap: 40px;
}
```
### 列边框
- `column-rule-style` 属性指定了列与列间的边框样式
```css
div {
    -webkit-column-rule-style: solid; /* Chrome, Safari, Opera */
    -moz-column-rule-style: solid; /* Firefox */
    column-rule-style: solid;
}
```
- `column-rule-width` 属性指定了两列的边框厚度
```css
div {
    -webkit-column-rule-width: 1px; /* Chrome, Safari, Opera */
    -moz-column-rule-width: 1px; /* Firefox */
    column-rule-width: 1px;
}
```
- `column-rule-color` 属性指定了两列的边框颜色
```css
div {
    -webkit-column-rule-color: lightblue; /* Chrome, Safari, Opera */
    -moz-column-rule-color: lightblue; /* Firefox */
    column-rule-color: lightblue;
}
```
- `column-width` 属性指定了列的宽度
```css
div {
    -webkit-column-width: 100px; /* Chrome, Safari, Opera */
    column-width: 100px;
}
```
## 用户界面
- `resize`调整尺寸  

由用户指定一个div元素尺寸大小
```css
div
{
    resize:both;
    overflow:auto;
}
```
- `box-sizing` 方框大小调整  

规定两个并排的带边框方框
```css
div
{
    box-sizing:border-box;
    -moz-box-sizing:border-box; /* Firefox */
    width:50%;
    float:left;
}
```
- `outline-offset` 外形修饰  

规定边框边缘之外 15 像素处的轮廓
```css
div
{
    border:2px solid black;
    outline:2px solid red;
    outline-offset:15px;
}
```

