package com.example.service;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoDbService {

    @Autowired private MongoClient mongoClient;
    @Autowired private MongoClientURI mongoClientURI;

    public long doSomeMongoOperations(){
        DB mongo = mongoClient.getDB(mongoClientURI.getDatabase());
        DBCollection collection = mongo.getCollection("collection");
        BasicDBObject document = new BasicDBObject("foo", "bar");
        collection.insert(document);
        return collection.getCount();
    }
}

