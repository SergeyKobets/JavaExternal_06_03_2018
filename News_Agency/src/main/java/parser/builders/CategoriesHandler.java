package parser.builders;

import bean.Category;
import bean.News;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import parser.CategoryEnum;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CategoriesHandler extends DefaultHandler {
    private List<Category> categories;
    List<News> listNews = new ArrayList<>();
    private Category current = null;
    private News currentNews = null;
    private CategoryEnum currentEnum = null;
    private EnumSet<CategoryEnum> withText;

    public CategoriesHandler() {
        categories = new ArrayList<>();
        withText = EnumSet.range(CategoryEnum.CATEGORIES, CategoryEnum.NEWS);
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("categories".equals(localName)) {
            return;
        }

        if ("category".equals(localName)) {
            current = new Category();
            current.setId(Integer.parseInt(attributes.getValue("id")));
            current.setName(attributes.getValue("name"));
        } else {
            currentNews = new News();
            currentNews.setId(Integer.parseInt(attributes.getValue("id")));
            CategoryEnum temp = CategoryEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("category".equals(localName)) {
            current.setNewsList(listNews);
            categories.add(current);
            listNews = new ArrayList<>();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NEWS:
                    currentNews.setContext(s);
                    listNews.add(currentNews);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
