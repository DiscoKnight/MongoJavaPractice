package practice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.GameTable;
import practice.repository.entity.PublisherTable;

@Repository
public interface SQLServerPublisherRepo extends CrudRepository<PublisherTable, String> {
}
