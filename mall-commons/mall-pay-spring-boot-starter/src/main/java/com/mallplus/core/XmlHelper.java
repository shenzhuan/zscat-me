package com.mallplus.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * xpath解析xml
 *
 * <pre>
 *     文档地址：
 *     http://www.w3school.com.cn/xpath/index.asp
 * </pre>
 *
 * @author L.cm
 */
public class XmlHelper {
    private final XPath path;
    private final Document doc;

    private XmlHelper(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = getDocumentBuilderFactory();
        // This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all
        // XML entity attacks are prevented
        // Xerces 2 only -
        // http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        // If you can't completely disable DTDs, then at least do the following:
        // Xerces 1 -
        // http://xerces.apache.org/xerces-j/features.html#external-general-entities
        // Xerces 2 -
        // http://xerces.apache.org/xerces2-j/features.html#external-general-entities
        // JDK7+ - http://xml.org/sax/features/external-general-entities
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);

        // Xerces 1 -
        // http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
        // Xerces 2 -
        // http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
        // JDK7+ - http://xml.org/sax/features/external-parameter-entities
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

        // Disable external DTDs as well
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and
        // Entity Attacks"
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(inputSource);
        path = getXpathFactory().newXPath();
    }

    private static XmlHelper create(InputSource inputSource) {
        try {
            return new XmlHelper(inputSource);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static XmlHelper of(InputStream is) {
        InputSource inputSource = new InputSource(is);
        return create(inputSource);
    }

    public static XmlHelper of(File file) {
        InputSource inputSource = new InputSource(file.toURI().toASCIIString());
        return create(inputSource);
    }

    public static XmlHelper of(String xmlStr) {
        StringReader sr = new StringReader(xmlStr.trim());
        InputSource inputSource = new InputSource(sr);
        XmlHelper xmlHelper = create(inputSource);
        sr.close();
        return xmlHelper;
    }

    private static DocumentBuilderFactory getDocumentBuilderFactory() {
        return XmlHelper.XmlHelperHolder.documentBuilderFactory;
    }

    private static XPathFactory getXpathFactory() {
        return XmlHelper.XmlHelperHolder.xPathFactory;
    }

    private Object evalXpath(String expression, Object item, QName returnType) {
        item = null == item ? doc : item;
        try {
            return path.evaluate(expression, item, returnType);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取String
     *
     * @param expression 路径
     * @return String
     */
    public String getString(String expression) {
        return (String) evalXpath(expression, null, XPathConstants.STRING);
    }

    /**
     * 获取Boolean
     *
     * @param expression 路径
     * @return String
     */
    public Boolean getBoolean(String expression) {
        return (Boolean) evalXpath(expression, null, XPathConstants.BOOLEAN);
    }

    /**
     * 获取Number
     *
     * @param expression 路径
     * @return {Number}
     */
    public Number getNumber(String expression) {
        return (Number) evalXpath(expression, null, XPathConstants.NUMBER);
    }

    /**
     * 获取某个节点
     *
     * @param expression 路径
     * @return {Node}
     */
    public Node getNode(String expression) {
        return (Node) evalXpath(expression, null, XPathConstants.NODE);
    }

    /**
     * 获取子节点
     *
     * @param expression 路径
     * @return NodeList
     */
    public NodeList getNodeList(String expression) {
        return (NodeList) evalXpath(expression, null, XPathConstants.NODESET);
    }

    /**
     * 获取String
     *
     * @param node       节点
     * @param expression 相对于node的路径
     * @return String
     */
    public String getString(Object node, String expression) {
        return (String) evalXpath(expression, node, XPathConstants.STRING);
    }

    /**
     * 获取
     *
     * @param node       节点
     * @param expression 相对于node的路径
     * @return String
     */
    public Boolean getBoolean(Object node, String expression) {
        return (Boolean) evalXpath(expression, node, XPathConstants.BOOLEAN);
    }

    /**
     * 获取
     *
     * @param node       节点
     * @param expression 相对于node的路径
     * @return {Number}
     */
    public Number getNumber(Object node, String expression) {
        return (Number) evalXpath(expression, node, XPathConstants.NUMBER);
    }

    /**
     * 获取某个节点
     *
     * @param node       节点
     * @param expression 路径
     * @return {Node}
     */
    public Node getNode(Object node, String expression) {
        return (Node) evalXpath(expression, node, XPathConstants.NODE);
    }

    /**
     * 获取子节点
     *
     * @param node       节点
     * @param expression 相对于node的路径
     * @return NodeList
     */
    public NodeList getNodeList(Object node, String expression) {
        return (NodeList) evalXpath(expression, node, XPathConstants.NODESET);
    }

    /**
     * 针对没有嵌套节点的简单处理
     *
     * @return map集合
     */
    public Map<String, String> toMap() {
        Element root = doc.getDocumentElement();

        // 将节点封装成map形式
        NodeList list = root.getChildNodes();
        Map<String, String> params = new HashMap<String, String>(list.getLength());
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            params.put(node.getNodeName(), node.getTextContent());
        }
        // 含有空白符会生成一个#text参数
        params.remove("#text");
        return params;
    }

    /**
     * 内部类单例
     */
    private static class XmlHelperHolder {
        private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        private static XPathFactory xPathFactory = XPathFactory.newInstance();
    }

}
