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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persons_id")
    private Persons persons;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails;

    public String toString() {
        return String.format("Order [id = %d, details = %s]", id, persons);
    }
}
