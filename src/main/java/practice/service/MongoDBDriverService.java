package practice.service;

import practice.mongodb.GameMongoDocument;
import practice.repository.GameModel;

import java.util.List;

public interface MongoDBDriverService {

    List<GameMongoDocument> getGameFromMongoJPA();

    void addGameToMongoDB(GameMongoDocument gameMongoDocument);

    List<GameMongoDocument> getGameFromMongoDriver();
}
