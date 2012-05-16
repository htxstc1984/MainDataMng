package com.itg.maindata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.itg.maindata.domain.SyMenu;

public class Util {

	public Document transMenuToXml(List<SyMenu> menus) {
		Map<String, Element> elements = new HashMap<String, Element>();
		Document doc = DocumentHelper.createDocument();

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

		return doc;
	}
}
