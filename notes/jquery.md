# What's jQuery
> jq就是一个封装了很多方法的js库。
# Why use jQuery
## 原生js的缺点
> - 不能添加多个入口函数(window.onload),如果添加多个,后面会把前面的覆盖
> - api名字都太长太难记
> - 代码冗余
> - 某些属性或者方法存在浏览器兼容性问题
> - 容错率低
## jq的优点
> - 可以写多个入口函数
> - jq的api名字较容易记
> - 代码简洁(隐式迭代)
> - 解决了浏览器兼容问题
> - 容错率高
# How to use jQuery
>- 1.引入jQuery文件.
>- 2.写一个入口函数.
>- 3.找到你要操作的元素(jq选择器),去操作(添加属性,样式,文本.....)
## jq的入口函数
1. 两种写法
```javascript
// 1.1
$(document).ready(function (){

});
// 1.2
$(function (){

});
```
2. jq入口函数和window.onload入口函数的区别   
>- window.onload入口函数不能写多个,但jq的入口函数可以
>- 执行时机不同：jq入口函数要快于window.onload  
>   - jq入口函数要等待页面上的dom树加载完后执行
>   - window.onload要等待页面上所有的资源（dom树/外部css/js连接，图片）都加载完毕后执行
```javascript
$(function () {
    console.log("我是一个入口函数");
});
$(function () {
    console.log("我又是一个入口函数");
});
```
## jq中的$
1. $是什么？
- 如果报了$ not defined,就说明没有引入jq文件
2. jq文件结构
- 其实是一个自执行函数
```javascript
(function(){
    window.jQuery = window.$ = jQuery;
}());
```
3.    
- 引入一个js文件，时会执行js文件中的代码   
- jQuery文件是一个自执行函数，执行这个jQuery文件中的代码，其实就是执行这个自执行函数
- 这个自执行文件就是给window对象添加一个jQuery属性和$属性
- $其实和jQuery是等价的，是一个函数
`console.log(window.jQuery === window.$); //true`
4. $是一个函数,参数传递不同,效果也不一样
- 如果参数传递的是一个匿名函数 - 入口函数
```javascript
$(function(){

});
```
- 如果参数传递的是一个字符串 - 选择器/创建一个标签
```javascript
$('#one');
$('<div>这是一个div</div>');
```
- 如果参数是一个dom对象,那他就会把dom对象转换成jQuery对象
`$(dom对象);`
## dom对象和jQuery对象
1. dom对象
- 原生js选择器获取到的对象
- 特点：只能调用dom对象或者属性，不能调用jQuery的属性或者方法
```javascript
document.getElementById("");
document.getElementsByClassName("");
var div1 = document.getElementById("one");
div1.style.backgroundColor = 'red';//dom对象可以调用dom属性和方法
div1.css('backgroundColor','green');//报错
``` 
2. jQueryd对象
- 利用jq选择器获取到的对象
- 特点：只能调用jq的对象或者属性，不能调用原生js的属性或者方法
```javascript
var $div1 = $('one');
$div1.css.('backgroundColor','green');
$div1.style.backgroundColor = 'red';//报错
```
- jQuery对象是一个伪数组，jQuery对象其实就是dom对象的一个包装集
3. dom对象和jq对象的相互转换
- dom转jq
```javascript
var div1 = document.getElementById("one");
var $div1 = $(div1);
```
- jq转dom
```javascript
1.使用下标取出来

var $divs = $('div');
var div1 = $div1[0];

2.使用jq的方法 get();

var div2 = $divs.get(1);
```
## 选择器
### 基本选择器
|名称|用法|描述|
|:----:|:----:|:----:|
|ID选择器|$('#id')|获取指定id元素|
|类选择器|$('.class')|获取同一类的元素|
|标签选择器|$('div')|获取同一标签的所有元素|
- 总结：和css的选择器用法一模一样
### 层级选择器
|名称|用法|描述|
|:----:|:----:|:----:|
|子代选择器|$('ul>li')|获取儿子层级的元素，不包含孙子层级的元素|
|后代选择器|$('ul li')|获取ul下的所有li元素,包括孙子等|
- 和CSS选择器用法一模一样
### 过滤选择器
- 这类选择器都带冒号   

