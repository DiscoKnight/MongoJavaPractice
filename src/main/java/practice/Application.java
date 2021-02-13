package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class Application {

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);
        //MongoDB mongoDB = new MongoDB();

        //mongoDB.foo();

    }

}
