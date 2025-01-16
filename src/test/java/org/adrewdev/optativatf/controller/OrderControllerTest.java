package org.adrewdev.optativatf.controller;

import org.adrewdev.optativatf.entity.OrderEntity;
import org.adrewdev.optativatf.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderEntity order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new OrderEntity();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(100.0);
    }

    @Test
    void getAllOrders() {
        List<OrderEntity> orders = Arrays.asList(order);
        when(orderService.getAllOrders()).thenReturn(orders);

        var response = (ResponseEntity<List<OrderEntity>>) orderController.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(100.0, response.getBody().get(0).getTotalAmount());
    }

    @Test
    void createOrder() {
        when(orderService.saveOrder(any(OrderEntity.class))).thenReturn(order);

        var response = (ResponseEntity<OrderEntity>) orderController.createOrder(order);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100.0, response.getBody().getTotalAmount());
    }

    @Test
    void updateOrder() {
        when(orderService.updateOrder(1L, order)).thenReturn(order);

        var response = (ResponseEntity<OrderEntity>) orderController.updateOrder(1L, order);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100.0, response.getBody().getTotalAmount());
    }

    @Test
    void deleteOrder() {
        doNothing().when(orderService).deleteOrder(1L);

        var response = (ResponseEntity<Void>) orderController.deleteOrder(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    void getOrdersByCustomer() {
        List<OrderEntity> orders = Arrays.asList(order);
        when(orderService.getOrdersByCustomer(1L)).thenReturn(orders);

        var response = (ResponseEntity<List<OrderEntity>>) orderController.getOrdersByCustomer(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getOrdersWithinDateRange() {
        List<OrderEntity> orders = Arrays.asList(order);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        when(orderService.getOrdersWithinDateRange(startDate, endDate)).thenReturn(orders);

        var response = (ResponseEntity<List<OrderEntity>>) orderController.getOrdersWithinDateRange(startDate, endDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}
