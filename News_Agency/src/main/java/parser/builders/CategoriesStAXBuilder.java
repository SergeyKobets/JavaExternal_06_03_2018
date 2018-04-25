package parser.builders;

import bean.Category;
import bean.News;
import parser.CategoryEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesStAXBuilder extends AbstractCategoriesBuilder {
    private List<Category> categories = new ArrayList<>();
    private XMLInputFactory inputFactory;

    public CategoriesStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public CategoriesStAXBuilder(List<Category> categories) {
        super(categories);

        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void buildSetCategories(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String category;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream); // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    category = reader.getLocalName();
                    if (CategoryEnum.valueOf(category.toUpperCase()) == CategoryEnum.CATEGORY) {
                        Category st = buildCategory(reader);
                        categories.add(st);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    public Category buildCategory(XMLStreamReader reader) throws XMLStreamException {
        Category category = new Category();
        List<News> newsList = new ArrayList<>();
        String categoryID = reader.getAttributeValue(null, CategoryEnum.ID.getValue());
        category.setId(Integer.parseInt(categoryID));
        category.setName(reader.getAttributeValue(null, CategoryEnum.NAME.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CategoryEnum.valueOf(name.toUpperCase())) {
                        case NEWS:
                            News news = new News();
                            String newsID = reader.getAttributeValue(null, CategoryEnum.ID.getValue());
                            news.setId(Integer.parseInt(newsID));
                            news.setContext(getXMLText(reader));
                            newsList.add(news);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CategoryEnum.valueOf(name.toUpperCase()) == CategoryEnum.CATEGORY) {
                        category.setNewsList(newsList);
                        return category;
                    }
                    break;
            }
        }

        throw new XMLStreamException("Unknown element in tag category");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;

    }
}
