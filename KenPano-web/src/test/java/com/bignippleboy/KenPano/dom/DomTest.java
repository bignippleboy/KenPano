package com.bignippleboy.KenPano.dom;


import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

public class DomTest extends KoalaBaseSpringTestCase {
	@Test
	public void parseXML() throws DocumentException {
		SAXReader reader = new SAXReader();
		File file = new File("/Users/huangken/Documents/temp/vtour-ken.xml");
		Document doc = reader.read(file);
		
		Node node = doc.selectSingleNode("//plugin[@name='gyro']");
	}
}
