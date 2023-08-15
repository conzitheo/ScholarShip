package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import theo.conzi.scholarship.builder.StudentBuilder;
import theo.conzi.scholarship.dto.StudentRequestDTO;
import theo.conzi.scholarship.dto.StudentResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.StudentEntity;
import theo.conzi.scholarship.enums.StatusEnum;
import theo.conzi.scholarship.repository.ClassRepository;
import theo.conzi.scholarship.repository.StudentRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository repository;
    private final ClassRepository classRepository;
    private final StudentBuilder builder;

    public List<StudentResponseDTO> getAllStudents() {
        return builder.buildListResponseDTO(repository.findAll());
    }

    public StudentResponseDTO getStudentById(Long id) {
        StudentEntity student = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("student not found"));
        return builder.buildResponseDTO(student);
    }

    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {
        ClassEntity classEntity = validateClass(requestDTO.getIdClass());
        StudentEntity student = builder.buildEntity(requestDTO, classEntity);
        return builder.buildResponseDTO(repository.save(student));
    }

    private ClassEntity validateClass(Long idClass) {
        ClassEntity classEntity = classRepository.findById(idClass)
                .orElseThrow(() -> new EntityNotFoundException("class not found"));

        if (!StatusEnum.WAITING.name().equalsIgnoreCase(classEntity.getStatus())) {
            throw new ValidationException("You can only register new students in the status: WAITING");
        }
        return classEntity;
    }

    public void deleteStudent(Long id) {
        getStudentById(id);
        repository.deleteById(id);
    }
}
