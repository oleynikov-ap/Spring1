package ru.geekbrains.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private int cost;

    @Override
    public String toString() {
        return String.format("Product: [%d %s %d]", id, title, cost);
    }

}
