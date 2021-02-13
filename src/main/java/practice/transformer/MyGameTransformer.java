package practice.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practice.mongodb.GameMongoDocument;
import practice.repository.GameModel;

@Mapper
public interface MyGameTransformer {

    MyGameTransformer INSTANCE = Mappers.getMapper(MyGameTransformer.class);

    GameMongoDocument getGameMongo(GameModel gameModel);
}