|名称|用法|描述|
|:----:|:----:|:----:|
|:eq(index)|$('li:eq(2)')|获取li元素中，选择索引为2的的元素，索引从0开始|
|:odd|$('li:odd')|获取li元素中，选择索引号为奇数的元素|
|:even|$('li:even')|获取li元素中，选择索引号为偶数的元素|
### 筛选选择器（方法）
- 筛选选择器的功能与过滤选择器有点类似，但是用法不一样，筛选选择器主要是方法。       

|名称|用法|描述|
|:----:|:----:|:----:|
|children(selector)|$('ul').children('li')|相当于$('ul-li'),子代选择器|
|find(selector)|$('ul').find('li')|相当于$('ul li'),后代选择器|
|siblings(selector)|$('#first').sibling('li')|查找兄弟节点，不包括自身|
|parent()|$('#first').parent();|查找父亲|
|eq(index)|$('li').eq(2)|相当于$('li:eq(2)')|
|next()|$('li').next()|找下一个兄弟|
|prev()|$('li').prev()|找上一次兄弟|
## 设置文本内容 text();
```html
    <input type="button" value="获取" id="getBtn" />
    <input type="button" value="设置" id="setBtn" />

    <div id="div1">
        我是一个div标签
        <p>我是一个p标签</p>
        <span>span1</span>
    </div>
    <div>
        我是一个div2标签
        <p>
            我是一个p2标签
            <span>span2</span>
        </p>
    </div>
```
1. 获取文本
```javascript
$('#getBtn').click(function () {
    //获取id为div1这个标签的文本
    //会获取到这标签中所有的文本，包括后代元素的文本
    console.log($('#div1').text());

    //获取标签为div元素的文本
    //包含了多个dom元素的jq对象，通过text()方法获取文本，会把所有dom元素的文本获取到。
    console.log($('div').text());
})
```
2. 设置文本
```javascript
$('setBtn').click(function () {
    //给id为div1的这个标签设置文本
    //会覆盖原来的文本内容,如果设置文本中包含标签，是不会把标签解析出来的
    $('#div1').text('我是新设置的文本');
    $('#div1').text('我是新设置的文本<a>我是连接</a>');

    //给标签为div的元素设置文本
    //包含了多个dom元素的jq对象，通过text()方法设置文本，会把所有的dom元素都设置上
    $('div').text('设置的文本');//隐式迭代
})
```
## 设置获取样式 css()
```html
    <input type="button" value="获取" id="getBtn" />
    <input type="button" value="设置" id="setBtn" />
    <div id="div1" style="width: 200px;border: 1px solid red"></div>
    <div id="div2" style="width: 300px;border: 1px solid red"></div>
    <div id="div3" style="width: 400px;border: 1px solid red"></div>
```
1. 获取样式：参数为要获取值的样式名
```javascript
$('#getBtn').click(function () {
    //获取di为div1这个元素的样式
    console.log($('#div1').css('width'));
    //在ie浏览器中，要获取边框这样的样式值，一定要记得给一个准确的边框
    console.log($('div1').css('border-top-width'));

    //获取标签为div的元素们的样式
    //获取包含了多个dom元素的jq对象的样式，只能获取到第一个dom对象的样式
    console.log($('div').css('width'));
});
```
2. 设置样式 css(样式名,样式值)
```javascript
$('setBtn').click(function () {
    //给di为div1的这个元素设置样式
    //设置单样式
    $('#div1').css('width','300px');
    $('#div1').css('height',300); 

    //设置多样式
    $('#div1').css({
        width:300,
        'height':'300px',
        'background-color':'red'
    });

    //给标签为div的元素们设置样式
    $('div').css({
        width:300,
        'height':'300px',
        'background-color':'red'
    });
    //隐式迭代
});
```
## Class类操作
1. 添加类:addClass(类名)
    - 添加单个类:$('obj').addClass('ClassName');
    - 添加多个类:$('obj').addClass('ClassName1 ClassName2');
2. 移除类:removeClass(类名)
    - 移除单个或多个类：与添加同
    - 不写参数则移除全部类
3. 判断类:hasClass(类名)
    - 判断有无某个类，返回值为boolean值
4. 切换类:toggleClass(类名)
    - 如果元素存在指定类，则删除，否则添加
