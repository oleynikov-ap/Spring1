package ru.geekbrains.spring.context.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.geekbrains.spring.context")
public class AppConfig {
    @Autowired
    private ProductService productService;
}
