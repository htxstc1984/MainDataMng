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

</head>
<body>
	<script type="text/javascript">
		Ext.onReady(function() {
			var grid;
			var store = new Ext.data.JsonStore({
				url : './getUserList2.html',
				root : 'users',
				autoLoad : true,
				fields : [ 'pkUser', 'username', 'userid', 'disable' ]
			});

			var colModel = new Ext.grid.ColumnModel([ {
				header : "pkUser",
				dataIndex : 'pkUser',
				width : 60,
				sortable : true
			}, {
				header : "username",
				dataIndex : 'username',
				width : 150,
				sortable : true
			}, {
				header : "userid",
				dataIndex : 'userid',
				width : 100,
				sortable : true
			}, {
				header : "disable",
				dataIndex : 'disable',
				width : 100,
				sortable : true,
			} ]);

			var tbar = [
					{
						text : "添加",
						handler : function() {
							this.location = "./getUser/newins.html";
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
						},
						scope : this
					}, "-", {
						text : "删除",
						//handler : this.deleteBranch,
						scope : this
					} ];
			grid = buildList(store, colModel, tbar);

		});
		
		
		function buildList(store, colModel, tbar) {
			var store = store;
			var colModel = colModel;
			var grid = new Ext.grid.GridPanel({
				store : store,
				cm : colModel,
				sm : new Ext.grid.RowSelectionModel({
					singleSelect : true
				}),
				region : 'center',
				// renderTo : 'grid',
				width : 600,
				height : 300,
				frame : true,
				tbar : tbar,
				// title : 'Framed with Checkbox Selection and Horizontal Scrolling',
				iconCls : 'icon-grid'
			});

			var dataView = new Ext.Viewport({
				layout : 'border',
				items : [ grid ]
			});

			return grid;
		}
	</script>
	<div id="grid" style="heigth: 600"></div>

</body>
</html>