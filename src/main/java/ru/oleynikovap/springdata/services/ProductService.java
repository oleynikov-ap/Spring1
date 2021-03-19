package ru.oleynikovap.springdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oleynikovap.springdata.model.Product;
import ru.oleynikovap.springdata.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByCost (Optional<Integer> min, Optional<Integer> max) {
        if (min.isPresent() && max.isPresent()) {
            return productRepository.findProductsByCostBetween(min.get(), max.get());
        }
        if (!min.isPresent() && max.isPresent()) {
            return productRepository.findProductsByCostIsLessThanEqual(max.get());
        }
        if (min.isPresent() && !max.isPresent()) {
            return productRepository.findProductsByCostGreaterThanEqual(min.get());
        }
        return productRepository.findAll();
    }
    public List<Product> findByTitlePart (Optional<String> partTitle) {
        if (partTitle.isPresent()) {
            return productRepository.getProductByTitleContainsIgnoreCase(partTitle.get());
        }
        else return productRepository.findAll();
    }
}
