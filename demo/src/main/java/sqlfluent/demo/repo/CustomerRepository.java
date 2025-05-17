package sqlfluent.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sqlfluent.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
