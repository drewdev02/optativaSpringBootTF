package org.adrewdev.optativatf.controller;

import org.adrewdev.optativatf.entity.BookEntity;
import org.adrewdev.optativatf.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookEntity book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new BookEntity();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
    }

    @Test
    void getAllBooks() {
        var books = Arrays.asList(book);
        when(bookService.getAllBooks()).thenReturn(books);

        var response = bookController.getAllBooks();
        var body = (List<BookEntity>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, body.size());
        assertEquals("Test Book", body.get(0).getTitle());
    }

    @Test
    void createBook() {
        when(bookService.saveBook(any(BookEntity.class))).thenReturn(book);

        var response = bookController.createBook(book);
        var body = (BookEntity) response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Book", body.getTitle());
    }

    @Test
    void updateBook() {
        when(bookService.updateBook(1L, book)).thenReturn(book);

        var response = bookController.updateBook(1L, book);
        var body = (BookEntity) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Book", body.getTitle());
    }

    @Test
    void deleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        var response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    void getBooksByCustomer() {
        var books = Arrays.asList(book);
        when(bookService.getAllBooksFromUser(1L)).thenReturn(books);

        var response = bookController.getBooksByCustomer(1L);
        var body = (List<BookEntity>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, body.size());
    }

    @Test
    void getBooksByCategory() {
        var books = Arrays.asList(book);
        when(bookService.getBooksByCategory(1L)).thenReturn(books);

        var response = bookController.getBooksByCategory(1L);
        var body = (List<BookEntity>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, body.size());
    }

    @Test
    void getBooksByAuthor() {
        var books = Arrays.asList(book);
        when(bookService.getBooksByAuthor("Test Author")).thenReturn(Optional.of(book));

        var response = bookController.getBooksByAuthor("Test Author");
        var body = (Optional<BookEntity>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, body.isPresent());
    }
}
