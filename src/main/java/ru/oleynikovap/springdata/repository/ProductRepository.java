package ru.oleynikovap.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oleynikovap.springdata.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByTitle (String title);
    List<Product> findProductsByCostBetween (Integer min, Integer max);
    List<Product> findProductsByCostGreaterThanEqual (Integer min);
    List<Product> findProductsByCostIsLessThanEqual (Integer max);
    List<Product> getProductByTitleContainsIgnoreCase (String partTitle);
}
