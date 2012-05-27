<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./extjs3.4/resources/css/ext-all.css"
	type="text/css" />
<script type="text/javascript"
	src="./extjs3.4/adapter/ext/ext-base-debug.js"></script>
<script type="text/javascript" src="./extjs3.4/ext-all-debug.js"></script>
<script type="text/javascript" src="./js/util.js"></script>
<script type="text/javascript" src="./js/localJS.js"></script>
<script type="text/javascript">
	if ((typeof Range !== "undefined")
			&& !Range.prototype.createContextualFragment) {
		Range.prototype.createContextualFragment = function(html) {
			var frag = document.createDocumentFragment(), div = document
					.createElement("div");
			frag.appendChild(div);
			div.outerHTML = html;
			return frag;
		};
	}
	var menuXML;
	var menuJson;

	Ext.onReady(function() {
		//创建TabPanel  
		getDataByAjax('./getAuthMenus.html', {}, function afterLoad(response,
				options) {
			//alert(response.responseText);
			if (response.responseText == ""
					|| response.responseText == "\r\n\r\n"
					|| response.responseText == "\r\n") {
				Ext.Msg.alert('提示', '该用户没有分配任何菜单权限！');
				this.location = './index.html';
			} else {
				buildMainFrame(response.responseText);
				var tp = new Ext.Panel({
					// title : 'Navigation',
					layout : 'border',
					region : 'south', // 放在西边，即左侧
					split : true,
					heigth : 50,
					collapsible : false, // 允许伸缩
					//tbar : toolsbar,
					margins : '3 0 3 3',
					cmargins : '3 3 3 3',

				});
				//Ext.getCmp('nav').add(tp).doLayout();
			}

		});
	});
</script>
</head>
<body>
	<form id="form1">
		<div id="main"></div>
	</form>
</body>
</html>