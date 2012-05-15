Ext.lib.Ajax.getConnectionObject = function() {
	var activeX = [ 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP' ];
	function createXhrObject(transactionId) {
		var http;
		try {
			http = new XMLHttpRequest();
		} catch (e) {
			for ( var i = 0; i < activeX.length; ++i) {
				try {
					http = new ActiveXObject(activeX[i]);
					break;
				} catch (e) {
				}
			}
		} finally {
			return {
				conn : http,
				tId : transactionId
			};
		}
	}

	var o;
	try {
		if (o = createXhrObject(Ext.lib.Ajax.transactionId)) {
			Ext.lib.Ajax.transactionId++;
		}
	} catch (e) {
	} finally {
		return o;
	}
};

// 解析xml字符串
function loadXML(xmlStr) {
	if (!xmlStr)
		return null; // 空串返回null
	if (window.ActiveXObject) {
		var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
	} else {
		if (document.implementation && document.implementation.createDocument) {
			var xmlDom = document.implementation
					.createDocument("", "doc", null)
		}
	}
	xmlDom.async = false;
	try {
		xmlDom.loadXML(xmlStr);
	} catch (e) { // 非IE浏览器
		var oParser = new DOMParser();
		xmlDom = oParser.parseFromString(xmlStr, "text/xml");
	}
	return xmlDom;
}

// Changes XML to JSON
function xmlToJson(xml) {

	// Create the return object
	var obj = {};

	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
			obj["@attributes"] = {};
			for ( var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}

	// do children
	if (xml.hasChildNodes()) {
		for ( var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof (obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof (obj[nodeName].length) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
};

function getDataByAjax(accUrl, params, sucCallback) {
	Ext.Ajax.request({
		url : accUrl,
		headers : {
			'userHeader' : 'userMsg'
		},
		params : params,
		method : 'POST',
		success : sucCallback,
		failure : function(response, options) {
			Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);

		}
	});
}

// 下面两个函数用于解析xml为树结构输出
function createXmlTree(xmlsrc, callback) {
	var tree = new Ext.tree.TreePanel({
		// el : "main",
		useArrows : true,
		autoScroll : true,
		animate : true,
		enableDD : true,
		collapsible : true,
		containerScroll : true,
		region : 'center',
		loader : new Ext.tree.TreeLoader(),
		border : false,
		autoHeight : true
	});
	var xmlDom = loadXML(xmlsrc);
	try { // 作为xml串解析
		var root = treeNodeFromXml(xmlDom.documentElement || xmlDom);
		tree.setRootNode(root);
		callback.call(tree);
	} catch (e) { // 作为url解析
		var p = new Ext.data.HttpProxy({
			url : xmlsrc || "etc/menu.xml"
		}); // 默认为etc/menu.xml
		p.on("loadexception", function(o, response, e) {
			tree.setRootNode(new Ext.tree.TreeNode("根节点"))
		});
		p.load(null, {
			read : function(response) {
				var doc = response.responseXML;
				tree.setRootNode(treeNodeFromXml(doc.documentElement || doc));
			}
		}, callback, tree);
	}
	return tree;
}

function treeNodeFromXml(XmlEl) {
	var t = ((XmlEl.nodeType == 3) ? XmlEl.nodeValue : XmlEl.tagName);
	if (t.replace(/\s/g, '').length == 0) {
		return null
	}
	var result = {
		text : t
	};

	if (XmlEl.nodeType == 1) {
		Ext.each(XmlEl.attributes, function(a) {
			if (a.nodeName == "url" && XmlEl.hasChildNodes())
				return; // 目录不添加链接属性
			result[a.nodeName] = a.nodeValue;
		});

		var url = result.url;
		result = new Ext.tree.TreeNode(result); // 根据属性设置构建树
		if (url) {
			result.url = url;
		}
		result.expanded = true;

		Ext.each(XmlEl.childNodes, function(el) {
			if ((el.nodeType == 1) || (el.nodeType == 3)) {
				var c = treeNodeFromXml(el);
				if (c) {
					result.appendChild(c);
				}
			}
		});
	}
	return result;
}

var tabs;
var menuTree;
var nav;
var view;

function buildMainFrame(menuXml) {
	tabs = new Ext.TabPanel({
		region : 'center', // border布局，将页面分成东，南，西，北，中五部分，这里表示TabPanel放在中间
		margins : '3 3 3 0',
		activeTab : 0,
		defaults : {
			autoScroll : true
		},
		items : [ {
			title : 'Closable Tab',
			html : "这是一个可以关闭的Tab",
			closable : true
		} ]
	});

	menuTree = new createXmlTree(menuXml, function() {
		// this.render();
		// this.getRootNode().expand();
	});
	menuTree.on('click', function(node, event) {
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
		// IframeReSizeHeight("iframe1");
		// IframeReSizeWidth("iframe1");
	});

	// 定义一个Panel
	nav = new Ext.Panel({
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
		layout : 'border',
		items : [ nav, tabs ]
	});
}

// iframe高度自适应
function IframeReSizeHeight(iframename) {
   　var pTar = document.getElementById(iframename);
   　if (pTar) {
              // iframe的contentDocument 在firefox下为object IE为undefined
              // iframe的Document 在firefox下为undefined IE为object

              var h;
      　    if (pTar.contentDocument&&pTar.contentDocument.body.scrollHeight) {
                       h=pTar.contentDocument.body.scrollHeight;
                       // 取得IE下中iframe的Document
      　　}else if (pTar.Document&&pTar.Document.body.scrollHeight) {
      　             h = pTar.Document.body.scrollHeight;
             }
       　  pTar.height=h;
   　}
}
// iframe宽度自适应
function IframeReSizeWidth(iframename) {
   　　var pTar = document.getElementById(iframename);
   　　if (pTar) {  
                   // firefox
                  var w;
      　    　if (pTar.contentDocument&&pTar.contentDocument.body.scrollWidth) {  
                        w=pTar.contentDocument.body.scrollWidth;
                       // IE
      　　}else if (pTar.Document&&pTar.Document.body.scrollWidth) {  
                       w = pTar.Document.body.scrollWidth; 
      　　}
      　   pTar.width=w;
   　}
}

