2016年10月8日 15:10:55
	下午直接教了springMVC，可怜我国庆“辛辛苦苦”地把struts2给学完了。
	
2016年10月8日 19:49:09
	晚上看视频自己学的。
	
	建立mapping访问controller的三种方式：
	1.在springMVC-servlet.xml中配置
<bean name="/cla" class="com.zx.controller.ClaController"></bean>

	2在springMVC-servlet.xml中配置
	<!-- 最常用的映射配置方式 -->  
	<!-- <prop key="/hello*.do">helloController</prop>-->  
	<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">  
 		<property name="mappings">  
  			<props>  
   				<prop key="/hello.do">helloController</prop>  
  			</props>  
 		</property>  
	</bean>  
	<bean name="helloController" class="test.HelloController"></bean>  
	
	3.在springMVC-servlet.xml中配置，根据类名访问
	访问时类名首字母要小写
	4.注解
	在controller上加上@Controller 和@RequestMapping("/a") ，并在方法上加
	@RequestMapping("/b")
	
2016年10月9日 09:56:59
	在springmvc-servlet.xml中配置
		<mvc:resources mapping="/resources/**" location="/resources/">	
	可以是这个文件夹中的资源能够被访问，而不被dispatcherServlet拦截。
	(2016年10月9日 12:26:35,这个不能用，完全没有mvc这样的标签.......
			经过一番研究，总结一下，首先，在使用这个标签之前需要先定一个
				<mvc:annotation-driven/>
		这样的标签。
			而在使用所有MVC标签之前需要先导入mvc这个namespaces（命名空间），也就是在xml文件的左下角
		选择namespaces，然后勾选中mvc。当然，如果xml文件左下角没有一些选项卡可以点击的话，需要在设置的
		xml文件选项中开启。
		这样三步,就可以访问静态资源了
	)
	
	可以在controller的方法上写这样的注解,表示路径为/view，能够访问到这个方法的http方法为get
	@RequestMapping(value="/view",method=RequestMethod.GET)
	
	并且假设有个request有个parameter，name为userName，则可以在这个方法的参数括号中写这样的注解
	public String methodA(@RequestParam("userName")String userName){//...}
	
	有看啊看，明白了一个叫请求路径和请求参数的东西，让我自己的理解讲述一下
	假设访问一个url: http://www.zx.com/a/b?userName=123&pwd=123
	那么，这个url中userName和pwd就是请求参数
	假设访问一个url: http://www.zx.com/a/b/userName
	那么在注解上写
	@RequestMapping(value="/a/b/{userName}")
	然后再在方法的参数注解
	public String methodA(@PathVariable("userName")String userName){//...}
	那么。这样就是请求路径
	
	
	下面是controller几种返回model和view的方式
	1.使用Model对象保存model，返回视图路径
	public String toList(Model model){
		model.addAttribute("claList", Db.claList);
		return "cla/cla";
	}
	2.使用ModelAndView对象保存model和路径，返回该对象
	public ModelAndView toList(ModelAndView mav){
		// = new ModelAndView();
		mav.addObject("claList",Db.claList);
		mav.setViewName("cla/cla");
		return mav;
	}
	3.使用Map<Stirng,Object>类型的对象保存model，返回视图路径
	public String toList(Map<String,Object> model){
		model.put("claList", Db.claList);
		return "cla/cla";
	}
	4.直接使用Servlet API
	public String toList(HttpServletRequest request){
		request.setAttribute("claList", Db.claList);
		return "cla/cla";
	}
	
	
	
	public String testA(@RequestParam(required=false) String name){ }
	上面这个方法的参数注解的意思是，name这个参数不是必须的，即使没有也不会报错。而默认的required都是
true，当参数不存在的时候会爆出错误。(@RequestParam(value="aa",required=false)String aa)
这样的写法也可以)(2016年10月9日 13:24:58,再说一点，不加@RequestParam这个注解也是可以接受参数的，
而且即使没接收到参数也不会报错)

	public String testA(@CookieValue("hello")String cookieValue){ }
	上面这个方法的参数注解的意思是，获取请求中名为hello的cookie的值，对了，再说一点，如果@CookieValue
或者上面的@RequestParam的（）中，没有指定名称的话，在编译模式下是默认取同名的参数的.

	public String testA( @RequestHeader ( "Host" ) String hostAddr){}
	上面这个方法的参数注解的意思是，获取请求的头信息，注意一点，这个参数的名字不区分大小写.
	
	再来一个小知识点，那就是@RequestMapping("/a")和@RequestMapping("a")，没差.
	
	
	@RequestMapping(value= "testParams",params={ "param1=value1","param2","!param3"})  
	public String testParams() {}
	上面这个方法的参数注解的意思是，访问这个方法的时候，request的parameter必须有一个名为param1的值为
value的参数，且必须有一个param2的值不限制的参数，且必须没有param3这个参数。

	@RequestMapping (value="testMethod",method={RequestMethod.GET,RequestMethod.DELETE})  
	public String testMethod() {  }
	上面这个方法的参数注解的意思是，只有request使用get或者delete（http的一种请求方法）,才可以访问该方法
	
	
	
	另外在来一个小知识点，那就是当你在参数中定义了request，并且使用request时，如果想使用request.getSession()
方法，但此时HttpSession还没有创建的话，就是出错。

	
	@RequestBody和@ResponseBody，当接收的参数或者是响应的参数list、xml、json之类的格式时，
需要在方法上声明这两个注解。

	另外，想要在controller的参数中直接获取表单数据的实体类，那就直接在参数中定义实体类，然后表单元素的
name等于实体类中的成员变量名就可以了，其他什么都不需要做。

	
	AJAX——现在在说一下怎么在controller中定义适合ajax的方法。需要注意的是，在方法的参数中可以直接定义
各种IO流，并且可以获取request的参数，或者代替response的输出。
	public void getPerson(PrintWriter pw){pw.write("hello");}
	这样写就可以了
	
	

2016年10月9日 13:37:21
	又看了很久的博客，算是稍稍明白了@ModelAttribute这个注解的用法。让给我一一试过去，然后说一下：
	1.
	public String toList(@ModelAttribute("userName")String userName){}
	这样写的意思是，将访问这个方法的request的名为userName的参数直接添加到model，相当于可以直接在接下来
的页面中获取到request中名为userName的参数的值。注意，这个注释里面的字符串一定要写，它不会自动将变量名作为
attribute的名字。其实根据我的理解，这个注解差不多就是model.addAttribute这个语句的简写。
（2016年10月21日 09:30:09  不能写在实体类参数上）
	2.
	在同一个controller中，如果有方法被@ModelAttribute("userName")这样注解，那么当你访问其他方法前，
该方法会被提前执行一次，(即使多个方法，只要注解中的名字不同，也会依次执行)并且接收request提交过来的参数，
提前放入Model中。
	例如
					@ModelAttribute("userName")
					public String testA(){
						String s = "fjsfusnfios";
						return s;
					}
					@RequestMapping("toList")
					public String toList(String password){
						System.out.println();
						return "cla/a";
					}
					(假设是这么写注解的话@ModelAttribute，也就是不写（）,那么，这个参数的name就是
					返回值类型的名字，首字母小写，也就是string)
	可以提前将userName放入model中（当然，上面我的例子中用了s将request的请求参数userName覆盖了
		也就是说，此时这个方法的返回值，也就是userName这个attribute的值
		另外，如果被注解的方法没有返回值的话，这个被注解的方法只会比其他方法先执行，如下：
					@ModelAttribute  
			        public void populateModel(@RequestParam String abc, Model model) {  
			           model.addAttribute("attributeName", abc);  
			        }  
		这样写——虽然好像用处不大，我也只是记录一下。
	）。
	且，也可以在真正被访问的方法toList中这么写参数
	public String toList(@ModelAttribute("userName")String userName,String password){}
	这样就可以继续在这个方法中使用userName了。
	
	注意：也可以从数据库中直接取出一些数据，然后写在被其注解的方法中，直接添加到model中，这样就可以直接在
页面中获取了。

	3.
	如果这么写
				@RequestMapping("toList")
				@ModelAttribute("userName")
				public String toList(String password){return "value";}
	那么返回值表示userName的值，而跳转到的视图则无法控制，将会是默认的请求路径：
		假设访问的是/springmvc01/WEB-INF/cla/toList
		那么，视图路径就是xml文件配置的默认前后路径，加上中间的cla和toList。
		也就是/springmvc01/WEB-INF/jsp/cla/toList.jsp
		

	下面是@SessionAttributes这个注解的一些使用
	首先，这个注解与@ModelAttribute不同，这个注解是写在Controller类上的。
	然后它可以指定名字或者指定类型，将其存储到session中。且，要存储的参数只要从request中提交上来就可以了
无需将要添加的参数在方法的参数中写出来等等。
	它的写法如下：
	@SessionAttributes(value={"a","b"}) //储存name为a或者b的参数
	@SessionAttributes(types={User.calss,Emp.class}) //储存类型为user或者emp的参数
	@SessionAttirbutes("a")//储存name为a的参数
	另外，当存储匹配类型的参数的时候，name用的是该类型的名字，首字母小写。
	
----------------------我是分割线-----------------------------------------------------------------------------------
下面是在controller间或者同一个controller的各个方法间跳转的一些使用技巧
	1.方法return  redirect:/stu/a  redirect表示重定向，后面的是路径，但
个说是重定向，如果在跳转过去的那个方法上加上参数，同样能够获取到前一个方法加入model
中的参数。但如果此时第二个方法不做任何操作再次跳转到一个视图中的话，视图中将无法获取
到这些参数。(！！！注意，上面的结论有错误，那就是重定向之后是完全接收不到数据的，无论之前
是不是加入model)（ !!!!!!!!注意，是写在RedirctAttribute是写在方法参数里
		2016年10月9日 17:18:32，弄清楚了一点，确实算是重定向，如果前一个方法没有加入
	model，跳转过后的方法将无法接受到参数。但是可以使用RedirectAttribute这个接口。
	在第一个方法中定义一个接口，然后使用addAttribute方法将要传的参数add进去就可以。
	这样子在跳转过去的方法中定义好参数，就可以接受到RedirectAttribute中的Attribute。
	
	addFlashAttribute，这个方法与上面的方法的不同之处在于。这个方法在controller的跳转
	间无法传递参数，但无论经过几次传递，一旦到达页面，就可以取得参数。但这些只是临时加入session
	中的（他的原理就是将参数暂时存储在session中），一旦页面再次刷新，session中的数据就会
	被清空。
）
	再说一点，这个重定向只能在controller之间跳转，无法直接跳转到一个页面，
据我研究，如果想要直接重定向到一个页面，需要从一个方法重定向到另一个方法，然后另一个
方法不做任何操作，跳转到一个页面，这样就相当于一个重定向了。
(2016年10月9日 22:39:37 以我浅薄的知识，加上百度。。我上面这个想法居然是正确的。)
	
2016年10月9日 22:16:14
	在说一个，那就是除了redirect:url 还有forward:url，这两者果然就是转发和重定向的区别
，但是，如果在redirect之前将参数加入model或者使用RedirectAttributes接口，然后在
controller之间跳转的话，还是可以获取到参数的————我可以推翻我之前的结论了，那都是错误的。事实证明
重定向之后，完全接收不到之前的参数，除了那个RedirectAttributes接口。
	另外，经过多次实验，如果不出意外，forward：url和redirect：url都只适用于controller间或
其方法之间的跳转。（2016年10月10日 08:27:50————我越想越觉得没有错。从controller到页面，是否
转发参数，也就是把参数加入model完全可以由自己决定，无需在多一个转发或者重定向）

------------------------我又是可爱的分割线--------------------------------------------
2016年10月10日 09:12:56
	刚刚把文件上传弄好了。总结一下。
	1.在springmvc-servlet.xml中配置如下：
		<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="99999999"/>
		<property name="defaultEncoding" value="UTF-8"/>
		<property name="resolveLazily" value="true"/>
	</bean>
	第一个参数是限制上传文件的大小，第二个是设置默认编码，第三个应该是缓存之类的，不是很了解。
	2.在页面加上一个如下表单：
		<form action="claa/upload" method="post" enctype="multipart/form-data"   >
		<input type="file" name="file">
		<input type="submit" name="上传"> 
		</form>
	其中， enctype="multipart/form-data" 。这句话必须的。
	3.定义controller的对应方法，定义方法参数MultipartFile file，并给其加上注解，用来接受表单
name为file的元素提交上来的文件数据,注解如下：@RequestParam(value="file").
	然后可以通过file的各个方法，获取到文件原始名字
	file.getOriginalFilename()
	也可以获取文件大小，并可以验证文件是否为空。
	然后需要使用FileUtils(commons.io包下的FileUtils)类的copyInputStreamToFile()方法。
其中，第一个参数是字节输入流，可以用MultipartFile file 的getInputStream()方法获取，第二个参
数是存储路径，需要新建一个File（注意，是java.io包的File）	，在File的构造函数中，第一个参数是要存储
的路径（本地的），第二个是存储的文件名。例子如下：
@RequestMapping(value="upload")
	public String upload(@RequestParam("file")MultipartFile file) throws IOException{
		if(!file.isEmpty()){
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:/桌面/Desktop/",System.currentTimeMillis() + file.getOriginalFilename()));
		}		
		return "cla/a";
	}
	！！！，注意：需要再导入要去下载的spring中没有的commons-io和commons-fileupload包。
	另外，上传多个文件的方法我没有去试，看了看，差不多，不过需要MultipartFile[] 这样的数组去
接收form中所有name为file的文件，然后遍历，即可依次处理每个文件。
	END.
	
2016年10月10日 13:12:42
	大梦初醒，中午睡了一觉手都麻了，现在继续，一一试验并总结controller中json的运用，当然不会过于完善。
	返回json
	1.处理ajax请求，将要返回的对象转为json格式，并使用printwriter直接输出流，无需返回值，也可以返回
	对象的数组。
	public void testJson(String a,String b,PrintWriter out) throws Exception, JsonMappingException, IOException{//@ResponseBody User
		User user = new User("3","333");
		User user1 = new User("1","111");
		User[] users = {user,user1};
		//jackson包的对Object进行json格式转换的类
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(users);
		out.write(result);
	}
	算了。这块先放着吧。
2016年10月11日 10:09:40
		关于接收$.ajax提交的data。如果提交的是JSON类型的，例如这么写：
		data:{claId:'1',claName:'1'},
		在接收的方法中直接写上Cla实体类会提交错误，而如果写两个String，就可以成功了。
		
		又实验了一次，如果在Cla参数前加上@RequestBody就可以成功接收到两个参数了，例如：
				@RequestMapping("testJson")
				public  String  testJson(@RequestBody Cla cla) throws Exception{
					User user1= new User("1","1");
					ObjectMapper mapper = new ObjectMapper();
					String result = mapper.writeValueAsString(user1);
					return result;
				}
	
---------------------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx---------------------
2016年10月10日 14:49:31
	下面整理下SpringMVC的拦截器这方面的内容。
	额——跟着慕课网的springMVC的拦截器课程学习的，他先讲了下springMVC的一种过滤器，那我也就记一下。
	只要在web.xml中设置如下：
		<filter>
	  	<filter-name>encoding</filter-name>
	  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	  	<init-param>
	  		<param-name>encoding</param-name>
	  		<param-value>UTF-8</param-value>
	  	</init-param>
	  	</filter>
		<filter-mapping>
		  	<filter-name>encoding</filter-name>
		  	<url-pattern>*</url-pattern>
		</filter-mapping>
	就可以了。
	
	好下面，开始拦截器的设置步骤。
	1.创建一个类，实现HandleInterceptor接口。
	（另外可以实现WebRequestInterceptor接口，但这个接口不能拦截请求，不常用）
	2.在springmvc-servlet.xml中注册拦截器，可以设置拦截的请求路径或者不拦截的请求路径，默认拦截所有
请求，具体配置如下：
						//另外，路径中可以使用通配符（eg:?*）
			<!-- 注册拦截器s -->
			<mvc:interceptors>
				<!-- 拦截器 -->
				<mvc:interceptor>
					<!-- 要拦截的请求path -->
					<mvc:mapping path="/claa/*"/>
					<!-- 不拦截的请求path -->
					<mvc:exclude-mapping path="/claa/toList"/>
					<!-- 实现拦截器(HandleInterceptor)的类 -->
					<bean class="com.zx.interceptor.TestInterceptor"></bean>
				</mvc:interceptor>
			</mvc:interceptors>
	再一点，如果想要拦截所有请求的话，拦截器可以直接这么写：
		<mvc:interceptors>
			<bean class="com.zx.interceptor.TestInterceptor"></bean>
		</mvc:interceptors>
		
	ok,下面是拦截器的方法介绍
	
	1.preHandle()
	//在请求到达目标的controller之前调用的方法
	//返回值表示是否需要将当前请求拦截下来，如果返回fasle，当前请求将被终止
	//Object arg2 表示当前被拦截的请求的目标对象
	(在该方法中可以直接用request.getRequestDispatcher（）.forward（），并return false，
	这样就可以拦截并转发请求了。
	)
	
	
	2.postHandle
	//请求经过目标Controller处理完成后，再次发送出去，未到达发送目标地时调用的方法
	//可以通过ModelAndView参数来修改响应的视图，或者发往视图的方法，以及其中的所有参数
	(ps:如果拦截的controller的方法是处理ajax请求的，直接使用out.write()的话，
	ModelAndView中的viewname就是out输出的那串字符。)
	
	3.afterCompletion()
	//请求全部处理完成后调用的方法，相当于servlet的销毁方法。不常用。
	
	当配置多个拦截器的时候，它的运行顺序是，先是根据拦截器链表的顺序先后执行拦截器的preHandle方法，
然后继续执行请求目标controller的方法，完毕后再倒序执行拦截器的postHandle方法和afterCompletion
方法。
	END。2016年10月10日 17:26:01下课了。(2016年10月10日 19:33:12，还有一点，debug的时候是可以
	修改变量值的！！！)
	
2016年10月11日 11:14:16
	再说一个。在方法的参数加如下注解 @RequestParam(value="a",require=false)String a,
和不写，，是没有区别的。都表示这个参数接受name为a的Parameter，并且可以为空。

2016年10月11日 15:11:18
	在发现SpringMVC 和Struts2都是有相应的jsp标签的。
	关于SpringMVC的标签的导入如下：
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	并不难，就说一个
	<form:select path="claList" items="${claList}"></form:select>
	这样子是取出model中一个名字为claList的，值为List<String>的Attribute的.
	
	<form:select path="claList" items="${claList }"
		itemLabel="userName" itemValue="password" ></form:select>
	这样子是取出model中一个名字为claList的，值为List<User>的Attribute的.
	
