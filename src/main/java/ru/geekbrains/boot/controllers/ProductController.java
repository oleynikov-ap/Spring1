package ru.geekbrains.boot.controllers;

import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product_page")
    public String showProductPage(Model model) {
        List<Product> list = productService.getProductRepository().getProducts();
        model.addAttribute("product", list);
        return "product";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.getProductRepository().delete(id);
        return "redirect:/product_page";
    }

    @PostMapping("/add_product")
    public String addNewProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int cost) {
        Product product = new Product(id, title, cost);
        productService.getProductRepository().add(product);
        return "redirect:/product_page";
    }

}
