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
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<base href="<%=basePath%>">

<link rel="stylesheet" href="./extjs3.4/resources/css/ext-all.css"
	type="text/css" />
<script type="text/javascript"
	src="./extjs3.4/adapter/ext/ext-base-debug.js"></script>
<script type="text/javascript" src="./extjs3.4/ext-all-debug.js"></script>
<script type="text/javascript" src="./js/util.js"></script>
<script type="text/javascript" src="./js/uiFn.js"></script>
<script type="text/javascript">
	var pk = "${auth.pkAuthority}";
	var formItems = new Array();
	var form1;
	formItems[0] = new Ext.form.Hidden({
		name : 'pkMenus',
		id : 'pkMenus',
		value : '',
	})

	Ext.onReady(function() {
		var cols;
		getUiSettingByCols("./getColums.html", {
			className : "com.itg.maindata.domain.SyAuthority",
			pk : pk
		}, formItems, './updateAuth.html', mysubmit, function(view) {
			var menuTree;
			getDataByAjax('./getAllMenus.html', {}, function afterLoad(
					response, options) {
				menuTree = new createXmlTree(response.responseText, function() {
					// this.render();
					// this.getRootNode().expand();
				});

				menuTree.on('checkchange', function(node, flag) {
					selectAllChildren(node, flag);
					selectAllParent(node, flag);
				});
				
				new Ext.Viewport({
					layout : 'border',
					items : [ menuTree ]
				});
				//menuTree.region = 'east';
				//view.add(menuTree);
				//view.doLayout();
			});
		});
	});
	function mysubmit() {
		var pkMenus = form1.get("pkMenus");

		var sels = grid_menu.selModel.getSelections();
		for (i = 0; i < sels.length; i++) {
			if (sels[i].get("pkMenu")) {
				pkMenus.setValue(pkMenus.getValue() + ";"
						+ sels[i].get("pkMenu"));
			}
		}

		if (pkMenus.getValue() != "") {
			pkMenus.setValue(pkMenus.getValue().substring(1,
					pkMenus.value.length));
		}

	}
</script>
</head>
<body>

</body>

</html>