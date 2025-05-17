package sqlfluent.demo.service;


import org.springframework.stereotype.Service;

import sqlfluent.demo.model.Order;
import sqlfluent.demo.repo.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<sqlfluent.demo.model.Order> getAll() { return repo.findAll(); }
    public Order save(Order o) { return repo.save(o); }
}