package practice.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import practice.model.PublisherModel;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "gameCollection")
public class GameModel {

    @Id
    @Field("_id")
    public String id;

    private String gameName;
    private String gameGenre;
    private PublisherModel gamePublisher;
    private int rating;
    private boolean preOrder;
    private String imageUrl;

}
