# 特点
- 跨设备、跨浏览器
- 响应式布局
- 提供了全面的组件
- 内置jQuery插件
- 支持HTML5、CSS3
- 支持LESS动态样式（LESS使用变量、嵌套、操作混合编码，编写更快、更灵活的CSS）
# 安装
```xml
<!doctype html>
<html>
  <head>
    <!-- 移动设备优先 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Bootstrap的安装和测试</title>
  </head>
  <body>
    <h1>Bootstrap 开启，测试</h1>

    <!-- 引入 JavaScript文件和jQuery -->
    <!-- jQuery 第一个, 然后 Popper.js, 再然后 Bootstrap JS -->
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js" ></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
```
# 布局系统
- 提供`.container`和`.container-fluid`两种容器布局
- 这两种样式是启用栅格系统最基本的要素
- `.containerr：`固体自适应方式 `.container-fluid`是流体100%自适应方式
- 容器布局可以嵌套，不推荐使用
- Bootstrap是以移动端为优先的
## 栅格系统
- 是一个以移动端为优先的网格系统
- 基于12列的布局，5种响应尺寸(面向不同设备)
- 完全使用flexbox流式,完全支持响应式标准
- 具体采用div容器的行，列和对齐内容来构建响应式布局  
demo:
```html
    <div class="container">
        <div class="row">
            <div class="col-sm">one</div>
            <div class="col-sm">two</div>
            <div class="col-sm">three</div>
        </div>
    </div>
```
- 实现1行3列的布局，为了显示清楚，可增加css样式
- `.row `表示一行，`.col-*` 表示一列，sm 表示屏幕类型
- 如果采用`.container-fluid`，则会100%占用屏幕宽度
- 在`.col-sm-* *`还可以强制设定每列所占有的栅格列
- 所占的栅格位正好是12列，超过12列会换行，小于12列则不能100%
- 智能计算和强制设置栅格位都是等宽的，也可以设置不对称
## 栅格等级
- 栅格系统种有5个栅格等级   

>|  | 超小屏幕(<576px)|小屏幕(≥576px)|中等屏幕(≥768px)|大屏幕(≥992px)|超大屏幕(≥1200px)
>| :----:| :----: | :----: |:----:| :----: | :----: |
>| .container最大宽度 | None(auto) | 540px |720px|960px|1140px|
>| 类前缀 | .col | .col-sm- |.col-md-|.col-lg-|.col-xl-|
>|列数|12|    
- 如果同时使用两个或以上的级别，并且比例相同，则遵循移动端设备优先的原则
- 栅格系统支持只指定其中一种或几种，其他随机的方式，指定数字奇偶均可
- 可以通过2个或以上来实现不同设备不同比例的混合布局
- 使用.w-100可以切割栅格栏位，进行分行操作
- 如果强制设置了col-3数值后，那切割后，将不会自动填充
## 在不同屏幕上自适应的隐藏与显示方法
- 要隐藏元素，只需对任何响应屏幕变化使用`.d-none`这个class即可，如:    
    >`.d-{sm,md,lg,xl}-none`
- 要显示元素，只需对任何响应屏幕变化使用`.d-block`这个class即可，如:    
    >`.d-{sm,md,lg,xl}-block`
- 要仅在给定的屏幕尺寸间隔上显示元素，可以将一个`.d-*-none`类与一个.`d-*-*`组合使用，如：
    > `.d-none .d-md.block .d-xl-none`
## 对齐与排列
- 栅格对齐：  
    1. 行对齐，用在行中(给行高看效果):   
        >居顶(默认)：.`align-items-start`    
        >居中:`.align-items-center`   
        >居底:`.align-items-end`   
    2. 列对齐,用在列中
        >居顶(默认)：`.align-self-start`   
        >居中:`.align-self-center`     
        >居底:`.align-self-end`   
    3. 不足100%填充，实现水平对齐方式，用在行中
        >居左(默认):`.justify-content-start`   
        >居中:`.justify-content-center`   
        >居右:`.justify-content-end`    
        >间隔相等(分散):`.justify-content-around`    
        >两端对齐:(分散):`.justify-content-between`  
