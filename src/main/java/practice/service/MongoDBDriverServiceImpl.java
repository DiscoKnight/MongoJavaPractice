package practice.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.configuration.GameAppplicationConfig;
import practice.model.PublisherModel;
import practice.mongodb.GameMongoDocument;
import practice.repository.GameModel;
import practice.repository.MongoDBJpaRepository;
import practice.transformer.MyGameTransformer;
import practice.transformer.MyGameTransformerImpl;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBDriverServiceImpl implements MongoDBDriverService {

    private Boolean remote = false;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private List<GameMongoDocument> mongoDocuments;
    private MongoCollection<Document> documentMongoCollection;

    @Autowired
    private MongoDBJpaRepository mongoDBJpaRepository;

    @Autowired
    private GameAppplicationConfig gameAppplicationConfig;

    @PostConstruct
    void init(){
        mongoDocuments = new ArrayList();
    }

    @Override
    public List<GameModel> getGameFromMongoJPA() {

        List<GameModel> gm = mongoDBJpaRepository.findAll();
        MyGameTransformerImpl impl = new MyGameTransformerImpl();

        for(GameModel m : gm){
            GameMongoDocument doc = impl.getGameMongo(m);

            System.out.println("smurf");
        }

        return gm;

    }

    @Override
    public List<GameMongoDocument> getGameFromMongoDriver(){

        MongoDatabase database = connectToDb().getDatabase(gameAppplicationConfig.databaseName);
        MongoCollection<Document> doc = database.getCollection(gameAppplicationConfig.collectionName);

        List<GameMongoDocument> gameMongoDocumentArrayList = new ArrayList<>();

        for (Document dc : doc.find()) {

            MyGameTransformerImpl im = new MyGameTransformerImpl();

            gameMongoDocumentArrayList.add(GameMongoDocument.builder()
                    .gameName(dc.getString("gameName"))
                    .gameGenre(dc.getString("gameGenre"))
                    //.gamePublisher(dc.get("gamePublisher", PublisherModel.class))
                    .build());

        }

        return gameMongoDocumentArrayList;
    }

    @Override
    public void addGameToMongoDB(GameMongoDocument gameMongoDocument){

        GameModel model = GameModel.builder()
                .id("1")
                .gameName(gameMongoDocument.getGameName())
                .gameGenre(gameMongoDocument.getGameGenre())
                .gamePublisher(gameMongoDocument.getGamePublisher())
                .preOrder(gameMongoDocument.isPreOrder())
                .rating(gameMongoDocument.getRating())
                .build();

        mongoDBJpaRepository.save(model);

    }

    private MongoClient connectToDb(){
        if(gameAppplicationConfig.isRemote) {
            MongoClientURI uri = new MongoClientURI(
                    gameAppplicationConfig.url);

            return mongoClient = new MongoClient(uri);
        }
        else{
            return mongoClient = new MongoClient("localhost", 27017);
        }
    }


}
