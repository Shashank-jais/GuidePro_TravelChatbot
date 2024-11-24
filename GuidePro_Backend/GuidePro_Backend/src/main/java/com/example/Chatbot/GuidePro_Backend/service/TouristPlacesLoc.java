package com.example.Chatbot.GuidePro_Backend.service;

import com.example.Chatbot.GuidePro_Backend.model.TouristPlaceDTO;
import com.example.Chatbot.GuidePro_Backend.model.TouristPlaces;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component

public class TouristPlacesLoc {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;


    public TouristPlaces findByText(String city) {
        TouristPlaces places = new TouristPlaces();
        List<TouristPlaceDTO> placeDTO = new ArrayList<>();

        // Access the database and collection
        MongoDatabase database = client.getDatabase("TouristPlaces");
        MongoCollection<Document> collection = database.getCollection("tourist");

        // Perform a search query
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                        new Document("text",
                                new Document("query", city)
                                        .append("path", "City") // Adjust "City" to your actual field name
                        )
                )
        ));

        // Map the results
        result.forEach(doc -> placeDTO.add(converter.read(TouristPlaceDTO.class, doc)));
        places.setPlaces(placeDTO);

        return places;
    }
}

