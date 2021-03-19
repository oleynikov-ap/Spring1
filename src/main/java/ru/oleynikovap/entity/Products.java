package ru.oleynikovap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @OneToMany(mappedBy = "products")
    private List<OrderDetails> orderDetails;

    public Products(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public String toString() {
        return String.format("Product [id = %d, title = %s, cost = %d]", id, title, cost);
    }
}
