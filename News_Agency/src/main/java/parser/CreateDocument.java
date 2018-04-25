package parser;

import bean.Category;
import bean.News;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateDocument {
    public static void createXml(String fileName, List<Category> categoriesList) {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = documentBuilder.newDocument();
        String root = "categories";
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);
        for (int i = 0; i < categoriesList.size(); i++) {
            Category category = categoriesList.get(i);
            String elementCategory = "category";
            Element element = document.createElement(elementCategory);
            element.setAttribute("id", String.valueOf(category.getId()));
            element.setAttribute("name", category.getName());


            List<News> newsList = category.getNewsList();
            for (int j = 0; j < newsList.size(); j++) {
                News currentNews = newsList.get(j);
                String news = "news";
                Element elementNews = document.createElement(news);
                String content = currentNews.getContext();
                elementNews.appendChild(document.createTextNode(content));
                elementNews.setAttribute("id", String.valueOf(currentNews.getId()));
                rootElement.appendChild(element);
                element.appendChild(elementNews);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            try {
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new FileWriter(fileName));
                transformer.transform(source, result);
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
