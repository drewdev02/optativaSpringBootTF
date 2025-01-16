package org.adrewdev.optativatf.service;

import org.adrewdev.optativatf.entity.BookEntity;
import org.adrewdev.optativatf.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

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
        List<BookEntity> books = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(books);

        List<BookEntity> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
    }

    @Test
    void saveBook() {
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        BookEntity result = bookService.saveBook(book);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        assertEquals("Test Author", result.getAuthor());
    }

    @Test
    void updateBook() {
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        var result = bookService.updateBook(1L, book);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        assertEquals("Test Author", result.getAuthor());
    }

    @Test
    void deleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllBooksFromUser() {
        List<BookEntity> books = Arrays.asList(book);
        when(bookRepository.findByCustomerId(1L)).thenReturn(books);

        List<BookEntity> result = bookService.getAllBooksFromUser(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
    }

    @Test
    void getBooksByCategory() {
        List<BookEntity> books = Arrays.asList(book);
        when(bookRepository.findByCategoryId(1L)).thenReturn(books);

        List<BookEntity> result = bookService.getBooksByCategory(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
    }

    @Test
    void getBooksByAuthor() {
        List<BookEntity> books = Arrays.asList(book);
        when(bookRepository.findByAuthor("Test Author")).thenReturn(Optional.of(book));

        var result = bookService.getBooksByAuthor("Test Author");

        assertNotNull(result);
        assertEquals(true, result.isPresent());
        assertEquals("Test Book", result.get().getTitle());
    }
}
