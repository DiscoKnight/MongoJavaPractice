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

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBDriverServiceImpl implements MongoDBDriverService {

    private Boolean remote = false;
    MongoClient mongoClient;

    @Autowired
    private MongoDBJpaRepository mongoDBJpaRepository;

    @Override
    public List<GameMongoDocument> getGameFromMongo() {

        List<GameModel> li = mongoDBJpaRepository.findAll();

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
