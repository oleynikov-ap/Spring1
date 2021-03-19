package ru.oleynikovap.repository;

import ru.oleynikovap.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private SessionFactory factory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createProduct(String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products p = new Products(title, cost);
            session.save(p);
            session.getTransaction().commit();
        }
    }

    public Products getProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products p = session.get(Products.class, id);
            session.getTransaction().commit();
            return p;
        }
    }

    public List<Products> getAllProducts() {
        List<Products> list;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            list = session.createQuery("from Products").getResultList();
            session.getTransaction().commit();
        }
        return list;
    }

    public void updateProduct(long id, String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products p = session.get(Products.class, id);
            p.setTitle(title);
            p.setCost(cost);
            session.getTransaction().commit();
        }
    }

    public  void deleteProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products p = session.get(Products.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }

    public List<Orders> getProductOrderListById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products p = session.get(Products.class, id);
            List<OrderDetails> odl = p.getOrderDetails();
            List<Orders> ol = new ArrayList<>();
            for (OrderDetails od : odl) {
                if (ol.contains(od.getOrders())) continue;
                ol.add(od.getOrders());
            }
            session.getTransaction().commit();
            return ol;
        }
    }

    public List<Persons> getProductCustomerListById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products p = session.get(Products.class, id);
            List<OrderDetails> odl = p.getOrderDetails();
            List<Persons> cl = new ArrayList<>();
            for (OrderDetails od : odl) {
                if (cl.contains(od.getOrders().getPersons())) continue;
                cl.add(od.getOrders().getPersons());
            }
            session.getTransaction().commit();
            return cl;
        }
    }
}