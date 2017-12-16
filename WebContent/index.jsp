<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/jquery-3.1.0.js"></script>
<script type="text/javascript">
	$(function(){
		$('a').click(function(){
			alert('333');
			$.post('cla/toList',{userName:'aaa'},function(result){
				alert(result);
			});
		});
		
		$('input[name="userName"]').blur(function(){
			var a = $(this).val();
			if(a.length > 3){
				alert("n");
			}
			
		});
		
		
		
		
		//测试ajax请求获取controller返回的json数据
		$('#testJson').click(function(){
			var data = {userName:'ddd',password:'12344'};
			//发送ajax请求
			$.ajax({
				type:'post',
				url:'claa/testJson',
				data:{claId:'c',claName:'b'},
				dataType:'json',
				success:function(result){
					alert(result);
				}
			});
		});
		
		
		
		
	});
</script>
</head>
<body>	
	<a href="claa/down">下载</a>
	<form action="claa/upload" method="post" enctype="multipart/form-data"   >
		<input type="file" name="file">
		<input type="submit" name="上传"> 
	</form>
	
	<br/>
	<hr/>
	<a href="cla/toList"> zzzzzz</a><br/>
	<form action="claa/interceptor" method="post">
		<input type="text" name="userName"/><br/>
		<input type="password" name="password"><br/>
		<input type="text" name="date" /><br/>
		<input type="submit" value="登录"><br/>
	</form>
	<br/>
	<hr/>
	<span id="testJson">testJson</span>
	
	<hr/>
	<a href="" id="ajax" onclick="return false;">ajax</a>
	<img src="${pageContext.servletContext.contextPath }/resources/image/1.PNG"/>
</body>
</html>