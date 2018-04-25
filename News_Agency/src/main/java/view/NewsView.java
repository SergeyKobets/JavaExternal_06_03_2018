package view;

import bean.Category;
import bean.News;
import controller.Controller;
import model.ModelData;

import java.util.List;

public class NewsView implements View {
    private Controller controller;

    /**
     * Эмулирует событие клиента
     */
    public void fireEventShowAllNews() {
        controller.onShowAllNews();
    }

    public void fireEventNewsAdded(String content, String category) {
        controller.onNewsAdd(content, category);
    }

    public void fireEventNewsDeleted(int id) {
        controller.onNewsDelete(id);
    }

    public void fireEventNewsChanged(int id, String content) {
        controller.onNewsUpdate(id, content);
    }


    @Override
    public void refresh(ModelData modelData) {
        List<Category> categoryList = modelData.getCategories();

        for (Category category : categoryList) {
            System.out.println(category.getName().toUpperCase() + ":");
            for (News news : category.getNewsList()) {
                System.out.println("\t" + news);
            }
        }

        System.out.println("======================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
