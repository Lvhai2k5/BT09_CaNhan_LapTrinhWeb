package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Tìm tất cả product theo giá tăng dần
    List<Product> findAllByOrderByPriceAsc();

    // Tìm tất cả product theo category id
    List<Product> findByCategories_Id(Long categoryId);
}
