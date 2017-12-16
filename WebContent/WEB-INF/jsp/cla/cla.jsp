<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr/>
		<!-- <a href="${pageContext.servletContext.contextPath }/claa/toForm">添加</a>
	<table>
		<tr>
			<td>id</td>
			<td>name</td>
			<td>action</td>
		</tr>
		<c:forEach items="${claList }" var="cla">
			<tr>
			<td>${cla.claId }</td>
			<td>${cla.claName }</td>
			<td>
				<a href="${pageContext.servletContext.contextPath }/claa/toForm?claId=${cla.claId}">修改</a>/
				<a href="${pageContext.servletContext.contextPath }/claa/delCla?claId=${cla.claId}">删除</a>
			</td>
		</tr>
		</c:forEach>
		
	</table> -->
		<form:select path="claList" items="${claList }"
		itemLabel="userName" itemValue="password" ></form:select>
	
</body>
</html>