package com.yesway.wechat.cfg;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AppConfig {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
	private static String appConfigpath;
	private static String confText = "";
	private static Map<String, String> propertyCache;

	public static String getParameter(String name) {
		if (propertyCache != null && propertyCache.containsKey(name)) {
			return (String) propertyCache.get(name);
		}
		return null;
	}

	public static Map<String, String> getPropertyCache() {
		return propertyCache;
	}

	public static boolean reload() {
		return configure(appConfigpath);
	}

	public static boolean configure(String filename) {
		boolean result = true;
		if (filename != null && filename.trim().length() > 0) {
			appConfigpath = filename;
			if (propertyCache != null) {
				propertyCache.clear();
			} else {
				propertyCache = new HashMap<String, String>();
			}
			try {
				SAXReader saxReader = new SAXReader();
				org.dom4j.Document doc = saxReader.read(new File(filename));
				confText = doc.asXML();
				Iterator<Element> elements = doc.getRootElement().elementIterator();
				while (elements.hasNext()) {
					Element subelement = (Element) elements.next();
					String subelementName = subelement.getName();
					if ("param".equals(subelementName)) {
						Attribute key = subelement.attribute("key");
						propertyCache.put(key.getValue(), subelement.getText());
					}
				}
			} catch (Exception e) {
				log.error("configure error:", e);
				result = false;
			}
		} else {
			log.error("configure error: appConfigpath is null");
			result = false;
		}
		return result;
	}

	public static String getConfText() {
		return confText;
	}

	public static boolean saveConf(String confText) {
		boolean result = true;
		try {
			FileWriter fw = new FileWriter(appConfigpath);
			confText = confText.replaceAll("&amp;", "&");
			confText = confText.replaceAll("&", "&amp;");
			fw.write(confText);
			fw.close();
		} catch (Exception e) {
			log.error("saveConf error:", e);
			result = false;
		}
		return result;
	}
}
