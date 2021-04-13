package com.company.Summative2MahoneyScott.dao;


import com.company.Summative2MahoneyScott.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookInventoryDao {
    // PREPARED STATEMENTS
    private static final String INSERT_BOOK_SQL = "Insert into book (isbn, publish_date, author_id, title, publisher_id, price) values ( ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BOOK_SQL = "Select * From book where book_id = ?";
    private static final String SELECT_ALL_BOOKS_SQL = "Select * From book";
    private static final String DELETE_BOOK_SQL = "Delete from book where book_id = ?";
    private static final String UPDATE_BOOK_SQL =
            "Update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";
    private static final String SELECT_BOOKS_BY_AUTHOR_SQL = "Select * From book where book_id = ?";

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public BookDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Book getBook(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // GET ALL BOOKS
    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    // ADD A BOOK
    @Transactional
    @Override
    public Book addBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());



        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        book.setId(id);

        return book;
    }

    // UPDATE BOOK
    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getId());
    }

    // DELETE BOOK
    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }


    // GET ALL BY AUTHOR
    @Override
    public List<Book> getBooksByAuthor(int authorId) {
        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR_SQL, this::mapRowToBook, authorId);
    }


    // ROW MAPPER
    private Book mapRowToBook(ResultSet rs, int rowNumber) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setAuthorId(rs.getInt("author_id"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getString("price"));
        book.setIsbn(rs.getString("isbn"));

        return book;
    }
}
