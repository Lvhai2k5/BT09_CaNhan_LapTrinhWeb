package vn.iotstar.service;

import org.springframework.stereotype.Service;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    // Lấy tất cả product theo giá tăng dần
    public List<Product> findAllOrderByPriceAsc() {
        return productRepo.findAllByOrderByPriceAsc();
    }

    // Lấy tất cả product theo category
    public List<Product> findByCategory(Long categoryId) {
        return productRepo.findByCategories_Id(categoryId);
    }
}
