package controller;

import model.Model;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import view.CategoriesView;
import view.NewsView;
import view.XMLView;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    final static Logger logger = Logger.getLogger(Controller.class);

    private Model model;
    private CategoriesView categoriesView;
    private NewsView newsView;
    private XMLView xmlView;

    public void setXmlView(XMLView xmlView) {
        this.xmlView = xmlView;
    }

    public void setNewsView(NewsView newsView) {
        this.newsView = newsView;
    }

    public void setCategoriesView(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllCategories() {
        model.loadCategories();
        categoriesView.refresh(model.getModelData());
    }

    public void onShowCategory(String name) {
        model.loadCategoryByName(name);
        newsView.refresh(model.getModelData());
    }

    public void onCategoryDelete(String name) {
        model.deleteCategory(name);
        //categoriesView.refresh(model.getModelData());
    }

    public void onCategoryAdd(String name) {
        model.addCategory(name);
        categoriesView.refresh(model.getModelData());
    }

    public void onShowAllNews() {
        model.loadNews();
        newsView.refresh(model.getModelData());
    }

    public void onNewsAdd(String content, String category) {
        model.addNews(content, category);
        newsView.refresh(model.getModelData());
    }

    public void onNewsDelete(int id) {
        model.deleteNews(id);
        //newsView.refresh(model.getModelData());
    }

    public void onNewsUpdate(int id, String content) {
        model.changeNewsData(id, content);
        //newsView.refresh(model.getModelData());
    }

    public void onCategoriesAddFromXml(List<bean.Category> categoriesList) {
        model.addCategoryFromXML(categoriesList);
    }

    public void onNewsWriteToXml() {
        model.loadNews();
        xmlView.refresh(model.getModelData());
    }

}
