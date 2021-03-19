package ru.oleynikovap;

import ru.oleynikovap.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.oleynikovap.repository.PersonRepository;
import ru.oleynikovap.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class CrudApp {
    private static SessionFactory factory;
    private static ProductRepository productRepository;
    private static ConfigurableApplicationContext context;
    private static PersonRepository personRepository;

    public static void main(String[] args) {
        try {
            context = SpringApplication.run(CrudApp.class, args);

            productRepository = (ProductRepository) context.getBean("productRepository");
            List<Orders> orderList = productRepository.getProductOrderListById(2);
            System.out.println(orderList);
            System.out.println();
            personRepository = (PersonRepository) context.getBean("personRepository");
            orderList = personRepository.getPersonOrdersById(2L);
            System.out.println(orderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
