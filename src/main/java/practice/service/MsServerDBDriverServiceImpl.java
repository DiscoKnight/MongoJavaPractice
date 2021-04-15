package practice.service;

import org.springframework.stereotype.Service;
import practice.model.response.Game;
import practice.repository.SQLServerDevRepo;
import practice.repository.SQLServerGameRepository;
import practice.repository.entity.DeveloperTable;
import practice.repository.entity.GameTable;
import practice.transformer.MyGameTransformer;
import practice.transformer.MyGameTransformerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MsServerDBDriverServiceImpl implements MsServerDBDriverService {

    private final SQLServerGameRepository sqlServerGameRepository;
    private final SQLServerDevRepo sqlServerDevRepo;
    private final MyGameTransformer myGameTransformer;
    private final List<Game> myList = new ArrayList<>();
    private MyGameTransformer transformer = new MyGameTransformerImpl();

    public MsServerDBDriverServiceImpl(SQLServerGameRepository sqlServerGameRepository,
                                       SQLServerDevRepo sqlServerDevRepo) {
        this.sqlServerGameRepository = sqlServerGameRepository;
        this.myGameTransformer = new MyGameTransformerImpl();
        this.sqlServerDevRepo = sqlServerDevRepo;

    }

    @Override
    public Optional<List<Game>> getAllGames() {
        List<DeveloperTable> k = sqlServerDevRepo.findAll();

        sqlServerGameRepository.findAll().stream().forEach(this::transform);

        return Optional.ofNullable(myList);
    }

    @Override
    public void deleteAnGame(GameTable gameTable) {

    }

    @Override
    public Optional<GameTable> editAnGame(GameTable gameTable) {
        return Optional.empty();
    }

    @Override
    public Optional<GameTable> getAnGame(String id) {
        return Optional.empty();
    }

    private void transform(GameTable gameTable){
        myList.add(transformer.getGameT(gameTable));
    }

}
