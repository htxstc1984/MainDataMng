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
<script type="text/javascript" src="./js/uiFn.js"></script>
<script type="text/javascript">
	Ext.onReady(function() {
		var grid;
		var cols;
		var win = new Ext.Window({
			id : "menuTree",
			width : 400,
			height : 400,
			modal : true
		});

		getColSettingByAjax("./getColums.html", {
			className : "com.itg.maindata.domain.MdPsn",
			pk : ""
		}, function(response, options) {
			eval("cols=" + response.responseText);
			var fields = new Array();
			var colsetting = cols.cols;
			var colModelItems = new Array();
			for (i = 0; i < colsetting.length; i++) {
				colitem = colsetting[i];
				fields[fields.length] = colitem.fieldName;
				var colModelItem = {};
				colModelItem.header = colitem.title;
				colModelItem.dataIndex = colitem.fieldName;
				colModelItem.width = colitem.width;
				colModelItem.sortable = colitem.sortable;
				colModelItems[colModelItems.length] = colModelItem;
			}
			var store = new Ext.data.JsonStore({
				url : './getPsnList.html',
				root : 'psns',
				autoLoad : true,
				fields : fields
			});
			var colModel = new Ext.grid.ColumnModel(colModelItems);
			var tbar = [
					{
						text : "添加",
						handler : function() {
							this.location = "./getPsn/newins.html";
						},
						scope : this
					},
					"-",
					{
						text : "修改",
						handler : function() {
							var row = grid.getSelectionModel().getSelected();
							if (row) {
								this.location = "./getPsn/"
										+ row.get("pkMdPsn") + ".html";
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

	});
</script>
</head>
<body>
	<div id="grid" style="heigth: 600"></div>
</body>
</html>