package heroes.backend;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<MissionEntity, Integer> {
  List<MissionEntity> findAllByOrderByName();
  Optional<MissionEntity> findByName(String name);
}