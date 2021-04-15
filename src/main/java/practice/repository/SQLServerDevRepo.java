package practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.DeveloperTable;

@Repository
public interface SQLServerDevRepo extends JpaRepository<DeveloperTable, String> {
}
