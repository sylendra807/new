package sqlfluent.demo.controller;

import org.springframework.web.bind.annotation.*;

import sqlfluent.demo.model.Order;
import sqlfluent.demo.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getAll() { return service.getAll(); }

    @PostMapping
    public Order save(@RequestBody Order o) { return service.save(o); }
}