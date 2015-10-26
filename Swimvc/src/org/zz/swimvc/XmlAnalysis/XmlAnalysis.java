package org.zz.swimvc.XmlAnalysis;



import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.zz.swimvc.tools.FindPath;

public class XmlAnalysis {

	
	public String XmlRead() throws DocumentException{
		
		FindPath fp = new FindPath();
		String Xmlpath = fp.FindXmlPath() + "swimvc-config.xml";
	
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(Xmlpath));
	
		
		
		Element root = document.getRootElement();
		Element e1 = (Element) root.elements("scan").get(0);
		String value = e1.element("class").attributeValue("class-name");
	
		
		return value;
		
	}
	
}
