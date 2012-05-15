package com.itg.maindata.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.XMLWriter;

import com.itg.maindata.domain.SyMenu;

public class Util {

	public Document transMenuToXml(List<SyMenu> menus) {
		Map<String, Element> elements = new HashMap<String, Element>();
		// XMLWriter writer = new XMLWriter(res.getOutputStream());
		Document doc = DocumentHelper.createDocument();
		// Element root = doc.addElement("root");
		// root.addElement("text").addText("菜单");
		// root.addElement("nodeType").addText("async");
		// root.addElement("id").addText("source");
		// // Element rootChildren = root.addElement("children");
		// for (SyMenu menu : menus) {
		// if (menu.getPkParent() == null
		// || menu.getPkParent().equalsIgnoreCase("")) {
		// Element level1 = root.addElement("children");
		// level1.addElement("text").addText(
		// menu.getName() == null ? "" : menu.getName());
		// level1.addElement("id").addText(
		// menu.getUrl() == null ? "" : menu.getUrl());
		// level1.addElement("pk").addText(menu.getPkMenu());
		// if (menu.getIsfolder() == 1) {
		// level1.addElement("cls").addText("folder");
		// // level1.addAttribute("leaf", "true");
		// } else {
		// level1.addElement("cls").addText("file");
		// }
		// elements.put(menu.getPkMenu(), level1);
		// }
		// }
		//
		// for (SyMenu menu : menus) {
		// if (menu.getPkParent() != null
		// && !menu.getPkParent().equalsIgnoreCase("")) {
		// if (elements.containsKey(menu.getPkParent())) {
		// Element parent = elements.get(menu.getPkParent());
		// // Element children = parent.addElement("children");
		// Element leaf = parent.addElement("children");
		// leaf.addElement("text").addText(
		// menu.getName() == null ? "" : menu.getName());
		// leaf.addElement("id").addText(
		// menu.getUrl() == null ? "" : menu.getUrl());
		// leaf.addElement("pk").addText(menu.getPkMenu());
		// if (menu.getIsfolder() == 1) {
		// leaf.addElement("cls").addText("folder");
		// // level1.addAttribute("leaf", "true");
		// } else {
		// leaf.addElement("cls").addText("file");
		// }
		// elements.put(menu.getPkMenu(), leaf);
		// }
		//
		// }
		// }

		Element root = doc.addElement("菜单");
		root.addAttribute("expanded", "true");
		for (SyMenu menu : menus) {
			if (menu.getPkParent() == null
					|| menu.getPkParent().equalsIgnoreCase("")) {
				Element folder = root.addElement(menu.getName());
				folder.addAttribute("expanded", "true");
				folder.addAttribute("id", menu.getPkMenu());
				elements.put(menu.getPkMenu(), folder);
			} else {
				if (elements.containsKey(menu.getPkParent())) {
					Element parent = elements.get(menu.getPkParent());
					Element children = parent.addElement(menu.getName());
					if (menu.getIsfolder() == 1) {
						children.addAttribute("expanded", "true");
						children.addAttribute("id", menu.getPkMenu());
					} else {
						children.addAttribute("url", menu.getUrl());
						children.addAttribute("id", menu.getPkMenu());
					}
					elements.put(menu.getPkMenu(), children);
				}
			}
		}

		// res.getOutputStream().
		return doc;
	}
}
