package com.company.Summative2MahoneyScott.dao;

import com.company.Summative2MahoneyScott.dto.Author;

import java.util.List;

public interface AuthorInventoryDao {
    Author getAuthor(int id);

    List<Author> getAllAuthors();

    Author addAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(int id);

}
