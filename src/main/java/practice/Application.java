package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoDataAutoConfiguration.class, MongoRepositoriesAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

    }

}
