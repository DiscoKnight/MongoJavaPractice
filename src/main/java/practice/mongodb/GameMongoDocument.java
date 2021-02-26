package practice.mongodb;

import lombok.Builder;
import lombok.Value;
import practice.model.PublisherModel;

@Builder
@Value
public class GameMongoDocument {

    private final String gameName;
    private final String gameGenre;
    private PublisherModel gamePublisher;
    private final int rating;
    private final boolean preOrder;


}
