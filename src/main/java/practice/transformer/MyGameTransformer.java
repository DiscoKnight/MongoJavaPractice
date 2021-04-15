package practice.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import practice.model.response.Game;
import practice.repository.entity.GameTable;

@Mapper(componentModel = "spring")
public interface MyGameTransformer {

    MyGameTransformer INSTANCE = Mappers.getMapper(MyGameTransformer.class);

    @Mapping(source = "gameName", target = "gameName")
    Game getGameT(GameTable gameTable);

    GameTable getTable(Game game);

}
