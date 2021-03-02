package ru.geekbrains.boot.repositories;

import ru.geekbrains.boot.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProducts();

    void add(Product k);
    String read(int index);
    boolean update(Long id, String title, int cost);
    boolean delete(Long id);
    public Optional<Product> findById(Long id);
}
