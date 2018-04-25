package bean;

//import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Category {

    //final static Logger logger = Logger.getLogger(Category.class);
    private List<News> newsList = new ArrayList<>();

    public void add(News news) {
        newsList.add(news);
    }

    public News get(int index) {
        return newsList.get(index);
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    private int id;
    private String name;

    public Category() {
        //logger.info("Create new category without param");
    }

    public Category(String name) {
       // logger.info("Create new category");
        this.name = name;
    }

    public Category(int id, String name) {
        //logger.info("Create new category");
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", newsList=" + newsList +
                '}';
    }
}
