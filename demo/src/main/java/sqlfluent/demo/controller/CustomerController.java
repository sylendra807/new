package sqlfluent.demo.controller;
import org.springframework.web.bind.annotation.*;

import sqlfluent.demo.model.Customer;
import sqlfluent.demo.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAll() { return service.getAll(); }

    @PostMapping
    public Customer save(@RequestBody Customer c) { return service.save(c); }
}