package parser;

public enum  CategoryEnum {
    CATEGORIES("categories"),
    CATEGORY("category"),
    NEWS("news"),
    NAME("name"),
    ID("id");

    private String value;

    CategoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
