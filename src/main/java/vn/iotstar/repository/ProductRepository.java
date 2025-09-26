package vn.iotstar.repository;

import vn.iotstar.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // 1) Giá tăng dần
  List<Product> findAllByOrderByPriceAsc();

  // 2) Tất cả product của 1 category
  List<Product> findByCategoryId(Long categoryId);
}
