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
<script type="text/javascript">
	var pk = "${psn.pkMdPsn}";
	var formItems;
	var form1;
	var grid_menu;

	Ext.onReady(function() {
		var cols;
		getColSettingByAjax("./getColums.html", {
			className : "com.itg.maindata.domain.MdPsn",
			pk : pk
		}, function(response, options) {
			eval("cols=" + response.responseText);
			var fields = new Array();
			var colsetting = cols.cols;
			var formItems = new Array();
			for (i = 0; i < colsetting.length; i++) {
				colitem = colsetting[i];
				fields[fields.length] = colitem.fieldName;
				var formItem = {};
				formItem.xtype = colitem.extType;
				formItem.name = colitem.fieldName;
				formItem.id = colitem.fieldName;
				formItem.value = colitem.value;
				formItem.fieldLabel = colitem.title;
				formItem.allowBlank = colitem.allowBlank;

				formItems[formItems.length] = formItem;
			}

			form1 = new Ext.form.FormPanel({
				// collapsible : true,// 是否可以展开
				region : "center",
				labelWidth : 75, // label settings here cascade unless overridden
				url : './updatePsn.html',
				frame : true,
				title : '修改',
				bodyStyle : 'padding:5px 5px 0',
				width : 250,
				waitMsgTarget : true,
				//这个属性决定了load和submit中对数据的处理，list必须是一个集合类型，json格式应该是[]包含的一个数组
				defaults : {
					width : 230
				},
				defaultType : 'textfield',
				items : formItems,
				buttons : [ {
					text : '保存',
					disabled : false,
					handler : function() {
						if (form1.form.isValid()) {
							//mysubmit();
							form1.form.submit({
								url : './updatePsn.html',
								success : function(form, action) {
									Ext.Msg.alert('保存成功', '添加级别成功！');
									history.back();
								},
								failure : function(form, action) {
									Ext.Msg.alert('保存失败', '添加级别失败！');
									history.back();
								},
								waitMsg : '正在保存数据，稍后...'
							});
							//dialog.hide();
						} else {
							Ext.Msg.alert('信息', '请填写完成再提交!');
						}
					}
				}, {
					text : '取消',
					handler : function() {
						//newFormWin.hide();
						history.back();
					}
				} ]
			});

			new Ext.Viewport({
				layout : 'border',
				items : [ form1 ]
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