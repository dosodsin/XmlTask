import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.IOException;

import static Transformation.CsvTransformation.doTransformXlsToCsv;
import static Transformation.XmlBuilder.buildDocument;
import static Transformation.XmlBuilder.documentToXml;
import static Transformation.XsltTransformation.transformXmlFile;


public class Main {

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, SAXException, IOException {
        buildDocument();
        documentToXml();
        transformXmlFile();
        doTransformXlsToCsv();
    }
}
