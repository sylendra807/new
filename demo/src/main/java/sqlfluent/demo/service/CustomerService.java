package sqlfluent.demo.service;
import org.springframework.stereotype.Service;

import sqlfluent.demo.model.Customer;
import sqlfluent.demo.repo.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> getAll() { return repo.findAll(); }
    public Customer save(Customer c) { return repo.save(c); }
}