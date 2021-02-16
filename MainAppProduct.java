package ru.geekbrains.spring.context.home;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.spring.context.AppConfig;

import java.util.Scanner;

public class MainAppProduct {
    public static void printMenu(){
        System.out.println("Выберите пункт меню");
        System.out.println("1 - Добавить товар");
        System.out.println("2 - Вывести данные товара");
        System.out.println("3 - Обновить данные товара");
        System.out.println("4 - Удалить товар");
        System.out.println("5 - Выход");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        System.out.println(productService.getProductRepository().getProducts());

        productService.getProductRepository().add(new Product(6L, "K", 100));
        System.out.println(productService.getProductRepository().getProducts());

        Scanner sc = new Scanner(System.in);
        String cmd;
        String[] words;
        int i;
        while (true){
            printMenu();
            cmd = sc.nextLine();
            if (cmd.equals("5")) break;
            switch (cmd){
                case "1":
                    System.out.println("введите товар через запятую: ID, TITLE, COST");
                    words = sc.nextLine().split(",");
                    productService.getProductRepository().add(new Product(
                            Long.valueOf(words[0]),
                            words[1],
                            Integer.valueOf(words[2])));
                    System.out.println(productService.getProductRepository().getProducts());
                    break;
                case "2":
                    System.out.println("Введите индекс товара от 1 до " + (productService.totalProduct()));
                    i = Integer.valueOf(sc.nextLine()) - 1;
                    System.out.println(productService.getProductRepository().read(i));
                    break;
                case "3":
                    System.out.println("Введите новые данные товара через запятую: ID, TITLE, COST");
                    words = sc.nextLine().split(",");
                    productService.getProductRepository().update(
                            Long.valueOf(words[0]),
                            words[1],
                            Integer.valueOf(words[2]));
                    System.out.println(productService.getProductRepository().getProducts());
                    break;
                case "4":
                    System.out.println("Введите ID товара: ");
                    productService.getProductRepository().delete(Long.valueOf(sc.nextLine()));
                    System.out.println(productService.getProductRepository().getProducts());
                    break;
                default:
                    System.out.println("Введите корректный пункт меню!");
            }
        }
        context.close();
    }
}
