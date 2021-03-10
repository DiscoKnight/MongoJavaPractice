package practice.mongodb;

import lombok.*;
import practice.model.PublisherModel;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMongoDocument {

    public String id;
    public String gameName;
    public String gameGenre;
    public PublisherModel gamePublisher;
    public int rating;
    public boolean preOrder;
    public String imageUrl;

}