## jq动画
### 三组基本动画
- 显示(show)和隐藏(hide),切换(toggle);
- 滑入(slideDown)与滑出(slideUp),切换(slideToggle);
- 淡入(fadeIn)和淡出(fadeOut),切换(fadeToggle);
```javascript
$obj.show([speed],[callback]);
// speed(可选)：动画执行时间
    //1.如果不传，就没有动画效果。如果是slide和fade系列，会默认为normal
    //2.毫秒值(比如1000)，动画在1000毫秒执行完成(推荐)
    //3.固定字符串:slow(600),normal(400),fast(200)
//callback(可选)：执行完动画后执行的回调函数
slideDown()/slideUp()/slideToggle();同理
fadeIn()/fadeOut()/fadeToggle();同理
```
### 自定义动画 animate();
- $(selector).animate({params},[speed],[easing],[callback]);
- 参数1(params)：必选,对象,代表需要做动画的属性
- 参数2(speed)：可选,代表执行动画的时长
- 参数3(easing)：可选,默认swing(缓动),可以是(linear)
- 参数4(callback)：可选,动画执行完执行的回调函数
### 动画队列与停止动画
- 在同一个元素上执行多个动画,那么对于这个动画来说,后面的动画会被放在动画队列中,等前面的动画执行完成了才会执行
```javascript
// stop方法:停止动画效果
stop(clearQueue,jumpToEnd);
//第一个参数:是否清除队列(true or false) 默认flase
//第二个参数:是否跳转到最终效果(true or false) 默认flase
```
## 节点操作
### 创建节点
1. html();
    - 设置或者获取内容
```javascript
$('#btnHtml').click(function () {
    //获取内容:html()方法不给参数
    //获取到元素的所有内容
    console.log($('#div').html());

    //设置内容:html()方法给参数
    //会把原来的内容给覆盖
    //如果设置的内容中包含了标签，是会把标签给解析出来的
    $('#div').html('我是设置的内容<a href="http://www.baidu.com">百度一下</a>');
});
```
2. $();
    - 确实能创建元素，但是创建的元素只存在与内存中，如果要在页面上显示，就要追加
```javascript
var $link = $('<a href="http://www.baidu.com">百度一下</a>');
$('#div').append($link);
```
### 添加节点
1. append();
    - 父元素.append(子元素); 剪切后作为最后一个子元素添加
2. prepend();
    - 父元素.prepend(子元素); 剪切后作为第一个子元素添加
3. before();
    - 元素A.before(元素B); 把元素B插入到元素A的前面，作为兄弟元素添加
4. after();
    -元素A.after(元素B); 把元素B插入到元素A的后面，作为兄弟元素添加
5. appendTo();
    -子元素.appendTo(父元素); 把子元素作为父元素的最后一个子元素添加
### 清空与移除节点
1. 清空元素:empty();
2. 移除节点:remove();
### 克隆节点:clone()
1. 只存在于内存中，如果要在页面上显示，就应该追加到页面上
2. clone()方法参数不管是true还是false，都是会克隆到后代节点的
3. clone()方法参数是true表示会把事件一起克隆到,参数如果是false就不会克隆事件,默认false
## 设置或者表单内容 val()
1. val()方法 不给参数就是获取
2. val()方法 给参数就是设置
## 操作属性
### attr操作
1. 设置属性
    - 设置单属性    
    `$(obj).attr('属性名','属性值');`    
    - 设置多属性
    ```
    $(obj).attr({
        属性名:'属性值',
        属性名:'属性值',
        属性名:'属性值'
    });
    ```
2. 获取属性
    - `$(obj).attr('属性名');`
    - 如果没有这个属性，获取到的值就是undefined
3. 移除属性: removeAttr();
    - 移除单属性    
    `$(obj).removeAttr('属性名');`
    - 移除多属性     
    `$(obj).removeAttr('属性名 属性名 属性名');`
### prop操作
- 在jq1.6之后，对于checked，selected，disable这类属性Boolean类型的属性来说，不能用attr方法，只能用prop方法
```javascript
//设置属性
$(obj).prop('checked',true);
//获取属性
$(obj).prop('checked')//返回true或false
```
## 尺寸和位置操作
### width方法和height方法
- 设置或者获取高度，不包括内边距，边框和外边距
```javascript
// 带参数表示高度
$('img').height(200);
// 不带参数获取高度
$('img').height();
```
获取网页的可视区宽高
```javascript
// 获取可视区宽度
$(window).width();
// 获取可视区高度
$(window).height();
```
### innerWidth/innerHeight/outerWidth/outerHeight
```javascript
innerWidth()/innerHeight() //返回元素的宽度/高度（包括内边距）
outerWidth()/outerHeight() //返回元素的宽度高度 （包括内边距和边框）
outerWidth(true)/outerHeight(true) //返回元素的宽度高度 （包括内边距 边框和外边距）
```
### scrollTop与scrollLeft
- 设置或者获取垂直滚动条位置
    - 没有参数则获取，反之设置
