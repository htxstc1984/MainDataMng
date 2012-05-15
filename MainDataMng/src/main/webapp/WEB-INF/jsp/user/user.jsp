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
<!--
<link rel="stylesheet" href="http://localhost:8080/MainDataMng/css/ext-all.css" type="text/css"/>
<script type="text/javascript" src="http://localhost:8080/MainDataMng/js/ext-base.js"></script>
<script type="text/javascript" src="http://localhost:8080/MainDataMng/js/ext-all.js"></script>
-->

<link rel="stylesheet" href="./extjs3.4/resources/css/ext-all.css"
	type="text/css" />
<script type="text/javascript"
	src="./extjs3.4/adapter/ext/ext-base-debug.js"></script>
<script type="text/javascript" src="./extjs3.4/ext-all-debug.js"></script>

<script type="text/javascript">
	var formItems;

	formItems = [ new Ext.form.Hidden({
		fieldLabel : '主键',
		name : 'pkUser',
		id : 'pkUser',
		value : "${user.pkUser}"
	}), new Ext.form.TextField({
		fieldLabel : '用户名',
		name : 'userid',
		id : 'userid',
		value : "${user.userid}"
	}), new Ext.form.TextField({
		fieldLabel : '姓名',
		name : 'username',
		id : 'username',
		value : "${user.username}"
	}), new Ext.form.TextField({
		fieldLabel : '密码',
		inputType : 'password',
		name : 'password',
		id : 'password',
		value : "${user.password}"
	}), new Ext.form.Checkbox({
		fieldLabel : '禁用',
		name : 'disable',
		id : 'disable',
		value : "${user.disable}"
	}), new Ext.form.Hidden({
		//fieldLabel : '禁用',
		name : 'pkAuths',
		id : 'pkAuths'
	}) ];

	Ext.onReady(function() {

		var form1 = new Ext.form.FormPanel({
			// collapsible : true,// 是否可以展开
			region : "center",
			labelWidth : 75, // label settings here cascade unless overridden
			url : './updateUser.html',
			frame : true,
			title : '修改',
			bodyStyle : 'padding:5px 5px 0',
			width : 350,
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
						form1.form.submit({
							url : './updateUser.html',
							success : function(from, action) {
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
	function mysubmit() {
		var authobjs = document.getElementsByName("chk");
		var pkauths = document.getElementById("pkAuths");
		for ( var i = 0; i < authobjs.length; i++) {
			//debugger;
			var auth = authobjs[i];
			if (auth.checked == true) {
				var id = auth.nextSibling;
				pkauths.value = pkauths.value + ";" + id.value;
			}
			//alert(auth.checked);
		}
		if (pkauths.value != "") {
			pkauths.value = pkauths.value.substring(1, pkauths.value.length);
		}
		//alert(pkauths.value);
	}
</script>
</head>
<body>
	<!-- 
	<c:if test="${!empty error}">
		<font color="red"><c:out value="${error}" /></font>
	</c:if>

	<form action="<c:url value="./updateUser.html"/>" onsubmit="mysubmit()"
		method="post">
		用户名： <input type="text" name="userid" value="${user.userid}">
		<br> 密 码： <input type="password" name="password"
			value="${user.password}"> <br> 姓名： <input type="text"
			name="username" value="${user.username}"> <br> <input
			type="hidden" name="pkUser" value="${user.pkUser}" /><br> <input
			type="hidden" id="pkAuths" name="pkAuths" value="" /><br>

		<table>
			<tr>
				<td>选择</td>
				<td>权限</td>
				<td>说明</td>
			</tr>
			<c:forEach var="auth" items="${auths}">
				<tr id="authobj">
					<td><input type="checkbox" name="chk"
						<c:if test="${auth.haveAuth==true}">checked="checked"</c:if>
						value="${auth.haveAuth}"><input type="hidden"
						id="pkAuthority" name="pkAuthority"
						value="${auth.syAuthority.pkAuthority}" /></td>
					<td><c:out value="${auth.syAuthority.name}" /></td>
					<td><c:out value="${auth.syAuthority.bz}" /></td>
				</tr>
			</c:forEach>
		</table>


		<input type="submit" value="修改" /> <input type="reset" value="重置" />
	</form>
	<div id="form11"></div>
	 -->
</body>



</html>