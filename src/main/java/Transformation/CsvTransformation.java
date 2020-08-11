package Transformation;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class CsvTransformation {

    static String secondXml = "src/main/resources/config/xmlAfterXslt.xml";
    static String csvTransform = "src/main/resources/xslt/csvTransformation.xslt";
    static String csvFile = "src/main/resources/config/csvAfterXslt.csv";

    public static void doTransformXlsToCsv() throws TransformerException, IOException, ParserConfigurationException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(secondXml);
        StreamSource stylesource = new StreamSource(csvTransform);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(csvFile));
        transformer.transform(source, outputTarget);

    }
}
