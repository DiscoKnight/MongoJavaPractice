package practice.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private int rating;
    private boolean preOrder;
}
