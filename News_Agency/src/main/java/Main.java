import bean.Category;
import controller.Controller;
import model.MainModel;
import model.Model;
import parser.CreateDocument;
import parser.factory.CategoryBuilderFactory;
import view.CategoriesView;
import view.NewsView;
import view.XMLView;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Model model = new MainModel();
        CategoriesView categoriesView = new CategoriesView();
        XMLView xmlView = new XMLView();
        NewsView newsView = new NewsView();
        Controller controller = new Controller();

        categoriesView.setController(controller);
        newsView.setController(controller);
        xmlView.setController(controller);

        controller.setModel(model);
        controller.setCategoriesView(categoriesView);
        controller.setNewsView(newsView);
        controller.setXmlView(xmlView);
//
//
        categoriesView.fireEventCategoryDeleted("Run");
 //       newsView.fireEventShowAllNews();


//        String type = "dom";
//        String filePath = "/Users/admin/Desktop/testXML.xml";
//        List<Category> categoriesListFromXml = CategoryBuilderFactory.parse(type, filePath);
//        //xmlView.fireEventCategoriesAdded(categoriesListFromXml);
//        xmlView.fireEventWriteNewsToXml("/Users/admin/Desktop/endTest.xml");


    }
}
