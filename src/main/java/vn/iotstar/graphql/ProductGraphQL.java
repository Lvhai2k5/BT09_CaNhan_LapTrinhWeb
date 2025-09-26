package vn.iotstar.graphql;

import vn.iotstar.entity.*;
import vn.iotstar.repository.*;
import vn.iotstar.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.*;

import java.util.List;

@Controller
public class ProductGraphQL {
  private final ProductService productService;
  private final UserRepository userRepo;
  private final CategoryRepository categoryRepo;

  public ProductGraphQL(ProductService productService, UserRepository userRepo, CategoryRepository categoryRepo){
    this.productService = productService; this.userRepo = userRepo; this.categoryRepo = categoryRepo;
  }

  @QueryMapping public List<Product> products(){ return productService.findAll(); }
  @QueryMapping public List<Product> productsSortedByPrice(){ return productService.findAllOrderByPriceAsc(); }
  @QueryMapping public List<Product> productsByCategory(@Argument Long categoryId){
    return productService.findByCategoryId(categoryId);
  }

  @MutationMapping
  public Product createProduct(@Argument String title, @Argument Integer quantity,
                               @Argument String description, @Argument Double price,
                               @Argument Long ownerId, @Argument Long categoryId){
    Product p = new Product();
    p.setTitle(title); p.setQuantity(quantity); p.setDescription(description); p.setPrice(price);
    if(ownerId!=null) userRepo.findById(ownerId).ifPresent(p::setOwner);
    if(categoryId!=null) categoryRepo.findById(categoryId).ifPresent(p::setCategory);
    return productService.save(p);
  }

  @MutationMapping
  public Product updateProduct(@Argument Long id, @Argument String title, @Argument Integer quantity,
                               @Argument String description, @Argument Double price,
                               @Argument Long ownerId, @Argument Long categoryId){
    Product p = productService.findById(id).orElse(null); if(p==null) return null;
    if(title!=null) p.setTitle(title);
    if(quantity!=null) p.setQuantity(quantity);
    if(description!=null) p.setDescription(description);
    if(price!=null) p.setPrice(price);
    if(ownerId!=null) userRepo.findById(ownerId).ifPresent(p::setOwner);
    if(categoryId!=null) categoryRepo.findById(categoryId).ifPresent(p::setCategory);
    return productService.save(p);
  }

  @MutationMapping(name="deleteProductGraphql")
  public Boolean deleteProductGraphql(@Argument Long id){ productService.delete(id); return true; }
}
