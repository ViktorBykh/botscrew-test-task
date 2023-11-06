package botscrew.test.task.repository;

import botscrew.test.task.model.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findLectorsByNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(
            String name, String lastName);
}
