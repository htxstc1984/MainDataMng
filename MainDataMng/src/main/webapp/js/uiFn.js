
function getColSettingByAjax(accUrl, params, callback) {
	var json;
	alert(1);
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