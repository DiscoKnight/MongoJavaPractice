package practice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
