package Transformation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.*;

import static DB.SqlConnectionFactory.getConnection;

public class XmlBuilder {

    private static final Logger logger = LogManager.getLogger();

    static String[] elements = {"ID_ART", "NAME", "CODE", "GUID", "USERNAME"};
    static String firstXml = "src/main/outputFiles/test.xml";
    private static Document document;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Document buildDocument() {

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            Element articles = document.createElement("articles");
            document.appendChild(articles);

            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select ID_ART,NAME,CODE,GUID,USERNAME from test");

            while (resultSet.next()) {
                Element article = document.createElement("article");
                articles.appendChild(article);
                for (String element : elements) {
                    article.setAttribute(element, resultSet.getString(element));
                }
            }

            return document;

        } catch (SQLException ex) {
            logger.error("problem with DB", ex.getMessage());
        } catch (ParserConfigurationException e) {
            logger.error("Document didn't parse", e.getMessage());
        }
        return null;
    }

    public static void documentToXml() {
        DOMSource domSource = new DOMSource(document);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult streamResult = new StreamResult(new File(firstXml));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException ex) {
            logger.error("document didn't transform", ex.getMessage());
        }

    }
}
