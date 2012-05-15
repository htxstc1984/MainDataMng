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
<script type="text/javascript" src="./js/uiFn.js"></script>
<script type="text/javascript">
	var users;
	var win;

	Ext.onReady(function() {
		getDataByAjax('./getUserList2.html', {}, function afterLoad(response,
				options) {
			eval("users={users:" + response.responseText + "}");
			//alert(users);
			buildUserList(users);
		});

	});

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
			sm : new Ext.grid.RowSelectionModel({
				singleSelect : true
			}),
			region : 'center',
			//renderTo : 'grid',
			width : 600,
			height : 300,
			frame : true,
			tbar : [
					{
						text : "添加",
						handler : function() {
							this.location = "./addUser.html";
							//this.getFormWin(win, "添加", "./addCommand.html", formItems) ;
						},
						scope : this
					},
					"-",
					{
						text : "修改",
						handler : function() {
							var row = grid.getSelectionModel().getSelected();

							if (row) {
								this.location = "./getUser/"
										+ row.get("pkUser") + ".html";
							}
							//this.getFormWin(win, "修改", "./updateUser.html", formItems) ;
						},
						scope : this
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