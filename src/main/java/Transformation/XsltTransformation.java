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

    public static void transformXmlFile(String sourceFilePath, String xsltFilePath, String outFilePath) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(new File(xsltFilePath)));
            StreamSource source = new StreamSource(new File(sourceFilePath));
            StreamResult result = new StreamResult(new File(outFilePath));
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            logger.error("xml file didn't transform with xslt", ex.getMessage());
        }
    }
}
