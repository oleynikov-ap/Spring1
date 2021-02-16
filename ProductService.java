package ru.geekbrains.spring.context.home;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    public ProductRepository productRepository;

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
