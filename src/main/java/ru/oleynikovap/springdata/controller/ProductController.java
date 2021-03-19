package ru.oleynikovap.springdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oleynikovap.springdata.model.Product;
import ru.oleynikovap.springdata.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/cost")
    public List<Product> findByCost(@RequestParam Optional<Integer> min, @RequestParam Optional<Integer> max){
        return productService.findByCost(min, max);
    }

    @GetMapping("/title")
    public List<Product> findByTitlePart (@RequestParam Optional<String> partTitle) {
        return productService.findByTitlePart(partTitle);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
