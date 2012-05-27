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
<script type="text/javascript" src="./js/uiFn.js"></script>
<script type="text/javascript" src="./js/util.js"></script>
<script type="text/javascript">
	var pk = "${menu.pkMenu}";
	var formItems;
	var form1;
	var win;

	Ext.onReady(function() {
		var cols;
		getUiSettingByCols("./getColums.html", {
			className : "com.itg.maindata.domain.SyMenu",
			pk : pk
		}, formItems, './updateMenu.html', null, function(form) {
			setParentTrigger(form);
		});

	});

	function setParentTrigger(form) {
		var pkParent = form.get("pkParent");
		pkParent.on("trigger", function() {
			//alert(1);
			getDataByAjax('./getAllMenus.html', {}, function afterLoad(
					response, options) {
				var menuTree = getMenuTree(response.responseText);
				menuTree.on('dblclick', function(node, event) {
					pkParent.setValue(node.id);
					win.hide();
				});
				if (!win) {
					win = new Ext.Window({
						id : "menuTree",
						width : 400,
						height : 400,
						modal : true,
						items : [ menuTree ]
					});
				}
				win.show();
			});

		}, this);
	}
</script>
</head>
<body>

</body>

</html>