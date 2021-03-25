package practice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoDBJpaRepository extends MongoRepository<GameModel, String> {

    @Query("{ 'gamePublisher.dev' : ?0 },{ 'gameGenre' : ?1 }, { 'gamePublisher.name' : ?2}")
    List<GameModel> findByDevAndGenre(String dev, String genre, String publisher);

}
