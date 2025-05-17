package sqlfluent.demo.service;


import org.springframework.stereotype.Service;

import sqlfluent.demo.model.Product;
import sqlfluent.demo.repo.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final sqlfluent.demo.repo.ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() { return repo.findAll(); }
    public Product save(Product p) { return repo.save(p); }
}
