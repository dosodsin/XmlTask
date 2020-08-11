package Transformation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XsltTransformation {

    private static final Logger logger = LogManager.getLogger();

    static String firstXml = "src/main/resources/config/test.xml";
    static String xsltFile = "src/main/resources/xslt/xmlTransformation.xslt";
    static String secondXml = "src/main/resources/config/xmlAfterXslt.xml";

    public static void transformXmlFile() {

        TransformerFactory factory = TransformerFactory.newInstance();

        try {
            Transformer transformer = factory.newTransformer(new StreamSource(new File(xsltFile)));
            StreamSource source = new StreamSource(new File(firstXml));
            StreamResult result = new StreamResult(new File(secondXml));
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.transform(source, result);

        } catch (TransformerException ex) {
            logger.error("xml file didn't transform with xslt", ex.getMessage());
        }
    }
}
