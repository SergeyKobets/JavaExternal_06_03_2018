package bean;

//import org.apache.log4j.Logger;

public class News {

   // final static Logger logger = Logger.getLogger(News.class);

    private int id;
    private String context;

    public News() {
       // logger.info("Create news without param");
    }

    public News(String context) {
        this.context = context;
    }

    public News(int id, String context) {
        //logger.info("Create news");
        this.id = id;
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "News: " +
                "id=" + id +
                ", context=" + context;
    }
}