```javascript
// 获取页面被卷曲的高度
$(window).scrollTop();
//获取页面被卷曲的宽度
$(window).scrollLeft();
```
### offset与position
- offset方法获取元素距离document的位置，position方法获取的是元素距离有定位的父元素(offsetParent)的位置
```javascript
// 获取元素距离document的位置，返回值为对象：（letf：100，top：100）
$(selector).offset();
// 获取相对于其最近有定位的父元素的位置
$(selector).position();
```
## 事件机制
### 发展历程
简单事件绑定--bind事件绑定--delegate事件绑定--on事件绑定   
- 简单事件注册
```javascript
cilck(handler) 单击事件
mouseenter(handler) 鼠标进入事件
mouseleave(handler) 鼠标离开事件
```
缺点:不能同时注册多个事件   
- bind方式注册事件
```javascript
// 第一个参数：事件类型
// 第二个参数：事件处理程序
$('p').bind('click mouseenter',function{
    //事件响应方法
});
```
- delegate注册委托事件
```javascript
// 第一个参数：selector 要绑定事件的元素
// 第二个参数：事件类型
// 第三个参数：事件处理函数
$('.parentBox').delegate('p','click',function(){
    //为.parentBox下面所有的p标签绑定鼠标点击事件
});
```
- on注册事件
### on注册事件
on注册简单事件
```javascript
//表示给$(selector)绑定事件，并由自己触发，不支持动态绑定
$(selector).on('click',function () {});
```
on注册事件委托
```javascript
// 表示给$(selector)绑定代理事件，当必须是它的内部元素span才能注册这个事件，支持动态绑定
$(selector).on('click','span',function() {});
```
事件委托原理
```javascript
// 事件委托的原理
var ul = doucument.querySelector('#ul');
ul.onclick = function(e) {
    //console.log(e.target.tagName);
    if(e.target.tagName.toLowerCase === 'li'){
        console.log(e.target);
    }
}
```
### 事件解绑
- unbind方式(不用)
```javascript
$(selector).unbind(); //解绑所有的事件
$(selector).unbind('click') //解绑指定事件
```
- undelegate方式(不用)
```javascript
$(selector).undelegate(); //解绑所有的事件
$(selector).undelegate('click') //解绑指定事件
```
- off方式(推荐)
```javascript
// 解绑匹配元素的所有事件
$(selector).off();
//解绑匹配元素的所有click事件
$(selector).off('click');
```
### 触发事件
```javascript
$(selector).click(); //触发 click
$(selector).trigger('click');
```
### 事件对象
jq事件对象其实就是js事件对象的一个封装，处理了兼容性
```javascript
// screenX和screenY 对应屏幕最左上角的值
// clientX和clientY 距离页面左上角的位置（忽视滚动条）
// pageX和pageY 距离页面最顶部的左上角的位置（计算滚动条的距离）
// event.stopPropagation() 阻止事件冒泡行为
// event.preventDefault() 组织浏览器默认行为
// return false 既能阻止事件冒泡，又能阻止浏览器默认行为
// event.keyCode 按下的键盘代码
```
### 补充
#### 链式编程
- 通常情况下，只有设置操作才能把链式编程延续下去，因为获取操作的时会，会返回获取到的相应的值，无法返回jq对象    
`end(); //筛选选择器会改变jq对象的dom对象，想要回复到上一次的状态，并且返回匹配元素之前的状态`
#### each()方法
- jq的隐式迭代会对所有的DOM对象设置相同的值，但是如果我们需要给每一个对象设置不同值的时候，就需要自己进行迭代   

作用：遍历jq对象集合，为每个匹配的元素执行一个函数
```javascript
//参数1 表示当前元素在所有匹配元素中的索引号
//参数2 表示当前元素(DOM对象)
$(selector).each(function(index,element){});
```
### 多库共存
- jq使用$作为标识符，但是如果与其他框架中的$冲突时，jq可以释放$符的控制权   
`var c = $.noConflict(); //释放$的控制权，并把$的能力给了c`
## 插件