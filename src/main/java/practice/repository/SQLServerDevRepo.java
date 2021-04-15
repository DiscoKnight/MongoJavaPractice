package practice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.DeveloperTable;
import practice.repository.entity.GameTable;

@Repository
public interface SQLServerDevRepo extends CrudRepository<DeveloperTable, String> {
}
