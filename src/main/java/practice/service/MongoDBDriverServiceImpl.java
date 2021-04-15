package practice.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Service;
import practice.configuration.GameAppplicationConfig;
import practice.repository.SQLServerGameRepository;
import practice.transformer.MyGameTransformerImpl;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBDriverServiceImpl implements MongoDBDriverService {

    private MongoClient mongoClient;
    private List<GameMongoDocument> mongoDocuments;
    private MyGameTransformerImpl myGameTransformer;
    private final SQLServerGameRepository SQLServerGameRepository;
    private final GameAppplicationConfig gameAppplicationConfig;

    public MongoDBDriverServiceImpl(GameAppplicationConfig gameAppplicationConfig,
                                    SQLServerGameRepository SQLServerGameRepository){
        this.gameAppplicationConfig = gameAppplicationConfig;
        this.SQLServerGameRepository = SQLServerGameRepository;
    }

    @PostConstruct
    void init() {

        mongoDocuments = new ArrayList();
        myGameTransformer = new MyGameTransformerImpl();

        if (gameAppplicationConfig.isRemote) {
            MongoClientURI uri = new MongoClientURI(
                    gameAppplicationConfig.url);

            mongoClient = new MongoClient(uri);
        } else {
            mongoClient = new MongoClient("localhost", 27017);
        }
    }

    @Override
    public List<GameMongoDocument> getGameFromMongoJPA() {

        List<GameMongoDocument> gameMongoDocuments = new ArrayList<>();

        for (GameModel gameModel : SQLServerGameRepository.findAll()) {
            gameMongoDocuments.add(myGameTransformer.getGameMongo(gameModel));

        }

        return gameMongoDocuments;

    }

    @Override
    public List<GameMongoDocument> getGameFromMongoDriver() {

        MongoCollection<Document> gameDocumentFromCollection = mongoClient
                .getDatabase(gameAppplicationConfig.databaseName)
                .getCollection(gameAppplicationConfig.collectionName);

        List<GameMongoDocument> gameMongoDocumentArrayList = new ArrayList<>();

        for (Document gameDocument : gameDocumentFromCollection.find()) {

            gameMongoDocumentArrayList.add(GameMongoDocument.builder()
                    .gameName(gameDocument.getString("gameName"))
                    .gameGenre(gameDocument.getString("gameGenre"))
                    .build());

        }

        return gameMongoDocumentArrayList;
    }

    @Override
    public void addGameToMongoDB(GameMongoDocument gameMongoDocument) {

        GameModel model = myGameTransformer.getMongoDocument(gameMongoDocument);
        model.id = generateIdForDocument();

        SQLServerGameRepository.save(model);


    }

    @Override
    public List<GameMongoDocument> getGamesByFilter(GameCriteria criteria) {

        mongoDocuments.clear();

        List<GameModel> gameModelList = SQLServerGameRepository.findByDevAndGenre(criteria.getDev(),
                criteria.getGenre(),
                criteria.getPublisher());

        gameModelList.stream().forEach(this::transform);

        return mongoDocuments;
    }

    @Override
    public GameMongoDocument getGameById(String id){
        GameModel gameModel = SQLServerGameRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return myGameTransformer.getGameMongo(gameModel);
    }

    private void transform(GameModel gameModel) {
        mongoDocuments.add(myGameTransformer.getGameMongo(gameModel));
    }

    private String generateIdForDocument() {
        return String.valueOf(SQLServerGameRepository.count() + 1);
    }

}
