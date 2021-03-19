package ru.oleynikovap.repository;

import ru.oleynikovap.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {
    private SessionFactory factory;

    @Autowired
    public PersonRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createPerson(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Persons c = new Persons(name);
            session.save(c);
            session.getTransaction().commit();
        }
    }

    public Persons getPerson(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Persons c = session.get(Persons.class, id);
            List<Orders> ol = c.getOrders();
            session.getTransaction().commit();
            return c;
        }
    }

    public List<Orders> getPersonOrdersById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Persons c = session.get(Persons.class, id);
            List<Orders> ol = new ArrayList<>();
            ol.addAll(c.getOrders());
            session.getTransaction().commit();
            return ol;
        }
    }

    public List<Products> getPersonProductListById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Persons c = session.get(Persons.class, id);
            List<Orders> ol = c.getOrders();
            List<Products> pl = new ArrayList<>();
            for (Orders o : ol) {
                for (OrderDetails od : o.getOrderDetails()) {
                    if (pl.contains(od.getProducts())) continue;
                    pl.add(od.getProducts());
                }
            }
            session.getTransaction().commit();
            return pl;
        }
    }

    public void updatePerson(long id, String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Persons c = session.get(Persons.class, id);
            c.setName(name);
            session.getTransaction().commit();
        }
    }

    public  void deletePerson(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Persons c = session.get(Persons.class, id);
            session.delete(c);
            session.getTransaction().commit();
        }
    }
}
