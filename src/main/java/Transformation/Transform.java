package Transformation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Transform {

    private static final Logger logger = LogManager.getLogger();

    Connection connection;
    CsvTransformation csvTransformation;
    XsltTransformation xsltTransformation;
    XmlBuilder xmlBuilder;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void transform() {

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select ID_ART,NAME,CODE,GUID,USERNAME from test");

            Document document = xmlBuilder.buildDocument(resultSet);

            xmlBuilder.documentToXml(document);
            xsltTransformation.transformXmlFile();
            csvTransformation.doTransformXlsToCsv();

        } catch (Exception ex) {
            logger.error("xml didn't create", ex.getMessage());
        }
    }
}
