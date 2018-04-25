package parser.builders;

import bean.Category;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class CategoriesSAXBuilder extends AbstractCategoriesBuilder {
    private List<Category> categories;
    private CategoriesHandler categoriesHandler;
    private XMLReader reader;

    public CategoriesSAXBuilder() {
        categoriesHandler = new CategoriesHandler();

        try {
            reader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            System.out.println("Error parser");
        }

        reader.setContentHandler(categoriesHandler);
    }

    public CategoriesSAXBuilder(List<Category> categories) {
        super(categories);

        try {
            reader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            System.out.println("Error parser");
        }

        reader.setContentHandler(categoriesHandler);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void buildSetCategories(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        categories = categoriesHandler.getCategories();
    }
}
