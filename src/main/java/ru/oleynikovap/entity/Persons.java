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
@Table(name = "persons")
@ToString
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "persons")
    private List<Orders> orders;

    public Persons(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Customer [id = %d, name = %s]", id, name);
    }
}
