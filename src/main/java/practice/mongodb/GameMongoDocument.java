package practice.mongodb;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GameMongoDocument {

    private final String gameName;
    private final String gameGenre;
    private final int rating;
    private final boolean preOrder;

}
