package com.zzj.learn;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLDOMTest {
	public static String xmlpath = "D:\\eclipse\\junoee\\workspace\\helloworld\\src\\files\\configuration_list.xml";
	
	public static void paseXMLConfig(String path) {
		System.out.println(path);// indeed the path(parameter)
		Document doc;
		DocumentBuilderFactory factory;
		DocumentBuilder docBuilder;
		Element root;
		String elementName;
		String[] elementsName = new String[] {

		};
		FileInputStream in;
		try {
			in = new FileInputStream(path);
			factory = DocumentBuilderFactory.newInstance();
			docBuilder = factory.newDocumentBuilder();
			
			doc = docBuilder.parse(in);
			System.out.println("parse successfull");
			root = doc.getDocumentElement();
			elementName = root.getNodeName();
			print("*********begin**********");
			NodeList children = root.getChildNodes();
			Node tempNode;
			for (int i = 0; i<children.getLength();i++){
				tempNode = children.item(i);
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println(tempNode.getNodeName());
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	private static void print(Object obj){
		System.out.println(obj);
	}
	

	// ��ӡĳ���ڵ��ȫ������
	public static void printAttributes(Element elem) {
		NamedNodeMap attributes;
		int i, max;
		String name, value;
		Node curNode;

		attributes = elem.getAttributes();
		max = attributes.getLength();
		
		elem.getChildNodes();
		elem.getElementsByTagName("");

		for (i = 0; i < max; i++) {
			curNode = attributes.item(i);
			name = curNode.getNodeName();
			value = curNode.getNodeValue();
			System.out.println("\t" + name + " = " + value);
		}
	}

	// ��ӡ���еĽڵ�����ƺ�ֵ
	// �ķ������õݹ鷽ʽ��ӡ�ĵ���ȫ���ڵ�
	public static void printElement(Element elem, int depth) {
		String elementName;
		NodeList children;
		int i, max;
		Node curChild;
		Element curElement;
		String nodeName, nodeValue;

		// elementName = elem.getNodeName();
		// ��ȡ����ڵ��ȫ���ӽڵ�
		children = elem.getChildNodes();

		// ��һ����ʽ��ӡ����ڵ�
		for (int j = 0; j < depth; j++) {
			System.out.print(" ");
		}
		printAttributes(elem);

		// ���õݹ鷽ʽ��ӡȫ���ӽڵ�
		max = children.getLength();
		for (i = 0; i < max; i++) {

			curChild = children.item(i);

			// �ݹ��˳�����
			if (curChild instanceof Element) {
				curElement = (Element) curChild;
				printElement(curElement, depth + 1);
			} else {
				nodeName = curChild.getNodeName();
				nodeValue = curChild.getNodeValue();

				for (int j = 0; j < depth; j++)
					System.out.print(" ");
				System.out.println(nodeName + " = " + nodeValue);
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		paseXMLConfig(xmlpath);
	}

}
