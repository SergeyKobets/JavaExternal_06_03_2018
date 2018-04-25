package parser.builders;

import bean.Category;
import bean.News;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDOMBuilder extends AbstractCategoriesBuilder {
    private List<Category> categories;
    private DocumentBuilder docBuilder;

    public CategoriesDOMBuilder() {
        this.categories = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public CategoriesDOMBuilder(List<Category> categories) {
        super(categories);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void buildSetCategories(String fileName) {
        Document doc = null;
        try {
//parsingXML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
// получение списка дочерних элементов
            NodeList nodeList = root.getElementsByTagName("category");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                Category category = buildCategory(element);
                categories.add(category);
            }


        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }


    public static Category buildCategory(Element categoryElement) {
        Category category = new Category();
        List<News> newsList = new ArrayList<>();
        category.setId(Integer.parseInt(categoryElement.getAttribute("id")));
        category.setName(categoryElement.getAttribute("name"));

        if (categoryElement.hasChildNodes()) {
            NodeList nodeList = categoryElement.getElementsByTagName("news");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                News news = new News();
                news.setId(Integer.parseInt(element.getAttribute("id")));
                news.setContext(element.getTextContent());
                newsList.add(news);
            }
            category.setNewsList(newsList);
        }

        return category;
    }
}
