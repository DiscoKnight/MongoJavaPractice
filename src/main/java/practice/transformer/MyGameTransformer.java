package practice.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import practice.mongodb.GameMongoDocument;
import practice.repository.GameModel;

@Mapper(componentModel = "spring")
public interface MyGameTransformer {

    MyGameTransformer INSTANCE = Mappers.getMapper(MyGameTransformer.class);

    @Mapping(source = "gameName", target = "gameName")
    GameMongoDocument getGameMongo(GameModel gameModel);
}
