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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookInventoryDaoTest {

    // SET UP DAOS
    @Autowired
    BookInventoryDao bookInventoryDao;

    @Autowired
    AuthorInventoryDao authorInventoryDao;

    @Autowired
    PublisherInventoryDao publisherInventoryDao;


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

    @Test
    public void addGetDeleteBook() {
        // ARRANGE BOOK, AUTHOR, AND PUBLISHER

        // AUTHOR
        Author author = new Author();
        author.setFirstName("Scott");
        author.setLastName("Mahoney");
        author.setStreet("2222 St");
        author.setCity("Norfolk");
        author.setState("VA");
        author.setPostalCode("23504");
        author.setPhone("1234567899");
        author.setEmail("email@gmail.com");

        // ACTION
         author = authorInventoryDao.addAuthor(author);


        // PUBLISHER
        Publisher publisher = new Publisher();
        publisher.setName("Bob");
        publisher.setStreet("123 St");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("23504");
        publisher.setPhone("8045678999");
        publisher.setEmail("email@gmail.com");

        // ACTION ADD
       publisher = publisherInventoryDao.addPublisher(publisher);


        // BOOK
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());                 // SET AUTHOR IN DB

        book.setPublisherId(publisher.getPublisherId());     // SET PUB IN DB
        book.setPublishDate(LocalDate.now());
        book.setTitle("Of Mice and Men");
        book.setPrice("12.99");
        book.setIsbn("1234567");

        // ACTION
        book = bookInventoryDao.addBook(book);
        Book book2 = bookInventoryDao.getBook(book.getId());

        // ASSERT
        assertEquals(book, book2);

        // ACTION DELETE
        bookInventoryDao.deleteBook(book.getId());
        book2 = bookInventoryDao.getBook(book.getId());

        assertNull(book2);
    }

    // GET ALL BOOKS
    @Test
    public void getAllBooks() {

        // AUTHOR
        Author author = new Author();
        author.setFirstName("Kyle");
        author.setLastName("Johnson");
        author.setStreet("543 St");
        author.setCity("Norfolk");
        author.setState("VA");
        author.setPostalCode("23504");
        author.setPhone("5678900987");
        author.setEmail("email@gmail.com");

        // ADD ACTION
        author = authorInventoryDao.addAuthor(author);

        // PUBLISHER
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("8765 St");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("23504");
        publisher.setPhone("3456789012");
        publisher.setEmail("email@gmail.com");

        // ADD ACTION
        publisher = publisherInventoryDao.addPublisher(publisher);

        // ARRANGE
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPublishDate(LocalDate.now());
        book.setTitle("Harry Potter");
        book.setPrice("14.99");
        book.setIsbn("123456");

        // ADD ACTION
        book = bookInventoryDao.addBook(book);

        // ARRANGE
        Book book2 = new Book();
        book2.setAuthorId(author.getAuthorId());
        book2.setPublisherId(publisher.getPublisherId());
        book2.setPublishDate(LocalDate.now());
        book2.setTitle("Star Wars");
        book2.setPrice("8.99");
        book2.setIsbn("789123");

        // ACTION
        book2 = bookInventoryDao.addBook(book2);
        List<Book> bookList = bookInventoryDao.getAllBooks();


        // ASSERT
        assertEquals(2, bookList.size());

    }

    // UPDATE BOOK
    @Test
    public void updateBook() {

        // AUTHOR
        Author author = new Author();
        author.setFirstName("Scott");
        author.setLastName("Mahoney");
        author.setStreet("1234 St");
        author.setCity("Norfolk");
        author.setState("VA");
        author.setPostalCode("23504");
        author.setPhone("2345678");
        author.setEmail("email@gmail.com");

        // ADD ACTION
        author = authorInventoryDao.addAuthor(author);

        // PUBLISHER
        Publisher publish = new Publisher();
        publish.setName("Joe");
        publish.setStreet("4567 blvd");
        publish.setCity("Norfolk");
        publish.setState("VA");
        publish.setPostalCode("23504");
        publish.setPhone("56576949");
        publish.setEmail("email@gmail.com");

        // ADD ACTION
        publish = publisherInventoryDao.addPublisher(publish);

        // ARRANGE
        Book book = new Book();
        book.setIsbn("234456");
        book.setPublishDate(LocalDate.now());
        book.setTitle("Me");
        book.setPrice("6.99");
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publish.getPublisherId());

        // ARRANGE
        book = bookInventoryDao.addBook(book);

        // ARRANGE
        book.setIsbn("475658");
        book.setPublishDate(LocalDate.now());
        book.setTitle("you");
        book.setPrice("7.99");

        // ACTION
        bookInventoryDao.updateBook(book);
        Book book3 = bookInventoryDao.getBook(book.getId());

        // ASSERT EQUALS
        assertEquals(book3, book);


    }


    // GET ALL BY AUTHOR
    @Test
    public void getAllBooksByAuthor() {

    }

}