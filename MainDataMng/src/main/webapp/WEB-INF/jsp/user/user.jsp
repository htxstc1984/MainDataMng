<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<head>
<title>宝宝淘论坛登录</title>
</head>
<body>
	<c:if test="${!empty error}">
		<font color="red"><c:out value="${error}" /></font>
	</c:if>

	<form action="<c:url value="../updateUser.html"/>" method="post">
		用户名： <input type="text" name="userid" value="${user.userid}">
		<br> 密 码： <input type="password" name="password"
			value="${user.password}"> <br> 姓名： <input type="text"
			name="username" value="${user.username}"> <br> <input
			type="hidden" name="pkUser" value="${user.pkUser}" /><br> <input type="submit"
			value="修改" /> <input type="reset" value="重置" />
	</form>
</body>
</html>