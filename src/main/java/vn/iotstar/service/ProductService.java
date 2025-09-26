package vn.iotstar.service;

import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
  private final ProductRepository repo;
  public ProductService(ProductRepository repo){ this.repo = repo; }

  public List<Product> findAll(){ return repo.findAll(); }
  public Optional<Product> findById(Long id){ return repo.findById(id); }
  public Product save(Product p){ return repo.save(p); }
  public void delete(Long id){ repo.deleteById(id); }

  public List<Product> findAllOrderByPriceAsc(){ return repo.findAllByOrderByPriceAsc(); }
  public List<Product> findByCategoryId(Long categoryId){ return repo.findByCategoryId(categoryId); }
}
