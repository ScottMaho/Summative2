package com.company.Summative2MahoneyScott.dao;

import com.company.Summative2MahoneyScott.dto.Book;

import java.util.List;

public interface BookInventoryDao {
    Book getBook(int id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int id);

    List<Book> getBooksByAuthor(int authorId);
}
