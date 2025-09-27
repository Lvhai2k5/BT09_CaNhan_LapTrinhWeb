package vn.iotstar.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import vn.iotstar.entity.AppUser;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.UserService;

import java.util.List;

@Controller
public class QueryResolver {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public QueryResolver(UserService userService,
                         CategoryService categoryService,
                         ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    // ---- USER ----
    @QueryMapping
    public List<AppUser> users() {
        return userService.findAll();
    }

    @QueryMapping
    public AppUser userById(@Argument Long id) {
        return userService.findById(id).orElse(null);
    }

    // ---- CATEGORY ----
    @QueryMapping
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id) {
        return categoryService.findById(id).orElse(null);
    }

    // ---- PRODUCT ----
    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productService.findById(id).orElse(null);
    }

    // Yêu cầu 1: Hiển thị tất cả product có price từ thấp đến cao
    @QueryMapping
    public List<Product> productsOrderByPriceAsc() {
        return productService.findAllOrderByPriceAsc();
    }

    // Yêu cầu 2: Lấy tất cả product của 01 category
    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.findByCategory(categoryId);
    }
}
