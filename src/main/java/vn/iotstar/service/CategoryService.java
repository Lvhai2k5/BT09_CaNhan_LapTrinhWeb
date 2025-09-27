package vn.iotstar.service;

import org.springframework.stereotype.Service;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

    public Category findByName(String name) {
        return categoryRepo.findByName(name);
    }
}
