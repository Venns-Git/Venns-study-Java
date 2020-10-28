# Javaweb-Filter过滤器

Filter：过滤器，用来过滤网站的数据

- 处理中文乱码
- 登录验证
- ....

开发步骤

1. 导包

2. 编写过滤器

	1. 实现Filter接口（javax.servler下的）

	2.    重写方法

		```java
		public class CharacterEncodingFilter implements Filter {
		    //初始化:web服务器启动，就开始初始，随时等待过滤对象出现
		    @Override
		    public void init(FilterConfig filterConfig) throws ServletException {
		        System.out.println("CharacterEncodingFilter初始化");
		    }
		
		    //Chain 链
		    /*
		        1.过滤器中的所有代码，在过滤特定请求的时候都会执行
		        2.必须要让过滤器继续同行
		
		     */
		    @Override
		    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		        servletRequest.setCharacterEncoding("utf-8");
		        servletResponse.setCharacterEncoding("utf-8");
		        servletResponse.setContentType("text/html;charset=UTF-8");
		        System.out.println("CharacterEncodingFilter执行前.....");
		        filterChain.doFilter(servletRequest,servletResponse);//让我们的请求继续走，如果不走，程序到这里就被拦截停止
		        System.out.println("CharacterEncodingFilter执行后");
		    }
		    //销毁：web服务器关闭的时候，过滤就会销毁
		    @Override
		    public void destroy() {
		        System.out.println("CharacterEncodingFilter销毁");
		    }
		}
		```

3. 在web.xml中配置

	```xml
	  <filter>
	    <filter-name>CharacterEncodingFilter</filter-name>
	    <filter-class>com.venns.filter.CharacterEncodingFilter</filter-class>
	  </filter>
	  <filter-mapping>
	    <filter-name>CharacterEncodingFilter</filter-name>
	  <!-- 只要是/servlet的任何请求，都会经过这个过滤器   -->
	    <url-pattern>/servlet/*</url-pattern>
	  </filter-mapping>
	```

	