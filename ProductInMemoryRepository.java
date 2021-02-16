package ru.geekbrains.spring.context.home;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductInMemoryRepository implements ProductRepository {
    private List<Product> products;

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Apple", 20));
        products.add(new Product(2L, "Orange", 80));
        products.add(new Product(3L, "Banana", 60));
        products.add(new Product(4L, "Sugar", 40));
        products.add(new Product(5L, "butter", 100));
    }

    public String read(int index) {
        return products.get(index).toString();
    }

    public void add(Product product) {
        if(product.getCost() < 0 || product.getCost() > 100) {
            throw new IllegalArgumentException("Student's score must be in interval [0, 100]");
        }
        products.add(product);
    }

    public int find(Long id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) return i;
        }
        return -1;
    }

    public boolean update(Long id, String title, int cost){
        int i = this.find(id);
        if (i >= 0) {
            products.get(i).setTitle(title);
            products.get(i).setCost(cost);
            return true;
        }
        return false;
    }

    public boolean update(Product product){
        return this.update(product.getId(), product.getTitle(), product.getCost());
    }

    public boolean delete(Long id){
        int i = this.find(id);
        if (i >= 0){
            return products.remove(i) != null;
        }
        return false;
    }

    public boolean delete(Product product){
        return this.delete(product.getId());
    }
}
