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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorInventoryDaoTest {

    // SET UP DAO
    @Autowired
    protected AuthorInventoryDao authorInventoryDao;

    @Autowired
    protected PublisherInventoryDao publisherInventoryDao;

    @Autowired
    protected BookInventoryDao bookInventoryDao;

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

    @Test public void addGetDeleteAuthor() {
        // AUTHOR
        Author author = new Author();
    }

    // GET ALL AUTHOR
    @Test
    public void getALLAuthors() {
        // ARRANGE
        Author author = new Author();
        author.setFirstName("Scott");
        author.setLastName("Mahoney");
        author.setStreet("2222 Ct");
        author.setCity("Norfolk");
        author.setState("VA");
        author.setPostalCode("23504");
        author.setPhone("456789123");
        author.setEmail("email@gmail.com");

        // ADD ACTION
        authorInventoryDao.addAuthor(author);
        author = new Author();
    }

    // UPDATE
    @Test
    public void updateAuthors() {
        // ARRANGE
        Author auth = new Author();
        auth.setFirstName("Jack");
        auth.setLastName("Frost");
        auth.setStreet("456 St");
        auth.setCity("Norfolk");
        auth.setState("VA");
        auth.setPostalCode("23504");
        auth.setPhone("87654321");
        auth.setEmail("email@gmail.com");

        // ACTION
        Author author2 = authorInventoryDao.addAuthor(auth);

        // ARRANGE
        Author updateAuthor = new Author();
        updateAuthor.setFirstName("Mike");
        updateAuthor.setLastName("Smith");
        updateAuthor.setStreet("123 Street");
        updateAuthor.setCity("Norfolk");
        updateAuthor.setState("VA");
        updateAuthor.setPostalCode("23504");
        updateAuthor.setPhone("123456789");
        updateAuthor.setEmail("email@gmail.com");
        updateAuthor.setAuthorId(author2.getAuthorId());

        // ACTION
        authorInventoryDao.updateAuthor(updateAuthor);
        Author author3 = authorInventoryDao.getAuthor(author2.getAuthorId());

        // ASSERT EQUALS
        assertEquals(author3.getState(), author2.getState());

        assertNotEquals(author3.getFirstName(), author2.getFirstName());
    }
}


