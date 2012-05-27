var tabs;
var menuTree;
var nav;
var view;
var toolPanel;

function buildMainFrame(menuXml) {
	var toolsbar = new Ext.Toolbar({
		items : [ {
			text : "注销",
			// iconCls : "add24Icon"
			handler : function() {
				alert(this.location);
				this.location = "./logout.html";
			},
			scope : this
		} ]
	});

	tabs = new Ext.TabPanel({
		region : 'center', // border布局，将页面分成东，南，西，北，中五部分，这里表示TabPanel放在中间
		margins : '3 3 3 0',
		activeTab : 0,
		// tbar : toolsbar,
		defaults : {
			autoScroll : true
		},
		items : [ {
			title : 'Closable Tab',
			html : "这是一个可以关闭的Tab",
			closable : true
		} ]
	});

	toolPanel = new Ext.Panel({
		// title : 'Navigation',
		layout : 'border',
		region : 'north', // 放在西边，即左侧
		split : true,
		heigth : 50,
		collapsible : false, // 允许伸缩
		tbar : toolsbar,
		margins : '3 0 3 3',
		cmargins : '3 3 3 3',

	});

	menuTree = new createXmlTree(menuXml, false, function() {
		// this.render();
		// this.getRootNode().expand();
	});
	menuTree
			.on(
					'dblclick',
					function(node, event) {
						// alert(node.id);
						if (node.hasChildNodes()) {
							return;
						}
						if (tabs.getItem("tab" + node.id)) {
							return;
						}
						var newTab = tabs.add({
							title : node.text,
							html : "这是一个可以关闭的Tab",
							id : "tab" + node.id,
							closable : true
						});

						if (node.url) {
							newTab.html = "<IFRAME ID='iframe1' HEIGHT=600 WIDTH=800 FRAMEBORDER=0 SCROLLING=auto SRC='"
									+ node.url + "'></IFRAME>";
						}

						tabs.setActiveTab(newTab);

					});
	menuTree.on('checkchange', function(node, flag) {
		selectAllChildren(node, flag);
		selectAllParent(node, flag);
	});

	// 定义一个Panel
	nav = new Ext.Panel({
		id : 'nav',
		title : 'Navigation',
		layout : 'border',
		region : 'west', // 放在西边，即左侧
		split : true,
		width : 200,
		collapsible : true, // 允许伸缩
		margins : '3 0 3 3',
		cmargins : '3 3 3 3',
		items : [ menuTree ]
	});

	view = new Ext.Viewport({
		id : 'vp',
		layout : 'border',
		items : [ toolPanel, nav, tabs ]
	});
}

// function IframeReSizeHeight(iframename) {
//	
// var pTar = document.getElementById(iframename);
// if (pTar) {
// var h;
// if (pTar.contentDocument&&pTar.contentDocument.body.scrollHeight) {
// h=pTar.contentDocument.body.scrollHeight;
// }else if (pTar.Document&&pTar.Document.body.scrollHeight) {
// h = pTar.Document.body.scrollHeight;
// }
// pTar.height=h;
// }
// }
//
// function IframeReSizeWidth(iframename) {
//
// var pTar = document.getElementById(iframename);
// if (pTar) {
// var w;
// if (pTar.contentDocument&&pTar.contentDocument.body.scrollWidth) {
// w=pTar.contentDocument.body.scrollWidth;
//
// }else if (pTar.Document&&pTar.Document.body.scrollWidth) {
// w = pTar.Document.body.scrollWidth;
//
// }
// pTar.width=w;
//      　  
// }
// }

