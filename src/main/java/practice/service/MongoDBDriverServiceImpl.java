package practice.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.mongodb.GameMongoDocument;
import practice.repository.GameModel;
import practice.repository.MongoDBJpaRepository;


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

    @PostConstruct
    void init(){
        mongoDocuments = new ArrayList();
    }

    @Autowired
    private MongoDBJpaRepository mongoDBJpaRepository;

    @Override
    public List<GameModel> getGameFromMongoJPA() {

        var v = mongoDBJpaRepository.findAll();

        return v;

    }

    @Override
    public List<GameMongoDocument> getGameFromMongoDriver(){

        MongoDatabase database = connectToDb().getDatabase("MyGameLibraryApp");
        MongoCollection<Document> doc = database.getCollection("gameCollection");

        List<GameMongoDocument> li2 = new ArrayList<>();

        for (Document dc : doc.find()) {

            li2.add(GameMongoDocument.builder().gameName(dc.getString("gameName")).build());

        }

        return li2;
    }

    @Override
    public void addGameToMongoDB(GameMongoDocument gameMongoDocument){

        GameModel model = GameModel.builder()
                .id("2")
                .gameName(gameMongoDocument.getGameName())
                .gameGenre(gameMongoDocument.getGameGenre())
                .preOrder(gameMongoDocument.isPreOrder())
                .rating(gameMongoDocument.getRating())
                .build();

        mongoDBJpaRepository.save(model);

    }

    private MongoClient connectToDb(){
        if(remote) {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://dev-1:Normandy19@cluster0.feijx.mongodb.net/MyGameLibraryApp?retryWrites=true&w=majority");

            return mongoClient = new MongoClient(uri);
        }
        else{
            return mongoClient = new MongoClient("localhost", 27017);
        }
    }


}
