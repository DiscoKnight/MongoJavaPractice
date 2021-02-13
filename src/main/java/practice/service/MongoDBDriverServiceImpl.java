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
import practice.transformer.MyGameTransformer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBDriverServiceImpl implements MongoDBDriverService {

    private Boolean remote = false;
    MongoClient mongoClient;

    private List<GameMongoDocument> mongoDocuments;

    @PostConstruct
    void init(){
        mongoDocuments = new ArrayList();
    }

    @Autowired
    private MongoDBJpaRepository mongoDBJpaRepository;

    @Override
    public List<GameMongoDocument> getGameFromMongoJPA() {

        mongoDBJpaRepository.findAll().stream().forEach(this::gameTransform);

        return mongoDocuments;

    }

    @Override
    public List<GameMongoDocument> getGameFromMongoDriver(){

        MongoDatabase database = foo().getDatabase("MyGameLibraryApp");
        MongoCollection<Document> doc = database.getCollection("gameCollection");

        List<GameMongoDocument> li2 = new ArrayList<>();

        for (Document dc : doc.find()) {

            li2.add(GameMongoDocument.builder().gameName(dc.getString("gameName")).build());

        }

        return li2;
    }

    @Override
    public void addGameToMongoDB(GameMongoDocument gameMongoDocument){

        MongoDatabase database = foo().getDatabase("MyGameLibraryApp");
        MongoCollection<Document> doc = database.getCollection("gameCollection");

    }

    private void gameTransform(GameModel gameModel){
        GameMongoDocument document = MyGameTransformer.INSTANCE.getGameMongo(gameModel);

        mongoDocuments.add(document);
    }

    private MongoClient foo(){
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
