package theo.conzi.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theo.conzi.scholarship.entity.OrganizerEntity;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {
}
