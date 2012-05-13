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
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <base href="<%=basePath%>">
<!--
<link rel="stylesheet" href="http://localhost:8080/MainDataMng/css/ext-all.css" type="text/css"/>
<script type="text/javascript" src="http://localhost:8080/MainDataMng/js/ext-base.js"></script>
<script type="text/javascript" src="http://localhost:8080/MainDataMng/js/ext-all.js"></script>
-->

<link rel="stylesheet" href="./css/ext-all.css" type="text/css"/>
<script type="text/javascript" src="./js/ext-base.js"></script>
<script type="text/javascript" src="./js/ext-all.js"></script>

<script type="text/javascript">
	Ext.onReady(function() {
		Ext.DomHelper.append(document.body, {
			tag : 'p',
			cls : 'some-class'
		});
		Ext.select('p.some-class').update('Ext Core successfully injected');
	});
	function mysubmit() {
		var authobjs = document.getElementsByName("chk");
		var pkauths = document.getElementById("pkAuths");
		for ( var i = 0; i < authobjs.length; i++) {
			//debugger;
			var auth = authobjs[i];
			if (auth.checked == true) {
				var id = auth.nextSibling;
				pkauths.value = pkauths.value + ";" + id.value;
			}
			//alert(auth.checked);
		}
		if (pkauths.value != "") {
			pkauths.value = pkauths.value.substring(1, pkauths.value.length);
		}
		alert(pkauths.value);
	}
</script>
</head>
<body>
	<c:if test="${!empty error}">
		<font color="red"><c:out value="${error}" /></font>
	</c:if>

	<form action="<c:url value="../updateUser.html"/>"
		onsubmit="mysubmit()" method="post">
		用户名： <input type="text" name="userid" value="${user.userid}">
		<br> 密 码： <input type="password" name="password"
			value="${user.password}"> <br> 姓名： <input type="text"
			name="username" value="${user.username}"> <br> <input
			type="hidden" name="pkUser" value="${user.pkUser}" /><br> <input
			type="hidden" id="pkAuths" name="pkAuths" value="" /><br>

		<table>
			<tr>
				<td>选择</td>
				<td>权限</td>
				<td>说明</td>
			</tr>
			<c:forEach var="auth" items="${auths}">
				<tr id="authobj">
					<td><input type="checkbox" name="chk"
						<c:if test="${auth.haveAuth==true}">checked="checked"</c:if>
						value="${auth.haveAuth}"><input type="hidden"
						id="pkAuthority" name="pkAuthority"
						value="${auth.syAuthority.pkAuthority}" /></td>
					<td><c:out value="${auth.syAuthority.name}" /></td>
					<td><c:out value="${auth.syAuthority.bz}" /></td>
				</tr>
			</c:forEach>
		</table>


		<input type="submit" value="修改" /> <input type="reset" value="重置" />
	</form>
</body>



</html>