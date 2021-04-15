package practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.model.response.Game;
import practice.repository.entity.GameTable;
import practice.service.MsServerDBDriverService;
import practice.transformer.MyGameTransformer;
import practice.transformer.MyGameTransformerImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class GameController {

    private final List<Game> myList = new ArrayList<>();
    private final MsServerDBDriverService msServerDBDriverService;

    public GameController(MsServerDBDriverService msServerDBDriverService) {
        this.msServerDBDriverService = msServerDBDriverService;
    }

    @GetMapping(value = "/game/getAllGames")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Game> viewGames() {
       return msServerDBDriverService.getAllGames().get();

    }

    @PostMapping(value = "/game/addGame")
    @ResponseStatus(HttpStatus.CREATED)
    void addGame() {

    }

    @GetMapping(value = "/game/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    void findByGameById(@PathVariable String id) {

    }

    @GetMapping(value = "/game/findGameWithParams")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    void findGameByFilters(@RequestParam(required = false) String dev,
                           @RequestParam(required = false) String publisher,
                           @RequestParam(required = false) String genre) {

    }

}
