package org.adrewdev.optativatf.repository;

import org.adrewdev.optativatf.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerId(Long customerId);

    List<OrderEntity> findByOrderDateBetween(LocalDateTime startDate, java.time.LocalDateTime endDate);
}
