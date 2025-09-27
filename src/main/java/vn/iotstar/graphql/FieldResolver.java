package vn.iotstar.graphql;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import vn.iotstar.entity.AppUser;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.service.ProductService;

import java.util.List;

@Controller
public class FieldResolver {

    private final ProductService productService;

    public FieldResolver(ProductService productService) {
        this.productService = productService;
    }

    // Lấy products của Category (nhiều-nhiều)
    @SchemaMapping(typeName = "Category", field = "products")
    public List<Product> products(Category category) {
        if (category.getId() == null) return List.of();
        return productService.findByCategory(category.getId());
    }

    // Lấy user của Product (nhiều-1)
    @SchemaMapping(typeName = "Product", field = "user")
    public AppUser user(Product product) {
        return product.getUser(); // JPA sẽ load khi cần (nếu fetch lazy cần @Transactional tại tầng service)
    }

    // Lấy categories của Product (nhiều-nhiều)
    @SchemaMapping(typeName = "Product", field = "categories")
    public List<Category> categories(Product product) {
        return product.getCategories().stream().toList();
    }
}
