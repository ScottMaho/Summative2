package com.company.Summative2MahoneyScott.dao;


import com.company.Summative2MahoneyScott.dto.Author;
import com.company.Summative2MahoneyScott.dto.Book;
import com.company.Summative2MahoneyScott.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherInventoryDaoTest {

    // SET UP DAO
    @Autowired
    PublisherInventoryDao publisherInventoryDao;

    @Autowired
    AuthorInventoryDao authorInventoryDao;

    @Autowired
    BookInventoryDao bookInventoryDao;

    @Before
    public void setUp() throws Exception {
        // CLEAN UP THE TEST DB

        // BOOK
        List<Book> bookList = bookInventoryDao.getAllBooks();
        for (Book b : bookList) {
            bookInventoryDao.deleteBook(b.getId());
        }

        // AUTHOR
        List<Author> authorList = authorInventoryDao.getAllAuthors();
        for (Author a : authorList) {
            authorInventoryDao.deleteAuthor(a.getAuthorId());
        }

        // PUBLISHER
        List<Publisher> publisherList = publisherInventoryDao.getAllPublishers();
        for (Publisher p : publisherList) {
            publisherInventoryDao.deletePublisher(p.getPublisherId());
        }


    }

    @Test public void addGetDeletePublisher() {
        // PUBLISHER
        Publisher publisher = new Publisher();
    }

    // GET ALL PUBLISHERS
    @Test
    public void getAllPublishers() {
        // ARRANGE
        Publisher publisher = new Publisher();
        publisher.setPublisherId(2);
        publisher.setName("Scott");
        publisher.setStreet("2345 St");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("23504");
        publisher.setPhone("1234567");
        publisher.setEmail("email@gmail.com");

        // ADD ACTION
        publisherInventoryDao.addPublisher(publisher);
        publisher = new Publisher();
    }

    // UPDATE
    @Test
    public void updatePublisher() {
        // ARRANGE
        Publisher publish = new Publisher();
        publish.setName("Joe");
        publish.setStreet("4567 blvd");
        publish.setCity("Norfolk");
        publish.setState("VA");
        publish.setPostalCode("23504");
        publish.setPhone("56576949");
        publish.setEmail("email@gmail.com");

        // ACTION
        Publisher publisher2 = publisherInventoryDao.addPublisher(publish);

        // ARRANGE // DONT NEED
        Publisher updatePublish = new Publisher();
        updatePublish.setName("Mark");
        updatePublish.setStreet("1234 ");
        updatePublish.setCity("Norfolk");
        updatePublish.setState("VA");
        updatePublish.setPostalCode("23504");
        updatePublish.setPhone("56576949");
        updatePublish.setEmail("email@gmail.com");
        updatePublish.setPublisherId(publisher2.getPublisherId());

        // ACTION
        publisherInventoryDao.updatePublisher(updatePublish);
        Publisher publisher3 = publisherInventoryDao.getPublisher(publisher2.getPublisherId());

        // ASSERT EQUALS
        assertEquals(publisher3.getState(), publisher2.getState());

        assertNotEquals(publisher3.getName(), publisher2.getName());
    }

}