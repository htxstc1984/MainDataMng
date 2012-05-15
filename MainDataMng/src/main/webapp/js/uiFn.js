function getFormWin(win, title, actionUrl, items, params) {
	var fp = new Ext.form.FormPanel({ // 注意：Ext.form.FormPanel=Ext.FormPanel
		title : title,
		region : "center",
		width : 300,
		// height:250,
		bodyStyle : 'padding:5px 5px 0',
		// renderTo : 'divFormPanel',
		frame : true,// 在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
		url : actionUrl,// 提交地址
		method : 'post',// 提交方法
		defaults : { // 在这里同一定义item中属性，否则需要各个指明
			xtype : 'textfield',
			labelAlign : 'left',
			labelWidth : 80,
			width : 100
		},
		items : items,
		buttonAlign : 'center',// 按钮对其方式
		buttons : [ {
			text : '确定',
			handler : function() {
				debugger;
				this.fp.form.submit({
					waitMsg : '正在登录......',
					url : actionUrl,
					success : function(form, action) {
						alert('登陆成功 地址');
					},
					failure : function(form, action) {
						form.reset();
					}
				});

				win.hide();
				fp.hide();
			}
		}, {
			text : '取消',
			handler : function() {// 点击取消按钮的操作事件
				win.hide();
				fp.hide();
			}
		} ]
	});

	if (!win) {
		win = new Ext.Window({
			title : 'Layout Window',
			closable : true,
			width : 600,
			height : 350,
			border : false,
			plain : true,
			layout : 'border',
			closeAction : 'hide',
			items : [ fp ]
		// 把上面创建的panel和TabPanel放在window中，并采用border方式布局
		});
	}

	win.show();
}

function buildExample() {
	var data = [ [ 1, 'EasyJWeb', 'EasyJF', 'www.easyjf.com' ],
			[ 2, 'jfox', 'huihoo', 'www.huihoo.org' ],
			[ 3, 'jdon', 'jdon', 'www.jdon.com' ],
			[ 4, 'springside', 'springside', 'www.springside.org.cn' ] ];

	var store = new Ext.data.SimpleStore({
		data : data,
		fields : [ "id", "name", "organization", "homepage" ]
	});

	var grid = new Ext.grid.GridPanel({
		renderTo : "grid",
		title : "中国Java开源产品及团队",
		height : 150,
		width : 600,
		columns : [ {
			header : "项目名称",
			dataIndex : "name"
		}, {
			header : "开发团队",
			dataIndex : "organization"
		}, {
			header : "网址",
			dataIndex : "homepage"
		} ],
		store : store,
		autoExpandColumn : 2
	});
}