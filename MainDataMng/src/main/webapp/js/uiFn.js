function getColSettingByAjax(accUrl, params, callback) {
	var json;
	Ext.Ajax.request({
		url : accUrl,
		headers : {
			'userHeader' : 'userMsg'
		},
		params : params,
		method : 'POST',
		success : callback,
		failure : function(response, options) {
			Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);

		}
	});
	return json;
}

function getUiSettingByCols(accUrl, params, formItems, updateUrl, afterBuild) {
	getColSettingByAjax(accUrl, params, function(response, options) {
		var cols;
		eval("cols=" + response.responseText);
		var fields = new Array();
		var colsetting = cols.cols;
		var pkFieldName = cols.pkFieldName;
		var formItems = new Array();
		for (i = 0; i < colsetting.length; i++) {
			colitem = colsetting[i];
			fields[fields.length] = colitem.fieldName;
			var formItem = {};
			formItem.xtype = colitem.extType;
			formItem.name = colitem.fieldName;
			formItem.id = colitem.fieldName;
			formItem.value = colitem.value;
			if (colitem.fieldName == pkFieldName) {
				formItem.value = pk;
			}
			if (colitem.extType == "checkbox") {
				formItem.checked = (formItem.value == "0") ? false : true;
			}
			formItem.fieldLabel = colitem.title;
			formItem.allowBlank = colitem.allowBlank;

			formItems[formItems.length] = formItem;
		}

		form1 = new Ext.form.FormPanel({
			// collapsible : true,// 是否可以展开
			region : "center",
			labelWidth : 75, // label settings here cascade unless overridden
			url : updateUrl,
			frame : true,
			title : '修改',
			bodyStyle : 'padding:5px 5px 0',
			width : 250,
			waitMsgTarget : true,
			// 这个属性决定了load和submit中对数据的处理，list必须是一个集合类型，json格式应该是[]包含的一个数组
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
						beforeSubmit(form1.form);
						form1.form.submit({
							url : updateUrl,
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
						// dialog.hide();
					} else {
						Ext.Msg.alert('信息', '请填写完成再提交!');
					}
				}
			}, {
				text : '取消',
				handler : function() {
					// newFormWin.hide();
					history.back();
				}
			} ]
		});

		new Ext.Viewport({
			layout : 'border',
			items : [ form1 ]
		});
	});
}

function beforeSubmit(form) {
	transType(form);
};
function transType(form) {
	var items = form.items.items;
	for (i = 0; i < items.length; i++) {
		if (items[i].xtype == "checkbox") {
			items[i].setRawValue((items[i].checked) ? "1" : "0");
		}
	}
}

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
