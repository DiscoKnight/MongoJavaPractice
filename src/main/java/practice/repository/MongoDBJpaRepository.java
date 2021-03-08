package practice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBJpaRepository extends MongoRepository<GameModel, String> {
}
