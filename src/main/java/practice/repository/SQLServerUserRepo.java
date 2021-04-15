package practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.repository.entity.UserTable;

@Repository
public interface SQLServerUserRepo extends JpaRepository<UserTable, String> {
}
