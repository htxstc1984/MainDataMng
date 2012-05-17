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
	Ext.onReady(function() {

		formItems = [ new Ext.form.TextField({
			fieldLabel : '用户名',
			name : 'userName',
			id : 'userName',
			allowBlank : false,
			value : "${user.userName}"
		}), new Ext.form.TextField({
			fieldLabel : '密码',
			inputType : 'password',
			allowBlank : false,
			name : 'password',
			id : 'password',
			value : "${user.password}"
		}) ];

		var loginForm = new Ext.form.FormPanel({
			// collapsible : true,// 是否可以展开
			region : "center",
			labelWidth : 75, // label settings here cascade unless overridden
			url : './login.html',
			frame : true,
			title : '登陆',
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
					if (loginForm.form.isValid()) {

						loginForm.form.submit({
							url : './login.html',
							success : function(form, action) {
								this.location = "./test.html";
							},
							failure : function(form, action) {
								Ext.Msg.alert('登录失败', '用户名或密码错误！');
							},
							waitMsg : '正在验证，稍后...'
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
					this.close();
					history.back();
				}
			} ]
		});

		new Ext.Viewport({
			layout : 'border',
			items : [ loginForm ]
		});
	})
</script>
</head>
<body>
	<div id="grid" style="heigth: 600"></div>
</body>
</html>