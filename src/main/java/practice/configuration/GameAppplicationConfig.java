package practice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "practice.repository")
public class GameAppplicationConfig extends AbstractMongoClientConfiguration {

    @Value("${collection.databaseName}")
    public String databaseName;

    @Value("${collection.collectionName}")
    public String collectionName;

    @Override
    protected String getDatabaseName() {
        return "MyGameLibraryApp";
    }
}
