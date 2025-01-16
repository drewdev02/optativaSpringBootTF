package org.adrewdev.optativatf.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adrewdev.optativatf.entity.OrderEntity;
import org.adrewdev.optativatf.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        log.info("Getting all orders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderEntity order) {
        log.info("Creating order: {}", order);
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody OrderEntity order) {
        log.info("Updating order with id: {}", orderId);
        return ResponseEntity.ok(orderService.updateOrder(orderId, order));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        log.info("Deleting order with id: {}", orderId);
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomer(@PathVariable Long customerId) {
        log.info("Getting orders for customer with id: {}", customerId);
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<?> getOrdersWithinDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        log.info("Getting orders within date range: {} - {}", startDate, endDate);
        return ResponseEntity.ok(orderService.getOrdersWithinDateRange(startDate, endDate));
    }
}
