package com.company.Summative2MahoneyScott.dao;

import com.company.Summative2MahoneyScott.dto.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaoJdbcTemplateImpl implements PublisherInventoryDao {
    // PREPARED STATEMENTS
    private static final String INSERT_PUBLISHER_SQL =
            "Insert into publisher (name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PUBLISHER_SQL =
            "Select * From publisher where publisher_id = ?";
    private static final String SELECT_ALL_PUBLISHER_SQL =
            "Select * From publisher";
    private static final String DELETE_PUBLISHER_SQL =
            "Delete from publisher where publisher_id = ?";
    private static final String UPDATE_PUBLISHER_SQL =
            "Update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where publisher_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Publisher getPublisher(int id)  {
        try {
            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);
        }
        catch (EmptyResultDataAccessException e)  {
            return null;
        }
    }

    // GET ALL PUBLISHERS
    @Override
    public List<Publisher> getAllPublishers()  {
        return jdbcTemplate.query(SELECT_ALL_PUBLISHER_SQL, this::mapRowToPublisher);
    }

    // ADD A PUBLISHER
    @Transactional
    @Override
    public Publisher addPublisher(Publisher publisher) {
        jdbcTemplate.update(INSERT_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail());


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        publisher.setPublisherId(id);

        return publisher;
    }

    // UPDATE PUBLISHER
    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getPublisherId());
    }

    // DELETE BOOK
    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER_SQL, id);
    }

    // ROW MAPPER
    private Publisher mapRowToPublisher(ResultSet rs, int rowNumber) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setPublisherId(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostalCode(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));

        return publisher;
    }

}
