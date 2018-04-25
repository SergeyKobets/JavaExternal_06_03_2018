package parser.factory;

import bean.Category;
import parser.builders.AbstractCategoriesBuilder;
import parser.builders.CategoriesDOMBuilder;
import parser.builders.CategoriesSAXBuilder;
import parser.builders.CategoriesStAXBuilder;
import parser.validator.Validator;

import java.io.File;
import java.util.List;

public class CategoryBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public static AbstractCategoriesBuilder createCategoryBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CategoriesDOMBuilder();
            case SAX:
                return new CategoriesSAXBuilder();
            case STAX:
                return new CategoriesStAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }

    public static List<Category> parse(String typeName, String filePath) {
        if (!Validator.isValid(filePath)) System.out.println("Wrong XML file " + new File(filePath).getName());

        AbstractCategoriesBuilder builder = createCategoryBuilder(typeName);
        builder.buildSetCategories(filePath);
        List<Category> categories = builder.getCategories();
        return categories;
    }

}
