package view;

import bean.Category;
import controller.Controller;
import model.ModelData;

import java.util.List;

public class CategoriesView implements View {

    private Controller controller;

    /**
     * Эмулирует событие клиента
     */
    public void fireEventShowAllCategories() {
        controller.onShowAllCategories();
    }

    public void fireEventShowCategory(String name) {
        controller.onShowCategory(name);
    }

    public void fireEventCategoryDeleted(String name) {
        controller.onCategoryDelete(name);
    }

    public void fireEventCategoryAdded(String name) {
        controller.onCategoryAdd(name);
    }


    @Override
    public void refresh(ModelData modelData) {
        System.out.println("Categories:");
        List<Category> categoryList = modelData.getCategories();

        for (Category category : categoryList) {
            System.out.println("\t\t\t\t" + category.getName());
        }

        System.out.println("=======================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
