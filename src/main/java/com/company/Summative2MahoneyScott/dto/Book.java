package com.company.Summative2MahoneyScott.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    // PROPERTIES AND VARS
    private int bookId;
    private int authorId;
    private int publisherId;
    private LocalDate publishDate;
    private String title;
    private String price;
    private String isbn;

    // GETTERS AND SETTERS

    public int getId() {
        return bookId;
    }

    public void setId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bookId == book.bookId && getAuthorId() == book.getAuthorId() && getPublisherId() == book.getPublisherId() && Objects.equals(getPublishDate(), book.getPublishDate()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getPrice(), book.getPrice()) && Objects.equals(getIsbn(), book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, getAuthorId(), getPublisherId(), getPublishDate(), getTitle(), getPrice(), getIsbn());
    }
}
