package vn.iotstar.graphql;

import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.*;

import java.util.List;

@Controller
public class CategoryGraphQL {
  private final CategoryService service;
  public CategoryGraphQL(CategoryService service){ this.service = service; }

  @QueryMapping public List<Category> categories(){ return service.findAll(); }
  @QueryMapping public Category categoryById(@Argument Long id){ return service.findById(id).orElse(null); }

  @MutationMapping public Category createCategory(@Argument String name, @Argument String images){
    Category c = new Category(); c.setName(name); c.setImages(images); return service.save(c);
  }
  @MutationMapping public Category updateCategory(@Argument Long id, @Argument String name, @Argument String images){
    Category c = service.findById(id).orElse(null); if(c==null) return null;
    c.setName(name); c.setImages(images); return service.save(c);
  }
  @MutationMapping(name="deleteCategoryGraphql")
  public Boolean deleteCategoryGraphql(@Argument Long id){ service.delete(id); return true; }
}
