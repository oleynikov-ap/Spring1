package ru.geekbrains.boot.services;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.boot.exceptions.ResourceNotFoundException;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductService {
    public ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) throw new ResourceNotFoundException(String.format("product %d not found", id));
        return product;
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {return productRepository;}

    public int totalProduct(){
        return productRepository.getProducts().size();
    }

    public int calculateAverageScore() {
        List<Product> products = productRepository.getProducts();
        if (products.size() == 0) {
            return 0;
        }
        int avg = 0;
        for (Product s : products) {
            avg += s.getCost();
        }
        return avg / products.size();
    }
}