- 栅格排列
    1. 栅格的列可以排序,使用`.order-N`,N最大值为12
    2. 使用`.oder-first`强行设置列为第一列,`.oredr-last`为最后一列
    3. 使用`.offset-N`或`.offset-*-N`设置列的偏移量，N表示栅格列数
    4. 使用`.ml-N`或`.mr-N`微调列距离
    5. 使用`.ml-auto`和`.mr-auto`来左右对齐
# 内容
## 排版
### 标题类
- 使用h1-6可以创建不同尺寸的标题文字
- 如果是使用其他元素标签，如p或div，调用h1-6同样实现大标题
- 通过`.text-muted`样式，构建大标题的附属小标题
- 还有一种更大型，更加醒目的标题方式:.`dispaly-1~4`
### 文本类
- 需要指定一些段落中重要的内容，可以使用.lead强调
- 比较常用的文本内联元素，来自HTML5：mark,dek,s,ins,u,small,strong,em
- 也可以使用`.mark`,`.small`等方式实现同样元素的效果
- 使用`.title`样式和`abbr`缩略语给文本做提示
- 使用`Blockquote`设置来源备注或引用，使用`.blockquote-footer`设置底部备注来源
- 可以对内容进行居中对齐`.text-center`或居右对齐`.text-right`
### 列表类
- 使用`.list-unstyle`样式，可以将列表初始化
- 使用`.list-inline`和`.list-inline-item`结合多列并排列表
- 使用dl,dt,dd可以实现水平描述，使用`.text-truncate`可以省略溢出
## 代码与图文
### 代码样式
- 使用`code`标签，可以将编程代码放入其中，但还是要手动转义特殊符号
- 使用`pre`标签，配合`code`实现代码块的效果
- 可以在代码区域设置`.pre-srollable`样式，实现固定区域滚动，默认高350px
- 使用`var`标签标识变量部分
- 使用`kdb`标签标识键盘输入
- 使用`spmp`标签表示这是一个示例
### 图文样式
- `.img-fluid`响应式
- `.img-thumbnail`,设置一个空心边框
- `.float-left`和`float-right`设置图片的左右浮动
- `.d-block`设置为区块,再通过margin左右auto方式.max-auto实现居中
- 因为图片本身是内联块属性，所以直接在父层用`.text-center`也可以实现居中
- HTML5新标签`picture`来实现响应式图片设置
- `figure`和`figcaption`实现图文组合显示
## 表格样式
- `.table`为表格的基本样式
- `.table-dark`颜色反转对比效果
- 在thead使用`.thead-light`或`.thead-dark`实现浅黑或深灰调的标头
- `.table-striped`实现数据表的条纹状显示，同样适用反转色调
- `.table-bordered`设置表格边框，同样适用反转色调
- `.table-borderless`设置无边框，同样适用反转色调
- `.table-hover`实现一行悬停的效果，同样适用反转色调
- `.table-sm`实现紧缩型表格，同样适用反转色调
- `.table-success`等语义化实现tr,td,th，同样适用反转色调
- `.table-responsive`实现溢出时出现底部滚动条
- `.table-responsive-sm`只有小于769px溢出时出现底部滚动条
## 颜色和边框
### 颜色样式
- `.text-*`将文本设置成指定的颜色，比如:`.text-succes`,可以.`text-*-50`降色
- `.text-*`也可以实现悬停和焦点的超链接样式，white和muted不支持
- `.bg-*`设置背景色，`.bg-transparent`设置透明度
### 边框样式
- `.border`给元素增加相应的边框，默认是淡灰(`border-*`:top,bottom,left,right指定任意一边)
- `.boeder-*`也可以设置需要的场景,包含:primary,secondary,success,danger,waring,info,light,dark,white
- `.border-0`消除四周的边框,`.boeder-*-0`消除某一边的边框
- `.rounded`和`.rounded-*`实现各种方位的圆角,`round-circle`正园,`.rounded-pill`椭圆
- `.rounded-sm`小圆角,`rounded-lg`大圆角
# 公共样式
## part-1
- `.close`&`times`构建关闭按钮
- `.float-left`,`float-right`,`float-none`实现浮动效果
- `.clearfix`给浮动区域的父级元素添加，清除浮动
- `float-*-left`等来实现不同屏幕的的浮动效果
- `.text-hide`隐藏元素标签内容
- `.overflow-auto`,'.`overflow-hidden`设置区域显示方式
- `visible`,`.invisible`设置内容可见或不可见
- `.align-*`设置文本对齐方式
## part-2
- `.p-*` 来设置内边距(padding)， 范围在0-5之间和auto
- `.m-* `来设置外边距(margin), 范围在0-5之间和auto
- `.pt-* `或 `mt-*` 设置边缘的距离， 这里的t可以是top, 其它还有b(bottom)、l(left)、r(right)等
- `.px-* `或 `mx-*`设置左右边缘距离，这里的x表示(left,right)
- `.py-* `或 `my-*`设置上下边缘距离，这里的y表示(top,bottom)
- `.pt-*-5`, *可以是md、lg等响应式的方式来设置边缘
- `.w-*`设置元素的长度，包括25%、50%、75%、100%和auto
- `.h-*` 设置元素的高度， 包括25%、50%、75%、100%和auto
- `.mw-*` 和 `.mh-*`设置max-width和max-height
- `.vw-*` 和 `.vh-*`设置相对于窗口的大小
- `.shadow-* `实现元素的阴影效果

