package practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import practice.model.GameCriteria;
import practice.model.GameMongoDocument;
import practice.service.MongoDBDriverService;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private MongoDBDriverService mongoDBDriverService;

    @GetMapping(value = "/game/getAllGames")
    @ResponseStatus(HttpStatus.OK)
    List<GameMongoDocument> getGames() {
        return mongoDBDriverService.getGameFromMongoJPA();
    }

    @PostMapping(value = "/game/addGame")
    @ResponseStatus(HttpStatus.CREATED)
    void addGame(@RequestBody(required = false) GameMongoDocument gameMongoDocument) {

        mongoDBDriverService.addGameToMongoDB(gameMongoDocument);

    }

    @GetMapping(value = "/game/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GameMongoDocument findByGameById(@PathVariable String id){

        return mongoDBDriverService.getGameById(id);

    }

    @GetMapping(value = "/game/findGameWithParams")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    List<GameMongoDocument> findGameByFilters(@RequestParam(required = false) String dev,
                                              @RequestParam(required = false) String publisher,
                                              @RequestParam(required = false) String genre){

        return mongoDBDriverService.getGamesByFilter(GameCriteria.builder().dev(dev).publisher(publisher).genre(genre).build());

    }

}
