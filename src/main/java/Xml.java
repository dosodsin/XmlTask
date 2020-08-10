import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.IOException;

import static Transformation.CsvTransformation.doTransformXlsToCsv;
import static Transformation.XmlBuilder.buildDocument;
import static Transformation.XmlBuilder.documentToXml;
import static Transformation.XsltTransformation.transformXmlFile;


public class Xml {

    static String firstXml = "src/main/outputFiles/test.xml";
    static String xsltFile = "src/main/transformationFiles/xmlTransformation.xslt";
    static String secondXml = "src/main/outputFiles/xmlAfterXslt.xml";
    static String csvTransform = "src/main/transformationFiles/csvTransformation.xslt";
    static String csvFile = "src/main/outputFiles/csvAfterXslt.csv";


    public static void main(String[] args) throws TransformerException, ParserConfigurationException, SAXException, IOException {
        Document document = buildDocument();
        documentToXml(document, firstXml);
        transformXmlFile(firstXml, xsltFile, secondXml);
        doTransformXlsToCsv(secondXml, csvFile, csvTransform);
    }
}
