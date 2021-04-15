package practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.GameTable;

@Repository
public interface SQLServerGameRepository extends JpaRepository<GameTable, String> {

}
