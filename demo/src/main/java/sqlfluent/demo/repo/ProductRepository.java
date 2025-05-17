package sqlfluent.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import sqlfluent.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
