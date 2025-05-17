package sqlfluent.demo.controller;
import org.springframework.web.bind.annotation.*;

import sqlfluent.demo.model.Product;
import sqlfluent.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() { return service.getAll(); }

    @PostMapping
    public Product save(@RequestBody Product p) { return service.save(p); }
}