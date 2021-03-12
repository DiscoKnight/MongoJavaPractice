package practice.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.configuration.GameAppplicationConfig;
import practice.model.GameCriteria;
import practice.model.GameMongoDocument;
import practice.repository.GameModel;
import practice.repository.MongoDBJpaRepository;
import practice.transformer.MyGameTransformerImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBDriverServiceImpl implements MongoDBDriverService {

    private MongoClient mongoClient;
    private List<GameMongoDocument> mongoDocuments;
    private MyGameTransformerImpl myGameTransformer;

    @Autowired
    private MongoDBJpaRepository mongoDBJpaRepository;

    @Autowired
    private GameAppplicationConfig gameAppplicationConfig;

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

        for (GameModel gameModel : mongoDBJpaRepository.findAll()) {
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

        mongoDBJpaRepository.save(model);


    }

    @Override
    public List<GameMongoDocument> getGamesByFilter(GameCriteria criteria) {

        mongoDocuments.clear();

        List<GameModel> gameModelList = mongoDBJpaRepository.findByDevAndGenre(criteria.getDev(),
                criteria.getGenre(),
                criteria.getPublisher());

        gameModelList.stream().forEach(this::transform);

        return mongoDocuments;
    }

    @Override
    public GameMongoDocument getGameById(String id){
        GameModel gameModel = mongoDBJpaRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return myGameTransformer.getGameMongo(gameModel);
    }

    private void transform(GameModel gameModel) {
        mongoDocuments.add(myGameTransformer.getGameMongo(gameModel));
    }

    private String generateIdForDocument() {
        return String.valueOf(mongoDBJpaRepository.count() + 1);
    }

}
