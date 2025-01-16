package org.adrewdev.optativatf.service;

import org.adrewdev.optativatf.entity.OrderEntity;
import org.adrewdev.optativatf.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity order;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new OrderEntity();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(100.0);

        startDate = LocalDateTime.now().minusDays(1);
        endDate = LocalDateTime.now().plusDays(1);
    }

    @Test
    void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        var result = orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void saveOrder() {
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(order);

        var result = orderService.saveOrder(order);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updateOrder() {
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(order);

        var result = orderService.updateOrder(1L, order);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void deleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void getOrdersByCustomer() {
        when(orderRepository.findByCustomerId(1L)).thenReturn(List.of(order));

        var result = orderService.getOrdersByCustomer(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getOrdersWithinDateRange() {
        when(orderRepository.findByOrderDateBetween(startDate, endDate)).thenReturn(List.of(order));

        var result = orderService.getOrdersWithinDateRange(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getOrderDate().isAfter(startDate));
        assertTrue(result.get(0).getOrderDate().isBefore(endDate));
    }
}
