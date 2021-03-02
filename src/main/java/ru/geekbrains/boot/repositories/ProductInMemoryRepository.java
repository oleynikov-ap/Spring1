package ru.geekbrains.boot.repositories;

import ru.geekbrains.boot.exceptions.ResourceAlreadyExistException;
import ru.geekbrains.boot.model.Product;
import org.springframework.stereotype.Component;
import ru.geekbrains.boot.model.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        if(this.isFind(product.getId()) != -1) {
            throw new ResourceAlreadyExistException("Продукт с указанным id уже существует");
        }
        products.add(product);
    }

    public int isFind(Long id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) return i;
        }
        return -1;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(s -> s.getId().equals(id)).findAny();
    }

    public boolean update(Long id, String title, int cost){
        int i = this.isFind(id);
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
        int i = this.isFind(id);
        if (i >= 0){
            return products.remove(i) != null;
        }
        return false;
    }

    public boolean delete(Product product){
        return this.delete(product.getId());
    }
}
