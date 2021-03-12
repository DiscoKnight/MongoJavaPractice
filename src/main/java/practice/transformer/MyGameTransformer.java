package practice.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practice.model.GameMongoDocument;
import practice.repository.GameModel;

@Mapper(componentModel = "spring")
public interface MyGameTransformer {

    MyGameTransformer INSTANCE = Mappers.getMapper(MyGameTransformer.class);

    GameMongoDocument getGameMongo(GameModel gameModel);

    GameModel getMongoDocument(GameMongoDocument gameMongoDocument);
}
