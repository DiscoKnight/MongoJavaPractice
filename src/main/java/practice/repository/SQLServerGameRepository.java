package practice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.GameTable;

@Repository
public interface SQLServerGameRepository extends CrudRepository<GameTable, String> {

}
