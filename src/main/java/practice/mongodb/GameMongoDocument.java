package practice.mongodb;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GameMongoDocument {

    private String gameName;
}
