package org.adrewdev.optativatf.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adrewdev.optativatf.entity.BookEntity;
import org.adrewdev.optativatf.entity.CategoryEntity;
import org.adrewdev.optativatf.entity.CustomerEntity;
import org.adrewdev.optativatf.entity.OrderEntity;
import org.adrewdev.optativatf.repository.BookRepository;
import org.adrewdev.optativatf.repository.CategoryRepository;
import org.adrewdev.optativatf.repository.CustomerRepository;
import org.adrewdev.optativatf.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            log.info("Creating data...");
            // Create categories
            CategoryEntity category1 = new CategoryEntity();
            category1.setName("Fiction");
            CategoryEntity category2 = new CategoryEntity();
            category2.setName("Science");

            categoryRepository.save(category1);
            categoryRepository.save(category2);

            // Create customers
            CustomerEntity customer1 = new CustomerEntity();
            customer1.setFirstName("Alice");
            customer1.setLastName("Smith");
            customer1.setEmail("alice@example.com");
            customer1.setCreatedAt(LocalDateTime.now());
            customer1.setUpdatedAt(LocalDateTime.now());

            CustomerEntity customer2 = new CustomerEntity();
            customer2.setFirstName("Bob");
            customer2.setLastName("Jones");
            customer2.setEmail("bob@example.com");
            customer2.setCreatedAt(LocalDateTime.now());
            customer2.setUpdatedAt(LocalDateTime.now());

            customerRepository.save(customer1);
            customerRepository.save(customer2);

            // Create books
            BookEntity book1 = new BookEntity();
            book1.setTitle("The Great Adventure");
            book1.setAuthor("John Doe");
            book1.setPublishedDate(LocalDateTime.now().toLocalDate());
            book1.setCategory(category1);
            book1.setCustomer(customer1); // Assuming the book belongs to customer1 for testing

            BookEntity book2 = new BookEntity();
            book2.setTitle("Understanding Physics");
            book2.setAuthor("Jane Smith");
            book2.setPublishedDate(LocalDateTime.now().toLocalDate());
            book2.setCategory(category2);
            book2.setCustomer(customer2); // Assuming the book belongs to customer2 for testing

            bookRepository.save(book1);
            bookRepository.save(book2);

            // Create orders
            OrderEntity order1 = new OrderEntity();
            order1.setCustomer(customer1);
            order1.setOrderDate(LocalDateTime.now());
            order1.setTotalAmount(20.99 * 2);  // Price * quantity for the book1
            order1.setBooks(List.of(book1)); // Add books to the order

            OrderEntity order2 = new OrderEntity();
            order2.setCustomer(customer2);
            order2.setOrderDate(LocalDateTime.now());
            order2.setTotalAmount(25.49 * 1);  // Price * quantity for the book2
            order2.setBooks(List.of(book2)); // Add books to the order

            orderRepository.save(order1);
            orderRepository.save(order2);
        };
    }
}
