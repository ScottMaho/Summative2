package com.company.Summative2MahoneyScott.dao;

import com.company.Summative2MahoneyScott.dto.Publisher;

import java.util.List;

public interface PublisherInventoryDao {
    Publisher getPublisher(int id);

    List<Publisher> getAllPublishers();

    Publisher addPublisher(Publisher publisher);

    void updatePublisher(Publisher publisher);

    void deletePublisher(int id);
}
