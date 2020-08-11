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

    String secondXml;
    String csvTransform;
    String csvFile;

    public String getSecondXml() {
        return secondXml;
    }

    public void setSecondXml(String secondXml) {
        this.secondXml = secondXml;
    }

    public String getCsvTransform() {
        return csvTransform;
    }

    public void setCsvTransform(String csvTransform) {
        this.csvTransform = csvTransform;
    }

    public String getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public void doTransformXlsToCsv() throws TransformerException, IOException, ParserConfigurationException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(secondXml);
        StreamSource streamSource = new StreamSource(csvTransform);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(streamSource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(csvFile));
        transformer.transform(source, outputTarget);

    }
}
