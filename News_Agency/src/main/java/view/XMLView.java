package view;

import bean.Category;
import controller.Controller;
import model.ModelData;
import parser.CreateDocument;

import java.util.List;

public class XMLView implements View {

    private String fileNameForXml;

    private Controller controller;

    public void fireEventCategoriesAdded(List<Category> categoriesList) {
            controller.onCategoriesAddFromXml(categoriesList);
    }

    public void fireEventWriteNewsToXml(String fileNameForXml) {
        this.fileNameForXml = fileNameForXml;
        controller.onNewsWriteToXml();
    }

    @Override
    public void refresh(ModelData modelData) {
        List<Category> categoriesList = modelData.getCategories();
        CreateDocument.createXml(fileNameForXml, categoriesList);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
