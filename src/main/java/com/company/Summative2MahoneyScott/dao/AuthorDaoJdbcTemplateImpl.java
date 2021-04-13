package com.company.Summative2MahoneyScott.dao;


import com.company.Summative2MahoneyScott.dao.AuthorInventoryDao;
import com.company.Summative2MahoneyScott.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJdbcTemplateImpl implements AuthorInventoryDao {
    // PREPARED STATEMENTS
    private static final String INSERT_AUTHOR_SQL =
           "Insert into author (first_name, last_name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_AUTHOR_SQL =
            "Select * From author where author_id = ?";
    private static final String SELECT_ALL_AUTHORS_SQL =
            "Select * From author";
    private static final String DELETE_AUTHOR_SQL =
            "Delete from author where author_id = ?";
    private static final String UPDATE_AUTHOR_SQL =
            "Update author set first_name = ?, last_name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where author_id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Author getAuthor(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowToAuthor, id);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    // GET ALL AUTHORS
    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SELECT_ALL_AUTHORS_SQL, this::mapRowToAuthor);
    }

    // ADD A AUTHOR
    @Transactional
    @Override
    public Author addAuthor(Author author) {
        jdbcTemplate.update(INSERT_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        author.setAuthorId(id);

        return author;
    }

    // UPDATE AUTHOR
    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail(),
                author.getAuthorId());
    }

    // DELETE BOOK
    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);
    }


    // ROW MAPPER
    private Author mapRowToAuthor(ResultSet rs, int rowNumber) throws SQLException {
        Author author = new Author();
        author.setAuthorId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostalCode(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));

        return author;
    }
}
