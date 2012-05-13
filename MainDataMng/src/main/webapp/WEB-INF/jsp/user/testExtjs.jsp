<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/ext-all.css" type="text/css" />
<script type="text/javascript" src="./js/ext-base-debug.js"></script>
<script type="text/javascript" src="./js/ext-all-debug.js"></script>
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
	Ext.onReady(function() {
		//创建TabPanel  
		var tabs = new Ext.TabPanel({
			region : 'center', //border布局，将页面分成东，南，西，北，中五部分，这里表示TabPanel放在中间  
			margins : '3 3 3 0',
			activeTab : 0,
			defaults : {
				autoScroll : true
			},
			items : [ {
				title : 'Bogus Tab',
				html : "第一个Tab的内容."
			}, {
				title : 'Another Tab',
				html : "我是另一个Tab"
			}, {
				title : 'Closable Tab',
				html : "这是一个可以关闭的Tab",
				closable : true
			} ]
		});

		var accordion = new Ext.Panel({
			layout : 'accordion',
			//title : '折叠布局',
			region : 'center',
			split : true,
			width : 350,
			floating : true,
			frame : true,
			collapsible : true,
			titleCollapse : true,
			layoutConfig : {
				activeOnTop : false,
				fill : true,
				hideCollapseTool : false,
				titleCollapse : true,
				animate : true
			},
			items : [ {
				title : "面板一",
				html : "面板一内容"
			}, {
				title : '面板二',
				html : '面板二内容'
			}

			]
		});
		
		var tree = new Ext.tree.TreePanel({
		    //renderTo: 'tree-div',
		    region : 'center',
		    useArrows: true,
		    autoScroll: true,
		    animate: true,
		    enableDD: true,
		    containerScroll: true,
		    border: false,
		    // auto create TreeLoader
		    dataUrl: 'get-nodes.php',

		    root: {
		        nodeType: 'async',
		        text: 'Ext JS',
		        draggable: false,
		        id: 'source'
		    }
		});

		tree.getRootNode().expand();

		
		//定义一个Panel  
		var nav = new Ext.Panel({
			title : 'Navigation',
			layout : 'border',
			region : 'west', //放在西边，即左侧  
			split : true,
			width : 200,
			collapsible : true, //允许伸缩  
			margins : '3 0 3 3',
			cmargins : '3 3 3 3',
			items : [ tree ]
		});

		var view = new Ext.Viewport({
			layout : 'border',
			items : [ nav, tabs ]
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