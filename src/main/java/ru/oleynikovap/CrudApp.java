package ru.oleynikovap;

import ru.oleynikovap.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.oleynikovap.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class CrudApp {
    private static SessionFactory factory;
    private static ProductRepository productRepository;
    private static ConfigurableApplicationContext context;

//    public static void init() {
//        PrepareDataApp.forcePrepareData();
//        factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//    }
//
//    public static void shutdown() {
//        factory.close();
//    }

    public static void main(String[] args) {
        try {
//            init();
            context = SpringApplication.run(CrudApp.class, args);

            productRepository = (ProductRepository) context.getBean("productRepository");
            List<Products> pl = productRepository.getAllProducts();
            System.out.println(pl);
            productRepository.createProduct("coffee",100);
            System.out.println(productRepository.getProduct(9));
            productRepository.updateProduct(9,"Cacao", 70);
            System.out.println(productRepository.getProduct(9));
            productRepository.deleteProduct(8);
            System.out.println(productRepository.getAllProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            shutdown();
//        }
    }
}
