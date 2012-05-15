<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./extjs3.4/resources/css/ext-all.css"
	type="text/css" />
<script type="text/javascript"
	src="./extjs3.4/adapter/ext/ext-base-debug.js"></script>
<script type="text/javascript" src="./extjs3.4/ext-all-debug.js"></script>
<script type="text/javascript" src="./js/localJS.js"></script>
<script type="text/javascript">
	var users;
	Ext.onReady(function() {
		getDataByAjax('./getUserList2.html', {}, function afterLoad(response,
				options) {
			eval("users={users:" + response.responseText + "}");
			//alert(users);
			buildUserList(users);
		});

	});

	function buildExample() {
		var data = [ [ 1, 'EasyJWeb', 'EasyJF', 'www.easyjf.com' ],
				[ 2, 'jfox', 'huihoo', 'www.huihoo.org' ],
				[ 3, 'jdon', 'jdon', 'www.jdon.com' ],
				[ 4, 'springside', 'springside', 'www.springside.org.cn' ] ];

		var store = new Ext.data.SimpleStore({
			data : data,
			fields : [ "id", "name", "organization", "homepage" ]
		});

		var grid = new Ext.grid.GridPanel({
			renderTo : "grid",
			title : "中国Java开源产品及团队",
			height : 150,
			width : 600,
			columns : [ {
				header : "项目名称",
				dataIndex : "name"
			}, {
				header : "开发团队",
				dataIndex : "organization"
			}, {
				header : "网址",
				dataIndex : "homepage"
			} ],
			store : store,
			autoExpandColumn : 2
		});
	}

	function buildUserList(users) {
		var store = new Ext.data.JsonStore({
			data : users,
			root : 'users',
			fields : [ 'pkUser', 'username', 'userid', 'disable' ]
		});
		//store.load();
		//alert(1);
		var colModel = new Ext.grid.ColumnModel([ {
			header : "pkUser",
			width : 60,
			sortable : true
		}, {
			header : "username",
			width : 150,
			sortable : true
		}, {
			header : "userid",
			width : 100,
			sortable : true
		}, {
			header : "disable",
			width : 100,
			sortable : true,
		} ]);
		var grid = new Ext.grid.GridPanel({
			store : store,
			cm : colModel,
			region : 'center',
			//renderTo : 'grid',
			width : 600,
			height : 300,
			frame : true,
			tbar : [ {
				text : "添加",
				handler : this.showAdd,
				scope : this
			}, "-", {
				text : "修改"
			}, "-", {
				text : "删除",
				//handler : this.deleteBranch,
				scope : this
			} ],

			title : 'Framed with Checkbox Selection and Horizontal Scrolling',
			iconCls : 'icon-grid'
		});

		var dataView = new Ext.Viewport({
			layout : 'border',
			items : [ grid ]
		});
	}
</script>
</head>
<body>
	<!--  
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
-->
	<div id="grid" style="heigth: 600"></div>

</body>
</html>