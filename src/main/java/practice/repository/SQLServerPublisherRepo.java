package practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.PublisherTable;

@Repository
public interface SQLServerPublisherRepo extends JpaRepository<PublisherTable, String> {
}
