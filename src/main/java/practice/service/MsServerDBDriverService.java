package practice.service;

import practice.model.response.Game;
import practice.repository.entity.GameTable;

import java.util.List;
import java.util.Optional;

public interface MsServerDBDriverService {

    Optional<List<Game>> getAllGames();

    void deleteAnGame(GameTable gameTable);

    Optional<GameTable> editAnGame(GameTable gameTable);

    Optional<GameTable> getAnGame(String id);


}
