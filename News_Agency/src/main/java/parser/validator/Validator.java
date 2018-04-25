package parser.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator {
    public static boolean isValid(String filePathXML) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = filePathXML;
        String schemaName = "/Users/admin/My_Projects/IdeaProjects/playground/firstXML/src/main/resources/categoryValid.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
// создание схемы
            Schema schema = factory.newSchema(schemaLocation);
// создание валидатора на основе схемы
            javax.xml.validation.Validator validator = schema.newValidator();
// проверка документа
            Source source = new StreamSource(fileName);
            validator.validate(source);
            System.out.println(fileName + " is valid.");

            CategoriesErrorHandler sh = new CategoriesErrorHandler("application.log");
            validator.setErrorHandler(sh);
            validator.validate(source);
            System.out.println(fileName + " validating is ended.");
            return true;
        } catch (SAXException e) {
            System.err.print("validation " + fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            System.err.print(fileName + " is not valid because "
                    + e.getMessage());
        }
        return false;
    }
}
