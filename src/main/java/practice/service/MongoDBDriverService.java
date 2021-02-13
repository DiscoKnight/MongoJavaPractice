package practice.service;

import practice.mongodb.GameMongoDocument;

import java.util.List;

public interface MongoDBDriverService {

    List<GameMongoDocument> getGameFromMongo();

    void addGameToMongoDB(GameMongoDocument gameMongoDocument);
}
