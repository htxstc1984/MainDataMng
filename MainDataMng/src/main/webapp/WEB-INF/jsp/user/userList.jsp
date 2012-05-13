<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/ext-all.css" type="text/css" />
<script type="text/javascript" src="./js/ext-base-debug.js"></script>
<script type="text/javascript" src="./js/ext-all-debug.js"></script>
<script type="text/javascript">
	var dataArr = new Array();

	<c:forEach var="user" items="${users}">dataArr[dataArr.length] = [
			"${user.pkUser}", "${user.userid}", "${user.username}" ]
	</c:forEach>;

	var cm = new Ext.grid.ColumnModel([ {
		header : 'pk',
		dataIndex : 'id'
	}, {
		header : 'userid',
		dataIndex : 'userid'
	}, {
		header : 'username',
		dataIndex : 'username'
	}, ]);
	cm.defaultSortable = true;

	var ds = new Ext.data.Store({
		proxy : new Ext.data.MemoryProxy(dataArr),
		reader : new Ext.data.ArrayReader({}, [ {
			name : 'id',
			mapping : 0
		}, {
			name : 'userid',
			mapping : 1
		}, {
			name : 'username',
			mapping : 2
		}, ])
	});
	ds.load();
</script>
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
				<td><a
					href="${pageContext.request.contextPath}/getUser/${user.pkUser}.html">编辑</a></td>
			</tr>
		</c:forEach>
	</table>
	<div id="grid" style="heigth: 600"></div>
	<script type="text/javascript">
		var grid = new Ext.grid.GridPanel({
			el : 'grid',
			ds : ds,
			cm : cm,
			height : 800
		});
		grid.render();
	</script>
</body>
</html>