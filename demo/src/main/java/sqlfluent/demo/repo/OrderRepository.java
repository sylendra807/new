package sqlfluent.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sqlfluent.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}