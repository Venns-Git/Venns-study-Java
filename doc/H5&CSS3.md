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
## 

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
从上到下  
`background-image: linear-gradient(#e66465, #9198e5);`  
从左到右  
`background-image: linear-gradient(to right, red , yellow);`  
 



