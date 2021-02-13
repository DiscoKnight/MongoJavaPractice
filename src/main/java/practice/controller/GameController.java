package practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import practice.mongodb.GameMongoDocument;
import practice.service.MongoDBDriverService;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private MongoDBDriverService mongoDBDriverService;

    @GetMapping(value = "/getAllGames")
    @ResponseStatus(HttpStatus.OK)
    List<GameMongoDocument> getGames(){
        return mongoDBDriverService.getGameFromMongo();
    }

    @PostMapping(value = "/addGame")
    @ResponseStatus(HttpStatus.CREATED)
    void addGame(@RequestBody(required = false) GameMongoDocument gameMongoDocument){

    }
}