## part-3
- `.d-*` 来设置元素的display模式，* 可以是none、inline、inline-block、-block、table、table-row等
- 也可以通过 `.d-md-* `中的md设置响应式的媒体查询效果
- `.embed-responsive`实现嵌入响应式， 比如`<iframe>`、`<embed>`等
再使用 `.embed-responsive-16by9` 实现响应式比例，还可以21:9, 4:3, 1:1
- `.text-*` 设置文本的对齐方式，有left、center、right
- 也可以设置 `.text-md-* `实现响应式的媒体查询效果
- `.text-warp`和`.text-nowarp`实现文本溢出时是否换行
- .`text-break`对于很长的字符串， 且中间没有空格实现换行
- `.text-lowercase` 设置小写，`.text-uppercase`设置大写，以及`.text-capitalize`设置首字母大写
- `.font-weight-bold`加粗、`.font-weight-normal` 正常、`.font-weight-light` 纤细、`.font-italic`倾斜
- `.text-monospace` 设置等宽字体
- `.text-reset` 实现字体颜色的重置
- `.text-decoration-none` 删除超链接下划线
## Flex弹性布局-1
- `.d-flex`和`.d-inline-flex`实现开启flex布局样式
- `.flex-row`可以呈现子元素水平方向的位置， 默认居左并从左到右显示(1, 2, 3)
- `.flex-row-reverse`子元素水平方向居右从左到右显示（3，2，1）
- `.flex-column`实现子元素垂直效果， 并从上往下显示(1, 2, 3)
- `.flex-column-reverse`实现子元素垂直效果，并从上往下显示（3，2，1）
- `.justify-content-start`(end、center、between、around)实现内容对齐
- 这5个内容对齐样式，也支持媒体查询: `.justify-content-*-start`
- `.align-items-start`(end、center、baseline、stretch)实现项目对齐
- 这5个项目对齐，也支持媒体查询：`align-items-*-start`
- `.align-self-start`(end、center、baseline、stretch)实现单项目对齐
## Flex弹性布局-2
- `.flex-fill`强制让每个元素项目占据相等的水平宽度
- 多个项目同时设置了`.flex-fill`, 则它们等比例分割宽度，适合导航项目
- 如果其中一个或两个没有设置`.flex-fill`, 则没有设置的会被设置的填充宽度
- `.flex-*-fill`也可以实现响应式的媒体查询操作；
- `.flex-grow-*`, * 表示0或1， 也能实现`.flex-fill`的功能，设置1即可
- 通过元素生成的css可以看出， 其实`.flex-fill`就是flex族的简写形式
-  `.flex-shrink-*`, * 表示0或1， 表示是否强制更换到新行中
- 这一对样式，也支持响应式的媒体查询： `.flex-*-grow|shrink-*`
- `.mr-auto`和`.ml-auto`等对齐方式，对flex元素进行浮动对齐
- 对于垂直方向，也可以使用 .`mb-auto`和 `.mt-auto` 来设置对象方向
- `.flex-wrap`(包裹)	和`.flex-nowrap`(不包裹，默认)来设置子元素项目
- `.flex-wrap-reverse`进行项目排序的倒序
- 这几个样式，也支持响应式的媒体查询：`.flex-*-wrap`等
- `.order-*`, 来设置子元素项目的排序顺序， 支持`.order-*-*`
- `.align-content-start`(end、center、between、around、stretch)垂直对齐
- `.align-content-*-start`等支持媒体响应式查询
# 组件
## 警告提示框
### 警告框样式
- `.alert` 设置警告框基础样式，并使用`.alert-success`设置警告框颜色
- 在使用了警告框的元素内部设置超链接`.alert-link`, 会搭配相应的颜色
- `.alert-heading` 可以设置继承颜色，`alert`本身也可以设置水平线段落等
### 警告框组件
- 在组件一栏，可以和浏览器交互功能，警告框的关闭效果， 通过`data-dismiss=”alert”`实现父元素关闭
- `.fade` 和`.show`实现了关闭后的淡出效果
- `.alert-dismissible`从调试器可以看到是`padding-right:4rem`;
- 真正实现关闭效果的只有`data-dismiss=”alert”`
- 直接使用脚本的方式也可以关闭
## 徽章和面包屑导航
### 徽章样式
- `.badge` 设置徽章基础样式，并使用`.badge-success`等设置徽章颜色
- `.badeg-pill` 将徽章设置成椭圆胶囊式
- 在超链接使用徽章，鼠标悬停时会换色
### 面包屑导航
- `.breadcrumb` 设置一个层次导航(一行分割显示)
## 按钮和按钮组
### 按钮样式
- `.btn `和 `.btn-*` 实现按钮的预设样式；
- `.btn` 不单单在`<button>`元素下使用，也可以在`<a>`、`<input>`下使用；
- `.btn` `.btn-outline-* `可以实现按钮的轮廓效果
- `.btn-lg`或`.btn-sm` 可以实现按钮尺寸的大和小；
- `.btn-block` 将按钮进行block区块设置；
- `.active`启用按钮(默认), 使用.disabled 禁用按钮， 注意`<a>`的禁用（写在class里面，其它写在外面）；
- 添加`data-toggle=”button”`实现按钮切换效果， 使用`.active`可以默认按下；
### 按钮组样式
- `.btn-group`实现传统方案的复选框和单选框样式
- `.btn-group` `.btn-group-toggle` 实现全新方案的复选框和单选框
- `.btn-group` 构建普通的按钮组
- `.btn-toolbar`构建分页工具类
- `.btn-group-lg` 和 `.btn-group-sm`实现大尺寸和小尺寸
- `.btn-group-vertical` 设置垂直按钮组
> 任何带有 class="btn" 的元素都会继承圆角灰色按钮的默认外观。但是 Bootstrap 提供了一些选项来定义按钮的样式，具体如下所示
```java
class="btn"			    默认的按钮
class="btn-primary"		一组按钮中的初始状态
class="btn-success"		一个成功或积极的动作
class="btn-info"		警告信息的上下文按钮
class="btn-warning"		谨慎采取的动作
class="btn-danger"		潜在危险动作
class="btn-link"		看起来想一个连接，但保持按钮的行为
```
## 卡片
- `.card` 来构建卡片，然后可以使用 `.card-body` 建立卡片主体内容
- 卡片主体标题可以使用 `.card-title(标题)` `.card-subtitle(副标题)`等
- 卡片主体使用 `.card-text` 代表文本内容
- 卡片主体使用 `.card-link` 代表超链接
- `.card-img-top` 可以设计一个带主题图片的内容管理器
- `.card-header` 设置一个列表组的标头
- `.card-footer` 配合 `.card-header`, 负责页眉页脚
- 卡片默认是 100%显示的， 可以使用栅格系统嵌套来固定卡片的布局，也可以使用 .w-25、.w-50、.w-75、.w-100来设置卡片的显示百分比
- 卡片支持文本的整体对齐和局部的对齐方式，采用 `.text-center`等
- 卡片使用 `.card-header-tabs` 可以配合列表 `ul`实现导航功能
- 将ul中的文本改成按钮式：`.nav-pills`、`.card-header-pills`实现按钮导航
- 卡片中 `.card-img` 插入一个整体的图片， 再配合 `.card-img-overlay`实背景,这种做法并不是真的作为背景，而是通过定位，让文字浮动在图片上进行编辑
- 卡片通过内部栅格，也可以实现左右水平排列的图文显示
- 卡片可以定制自己的背景和颜色， 这里并无组件样式， 均为之前所配置
- 卡片可以定制自己的边框， 直接使用之前边框组件样式即可
- 在 `.card` 元素外层， 构建一个 `.card-group`分组， 可以紧紧将每个卡片贴在一起
- `.card-group` 本身就具有栅格系统， 但会紧贴， 对应的 `.card-deck` 提供间隙
- 有时，卡片的高度不一，上面两种会自动补全，换行也会留有巨大空隙，这时，采用`.card-columns`可以自我进行填充， 均分空隙
## 列表组
- 列表组`ul`使用`.list-group`，`li` 使用`.list-group-item` 实现
- `.active` 实现首选项
- `.disabled` 实现禁用
- 将 ul、li 替换成 div、a 实现列表组的功能，需配合`.list-group-item-action`
- 或将 `ul`、`li` 替换成 `div`、`button` 和 `a` 效果一样
- 使用`.list-group-flush` 实现紧贴效果，就是外围无边框
- 使用`.list-group-horizontal` 实现水平排列效果
- `.list-group-horizntal-*`，*号可以表示 md,sm,lg,xl 等屏幕响应式
- 列表语境颜色显示，`.list-group-item-*`，*表示颜色
- 可以将`.badge` 徽章引入到列表组里，并进行适当的排版布局
- 结合 jQuery 和 Bootstrap.js 内置的脚本效果，实现内容切换功能
## 导航和滑动门
### 导航(.nav)
- `.nav-item` 和 `active` 暂时体现不出效果
- 更改成 `nav`和` a`，也可以实现相同的导航效果
- `.justify-content-between` 实现导航的居中对齐，-end 右对齐
- `.flex-column` 将导航垂直显示
- `.nav-tabs`实现标签选项卡
- `.nav-pills` 实现按钮式选项卡
- `.nav-fill` 实现水平布局
- `<nav>`来实现以上效果时，此时需要`.nav-item` 来体现效果
- 等宽操作，使用`.nav-justified`，可以通过加上边框来体会区别
### 滑动门
- 即采用 jQuery 和 Bootstrap.js 实现选项卡切换
## 大块屏和旋转效果
### 大块屏
- `.jumbotron`是为了展示一些核心内容或广告内容的区域
### 旋转特效
- `.spinner-border` 实现类 loading...的旋转特效的功能
- `.text-success`(或其它)给旋转特效增加各种颜色
- `.spinner-grow` 可以实现渐变式 loading...特效
- `.m-5(margin)`外边距来跳转距离，如果精确就用普通的 CSS
- `.spinner-broder-sm` 或`.spinner-grow-sm` 实现特效尺寸的大小
- `.text-center`，将特效当作文本进行左中右排列，flex 方式雷同
- 结合`button` 按钮和文本，实现禁用状态下的 loading...
## 折叠菜单
- `.collapse` 构建一个最简单的折叠菜单的效果
- `.multi-collapse` 实现一个按钮控制多个折叠菜单的功能
- `.accordion` 结合`.card` 卡片实现手风琴效果
- 通过 JS 控制，来设置按钮触发的效果
## 下拉菜单
- 下拉菜单组件依赖于 Popper.js，而 Bootstrap 组件包里没有这个文件
- 但组件包里 bootstrap.bundle.js 已经包含了这个组件功能，使用这个即可。只要将将 bootstrap.bundle.js 替代掉 bootstrap.js 即可
- `.dropdown` 等系列样式，来构建下拉菜单效果
- `.dropdown-divider` 给菜单项目之间增加一条分割线
- `.dropdown-toggle-split` 实现分裂式按钮下拉菜单
- 支持使用.`btn-lg` 等系列将下拉按钮设置大小
- `.dropup` 可以将下拉菜单向上展开，会根据上下区域调整
- `.dropright`、`dropleft` 可以将下拉菜单向右、向左展开
- `.active` 设置首选项，使用 `disabled`
- `.dropdown-menu-right` 可以设置菜单向右对齐
## 点击提示和悬停提示
### 悬停提示
- 和下拉菜单一样，这个组件也依赖于 Popper.js
- 悬停提示，必须要脚本端进行绑定才能执行效果
- 支持通过 JS 脚本方式控制提示的各种行为操作
### 点击提示
- 默认情况下，离开触发环境不会自动隐藏，需要设置
- 使用脚本对 popover 进行控制
## 图文混排和滚动监听
### 媒体图文
- 需要一张小图片，然后使用`.media`样式实现混排效果
- 使用`.align-self-end(start、center)`设置图片的显示位置
- 如果想让图片显示在右边，可以直接将`img`标签设置在内容底部
- 如果想要图文混排列表化，可以设置成 `ul>li` 列表模式
### 滚动监听
- 滚动监听，需要列表组和滚动区域结合实现
- 左右列表组也支持滚动监听
## 弹出提示框和模态框
### 提示框
- 使用`.toast` 可以创建一个弹出提示框，默认是隐藏，设置 `show` 显示
- 弹出提示框要求要通过脚本初始化，否则无法实现关闭功能
- 如果关闭时想要过渡效果，加上`.fade` 即可，多个弹窗会向下叠加
- 可以直接用 CSS 定位调整出现的方位，比如右上角
- 点击弹窗时，默认是自动隐藏的，可以关闭它
### 模态框
- 模态框`.modal`系列样式组成，需要很多层次构建
- 模态框支持长文浏览模式，通过鼠标中键或者滚动条浏览
- 模态框默认是偏上显示的，也可以设置居中显示
- 模态框支持设置大小，`.modal-sm(xl、lg)`等
- 模态框可以想象成一个完整的 HTML 区域，支持内部的表单、栅格、其它组件等
## 表单
- `.form-control`、`form-check-input` 构建一个登录页面
- 表单控件中`<input>`、`<textarea>`、`<select>`统一采用`.form-control`
- 对于 file 上传控件，单独使用`.form-control-file` 样式
- `.form-control-sm(xl,lg)`支持放大和缩小操作
- `.form-control-plaintext` 和只读属性 `readonly` 实现只读效果
- 使用栅格布局，再使用 `label` 元素下的`.col-form-label` 实现对齐
- `.form-control-range` 构建一个输入范围
- `.form-check` 系列样式设计复选框和单选框
- `.form-check-inline` 实现单选框和复选框水平排列
- `.form-row` 构建一个响应式布局的表单，并使用谷歌和 opera 测试
- 在邮箱控件左侧增加一个提示标识，使用`.input-group` 等样式
- `.form-text` 给表单控件增加提示功能，如果要一行，使用`.form-inline`
- `<fieldset disabled>`禁用所有整体表单功能
## 输入框
- 输入框就是`<input>`标签元素的强化版组件样式，提供更加丰富的样式
- 输入框组件的核心就是`.input-group` 内部包含的表单样式
- `.input-group-lg(sm)`等可以设置输入框组件大小
- 支持多控件输入模式，也支持多提示模式和按钮模式
- 支持按钮的下拉菜单模式
- 支持 select 下拉菜单模式
- 支持 file 上传文件的组件样式
## 验证表单
- 表单需要在提交前验证，如果出现不合法或合法，显示不同的样式
- 这里不实现动态脚本的提交效果，只设置验证表单的样式
- `.is-valid` 和`.is-invalid` 实现成功或失败
- 对于提示文本，使用`.valid-feedback` 和`.invalid-feedback`
- 更丰富的验证样式，支持复选、单选、下拉和上传
## 导航栏
- 导航栏是一种响应式的组件，主要使用`.navbar-toggler` 来实现
- 如果你不想使用 `ul>li` 这种方式实现导航列表，可以使用 `div>a`
- 给导航栏增加一个查询的表单
- 如果你想给导航栏增加文本，使用`.navbar-text`
- 导航栏可以设置主题配色，主要修改`.navbar-dark` 和`.bg-dark` 两个样式
- 如果想要响应式的菜单按钮显示在左边，只要把 LOGO 移入隐藏切换区域即可
# 总结
Bootstrap为我们提供了非常多的公共样式以及组件，不需要全部记住，只需要在用的时候知道有这么个样式就行了，可以多看官方手册，多在项目中实践，