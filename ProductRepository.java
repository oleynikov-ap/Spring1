package ru.geekbrains.spring.context.home;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();

    void add(Product k);
    String read(int index);
    boolean update(Long id, String title, int cost);
    boolean delete(Long id);
}
