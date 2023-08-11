package theo.conzi.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theo.conzi.scholarship.entity.SquadEntity;

@Repository
public interface SquadRepository extends JpaRepository<SquadEntity, Long> {

}
