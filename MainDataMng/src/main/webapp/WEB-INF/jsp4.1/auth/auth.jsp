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

<script type="text/javascript">
	var formItems;

	formItems = [ new Ext.form.Hidden({
		fieldLabel : '主键',
		name : 'pkAuthority',
		id : 'pkAuthority',
		value : "${auth.pkAuthority}"
	}), new Ext.form.TextField({
		fieldLabel : '权限',
		name : 'name',
		id : 'name',
		allowBlank : false,
		value : "${auth.name}"
	}), new Ext.form.TextField({
		fieldLabel : '备注',
		allowBlank : false,
		name : 'bz',
		id : 'bz',
		value : "${auth.bz}"
	}), new Ext.form.Hidden({
		//fieldLabel : '禁用',
		name : 'pkMenus',
		id : 'pkMenus',
		value : '',
	}) ];

	var form1;
	var grid_menu;

	Ext.onReady(function() {

		form1 = new Ext.form.FormPanel({
			// collapsible : true,// 是否可以展开
			region : "west",
			labelWidth : 75, // label settings here cascade unless overridden
			url : './updateAuth.html',
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
						mysubmit();
						form1.form.submit({
							url : './updateAuth.html',
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

		var sm = new Ext.grid.CheckboxSelectionModel();// 创建复选择模式对象
		var colModel = new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
				sm, {
					header : "pkMenu",
					dataIndex : 'pkMenu',
					width : 60,
					sortable : true
				}, {
					header : "name",
					dataIndex : 'name',
					width : 150,
					sortable : true
				}, {
					header : "code",
					dataIndex : 'code',
					width : 150,
					sortable : true
				}, {
					header : "url",
					dataIndex : 'url',
					width : 150,
					sortable : true
				}, {
					header : "pkParent",
					dataIndex : 'pkParent',
					width : 150,
					sortable : true
				}, {
					header : "isfolder",
					dataIndex : 'isfolder',
					width : 100,
					sortable : true
				} ]);
		var store = new Ext.data.JsonStore({
			url : './getRAuthMenu.html',
			baseParams : {
				pkAuth : "${auth.pkAuthority}"
			},
			root : 'rams',
			autoLoad : true,
			fields : [ 'haveMenu', 'pkMenu', 'name', 'code', 'url', 'pkParent',
					'isfolder' ],
			listeners : {
				'load' : function() {
					var records = new Array();
					store.each(function(record) {
						if (record.get("haveMenu") == true) {
							records[records.length] = record;
						}
					})
					sm.selectRecords(records);
				}
			}
		});
		grid_menu = new Ext.grid.EditorGridPanel({
			store : store,
			cm : colModel,
			sm : sm,// 创建复选择模式对象
			region : 'center',
			//renderTo : 'grid',
			stripeRows : true,// 显示斑马线
			viewConfig : {// 自动充满表格
				autoFill : true
			},
			width : 600,
			height : 300,
			frame : true,
			//title : 'Framed with Checkbox Selection and Horizontal Scrolling',
			iconCls : 'icon-grid',

		});

		new Ext.Viewport({
			layout : 'border',
			items : [ form1, grid_menu ]
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