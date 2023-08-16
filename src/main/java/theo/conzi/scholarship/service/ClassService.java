package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import theo.conzi.scholarship.builder.ClassBuilder;
import theo.conzi.scholarship.dto.ClassRequestDTO;
import theo.conzi.scholarship.dto.ClassResponseDTO;
import theo.conzi.scholarship.dto.ClassUpdateDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.OrganizerEntity;
import theo.conzi.scholarship.entity.StudentEntity;
import theo.conzi.scholarship.enums.StatusEnum;
import theo.conzi.scholarship.enums.TypeEnum;
import theo.conzi.scholarship.repository.ClassRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ClassService {

    private final ClassRepository repository;
    private final ClassBuilder builder;


    public List<ClassResponseDTO> getAllClasses() {
        return builder.buildListResponseDTO(repository.listClass());
    }

    public ClassResponseDTO getClassById(Long id) {
        ClassEntity classEntity = validateIdClass(id);
        return builder.buildResponseDTO(classEntity);
    }

    private ClassEntity validateIdClass(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("class not found"));
    }

    public ClassResponseDTO createClass(ClassRequestDTO requestDTO) {
        ClassEntity classEntity = builder.buildEntity(requestDTO);
        classEntity.setStatus(StatusEnum.WAITING.name());
        return builder.buildResponseDTO(repository.save(classEntity));
    }

    public void deleteClass(Long id) {
        getClassById(id);
        repository.deleteById(id);
    }

    public ClassResponseDTO updateStatus(ClassUpdateDTO classUpdateDTO, Long id) {
        validateNewStatus(classUpdateDTO);
        ClassEntity classEntity = validateIdClass(id);

        if (!isValidStatusTransition(classEntity.getStatus(), classUpdateDTO.getStatus())) {
            throw new ValidationException("Invalid status, only can change the status to STARTED, " +
                    "if the before is WAITING, and to FINISHED, if the before is STARTED");
        }

        if (classUpdateDTO.getStatus().equalsIgnoreCase(StatusEnum.STARTED.name())) {
            validateClassStartRequirements(classEntity.getOrganizers(), classEntity.getStudents());
        }

        classEntity.setStatus(classUpdateDTO.getStatus());
        return builder.buildResponseDTO(repository.save(classEntity));
    }

    private void validateNewStatus(ClassUpdateDTO classUpdateDTO) {
        if (!classUpdateDTO.getStatus().equalsIgnoreCase(StatusEnum.STARTED.name()) &&
                !classUpdateDTO.getStatus().equalsIgnoreCase(StatusEnum.FINISHED.name())) {
            throw new ValidationException("Status invalid, only can change the status to - STARTED and FINISHED");
        }
    }

    private boolean isValidStatusTransition(String currentStatus, String newStatus) {
        if (currentStatus.equalsIgnoreCase(StatusEnum.WAITING.name())) {
            return newStatus.equalsIgnoreCase(StatusEnum.STARTED.name());
        } else if (currentStatus.equalsIgnoreCase(StatusEnum.STARTED.name())) {
            return newStatus.equalsIgnoreCase(StatusEnum.FINISHED.name());
        }
        return false;
    }

    private void validateClassStartRequirements(List<OrganizerEntity> organizers, List<StudentEntity> students) {
        if (!CollectionUtils.isEmpty(students) && students.size() < 15) {
            throw new ValidationException("Class start requires at least 15 students");
        }

        if (CollectionUtils.isEmpty(organizers)) {
            throw new ValidationException("Class start requires organizers");
        }

        Map<String, Long> organizerCounts = countStudentsOfType(organizers);
        long coordinatorCount = organizerCounts.getOrDefault(TypeEnum.COORDINATOR.name(), 0L);
        long scrumMasterCount = organizerCounts.getOrDefault(TypeEnum.SCRUM_MASTER.name(), 0L);
        long instructorCount = organizerCounts.getOrDefault(TypeEnum.INSTRUCTOR.name(), 0L);

        if (coordinatorCount < 1 || scrumMasterCount < 1 || instructorCount < 3) {
            throw new ValidationException("Class start requires at least 1 coordinator, 1 scrum master, and 3 instructors");
        }
    }

    private Map<String, Long> countStudentsOfType(List<OrganizerEntity> organizers) {
        Map<String, Long> organizerCounts = new HashMap<>();

        for (OrganizerEntity organizer : organizers) {
            String organizerType = organizer.getType();
            organizerCounts.put(organizerType, organizerCounts.getOrDefault(organizerType, 0L) + 1);
        }

        return organizerCounts;
    }
}
