package vn.iotstar.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import vn.iotstar.dto.*;
import vn.iotstar.entity.AppUser;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.UserService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MutationResolver {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public MutationResolver(UserService userService,
                            CategoryService categoryService,
                            ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    // ---------------- USER CRUD ----------------

    @MutationMapping
    public AppUser createUser(@Argument CreateUserInput input) {
        AppUser u = new AppUser();
        u.setFullname(input.getFullname());
        u.setEmail(input.getEmail());
        u.setPassword(input.getPassword());
        u.setPhone(input.getPhone());
        return userService.save(u);
    }

    @MutationMapping
    public AppUser updateUser(@Argument UpdateUserInput input) {
        return userService.findById(input.getId())
                .map(u -> {
                    if (input.getFullname() != null) u.setFullname(input.getFullname());
                    if (input.getEmail() != null) u.setEmail(input.getEmail());
                    if (input.getPassword() != null) u.setPassword(input.getPassword());
                    if (input.getPhone() != null) u.setPhone(input.getPhone());
                    return userService.save(u);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + input.getId()));
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userService.deleteById(id);
        return true;
    }

    // Batch: thêm nhiều user một lúc (tuỳ chọn)
    @MutationMapping
    public List<AppUser> createUsers(@Argument List<CreateUserInput> inputs) {
        return inputs.stream().map(in -> {
            AppUser u = new AppUser();
            u.setFullname(in.getFullname());
            u.setEmail(in.getEmail());
            u.setPassword(in.getPassword());
            u.setPhone(in.getPhone());
            return userService.save(u);
        }).collect(Collectors.toList());
    }

    // -------------- CATEGORY CRUD --------------

    @MutationMapping
    public Category createCategory(@Argument CreateCategoryInput input) {
        Category c = new Category();
        c.setName(input.getName());
        c.setImages(input.getImages());
        return categoryService.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument UpdateCategoryInput input) {
        return categoryService.findById(input.getId())
                .map(c -> {
                    if (input.getName() != null) c.setName(input.getName());
                    if (input.getImages() != null) c.setImages(input.getImages());
                    return categoryService.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + input.getId()));
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        categoryService.deleteById(id);
        return true;
    }

    // -------------- PRODUCT CRUD ---------------

    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        Product p = new Product();
        p.setTitle(input.getTitle());
        p.setQuantity(input.getQuantity() != null ? input.getQuantity() : 0);
        p.setDescription(input.getDesc());
        p.setPrice(input.getPrice() != null ? input.getPrice() : BigDecimal.ZERO);

        if (input.getUserId() != null) {
            userService.findById(input.getUserId()).ifPresent(p::setUser);
        }
        if (input.getCategoryIds() != null && !input.getCategoryIds().isEmpty()) {
            var selected = categoryService.findAll().stream()
                    .filter(c -> input.getCategoryIds().contains(c.getId()))
                    .collect(Collectors.toSet());
            p.setCategories(new HashSet<>(selected));
        }
        return productService.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument UpdateProductInput input) {
        return productService.findById(input.getId())
                .map(p -> {
                    if (input.getTitle() != null) p.setTitle(input.getTitle());
                    if (input.getQuantity() != null) p.setQuantity(input.getQuantity());
                    if (input.getDesc() != null) p.setDescription(input.getDesc());
                    if (input.getPrice() != null) p.setPrice(input.getPrice());

                    if (input.getUserId() != null) {
                        userService.findById(input.getUserId()).ifPresent(p::setUser);
                    }
                    if (input.getCategoryIds() != null) {
                        var selected = categoryService.findAll().stream()
                                .filter(c -> input.getCategoryIds().contains(c.getId()))
                                .collect(Collectors.toSet());
                        p.setCategories(new HashSet<>(selected));
                    }
                    return productService.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + input.getId()));
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        productService.deleteById(id);
        return true;
    }

    // Batch: thêm nhiều product (tuỳ chọn)
    @MutationMapping
    public List<Product> createProducts(@Argument List<CreateProductInput> inputs) {
        return inputs.stream().map(in -> {
            Product p = new Product();
            p.setTitle(in.getTitle());
            p.setQuantity(in.getQuantity() != null ? in.getQuantity() : 0);
            p.setDescription(in.getDesc());
            p.setPrice(in.getPrice() != null ? in.getPrice() : BigDecimal.ZERO);
            if (in.getUserId() != null) {
                userService.findById(in.getUserId()).ifPresent(p::setUser);
            }
            if (in.getCategoryIds() != null && !in.getCategoryIds().isEmpty()) {
                var selected = categoryService.findAll().stream()
                        .filter(c -> in.getCategoryIds().contains(c.getId()))
                        .collect(Collectors.toSet());
                p.setCategories(new HashSet<>(selected));
            }
            return productService.save(p);
        }).collect(Collectors.toList());
    }
}
