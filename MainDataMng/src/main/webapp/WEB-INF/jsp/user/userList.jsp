<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>主键</td>
			<td>用户名</td>
			<td>姓名</td>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.pkUser}" /></td>
				<td><c:out value="${user.userid}" /></td>
				<td><c:out value="${user.username}" /></td>
				<td><a href="${pageContext.request.contextPath}/getUser/${user.pkUser}.html">编辑</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>