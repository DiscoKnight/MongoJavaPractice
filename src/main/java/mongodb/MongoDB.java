package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class MongoDB {

    public List<GameMongoDocument> foo() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("MyGameLibraryApp");
        MongoCollection<Document> doc = database.getCollection("gameCollection");

        List<GameMongoDocument> li = new ArrayList<>();

        for (Document dc : doc.find()) {
            li.add(GameMongoDocument.builder().gameName(dc.getString("gameName")).build());

        }

        return li;

    }


}
