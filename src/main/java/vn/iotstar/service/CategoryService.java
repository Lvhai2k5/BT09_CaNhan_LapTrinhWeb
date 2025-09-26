package vn.iotstar.service;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoryService {
  private final CategoryRepository repo;
  public CategoryService(CategoryRepository repo){ this.repo = repo; }

  public List<Category> findAll(){ return repo.findAll(); }
  public Optional<Category> findById(Long id){ return repo.findById(id); }
  public Category save(Category c){ return repo.save(c); }
  public void delete(Long id){ repo.deleteById(id); }
}
