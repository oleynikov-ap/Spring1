package ru.geekbrains.spring.context.home;

public class Product {
    private Long id;
    private String title;
    private int cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Product() {}

    public Product(Long id, String name, int score) {
        this.id = id;
        this.title = name;
        this.cost = score;
    }

    @Override
    public String toString() {
        return String.format("Product: [%d %s %d]", id, title, cost);
    }

}
