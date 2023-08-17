package theo.conzi.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import theo.conzi.scholarship.entity.ClassEntity;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    @Query(value = "select c from ClassEntity c join fetch c.students ")
    List<ClassEntity> listClass();
}
