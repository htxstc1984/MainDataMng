// 解析xml字符串
function loadXML(xmlStr) {
	if (!xmlStr)
		return null; // 空串返回null
	if (window.ActiveXObject) {
		var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
	} else {
		if (document.implementation && document.implementation.createDocument) {
			var xmlDom = document.implementation
					.createDocument("", "doc", null);
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
		draggable : false,
		useArrows : true,
		autoScroll : true,
		animate : true,
		enableDD : false,
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
			tree.setRootNode(new Ext.tree.TreeNode("根节点"));
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
		return null;
	}
	var result = {
		checked : false,
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
		result.draggable = false;

		if (url) {
			result.url = url;
		} else {
			result.expanded = true;
		}

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

// 这个方法是选择父节点,自动选中所有的子节点
function selectAllChildren(node, checked) {
	checked ? node.expand() : node.collapse();
	if (node.hasChildNodes()) {
		node.eachChild(function(child) {
			child.attributes.checked = checked;
			var cb = child.ui.checkbox;
			if (cb)
				cb.checked = checked;
			selectAllChildren(child, checked);
		});
	}
}
// 这个方法是选择子节点,自动选中父节点的父节点
function selectAllParent(node, checked) {
	if (checked) {
		node.expand();
		var parentNode = node.parentNode;
		if (parentNode != undefined) {
			parentNode.attributes.checked = checked;
			var cb = parentNode.ui.checkbox;
			if (cb)
				cb.checked = checked;
			selectAllParent(parentNode, checked);
		}
	}
}
