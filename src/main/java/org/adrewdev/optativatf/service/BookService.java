package org.adrewdev.optativatf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adrewdev.optativatf.entity.BookEntity;
import org.adrewdev.optativatf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity saveBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public BookEntity updateBook(Long bookId, BookEntity book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<BookEntity> getAllBooksFromUser(Long customerId) {
        return bookRepository.findByCustomerId(customerId);
    }

    public List<BookEntity> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }


    public Optional<BookEntity> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
