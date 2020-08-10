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
    public static void doTransformXlsToCsv(String xmlFilePath, String csvFilePath, String xslTemplatePath) throws TransformerException, IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFilePath);
        StreamSource stylesource = new StreamSource(xslTemplatePath);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(csvFilePath));
        transformer.transform(source, outputTarget);
    }
}
