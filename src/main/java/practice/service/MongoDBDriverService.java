package practice.service;

import practice.model.GameCriteria;
import practice.model.GameMongoDocument;

import java.util.List;

public interface MongoDBDriverService {

    /**
     * Get all games from the MongoDB
     *
     * @return
     */
    List<GameMongoDocument> getGameFromMongoJPA();

    /**
     * Add game object to the MongoDB
     *
     * @param gameMongoDocument
     */
    void addGameToMongoDB(GameMongoDocument gameMongoDocument);

    /**
     *
     * @return
     */
    List<GameMongoDocument> getGameFromMongoDriver();

    /**
     * Get games matching specified criteria - Developer,
     * publish, genre
     *
     */
    List<GameMongoDocument> getGamesByFilter(GameCriteria criteria);

    /**
     *
     */
    GameMongoDocument getGameById(String id);
}
