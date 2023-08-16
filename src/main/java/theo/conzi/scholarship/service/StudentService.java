package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import theo.conzi.scholarship.builder.StudentBuilder;
import theo.conzi.scholarship.dto.StudentRequestDTO;
import theo.conzi.scholarship.dto.StudentResponseDTO;
import theo.conzi.scholarship.dto.StudentUpdateDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.SquadEntity;
import theo.conzi.scholarship.entity.StudentEntity;
import theo.conzi.scholarship.enums.StatusEnum;
import theo.conzi.scholarship.repository.ClassRepository;
import theo.conzi.scholarship.repository.SquadRepository;
import theo.conzi.scholarship.repository.StudentRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository repository;
    private final ClassRepository classRepository;
    private final SquadRepository squadRepository;
    private final StudentBuilder builder;

    public List<StudentResponseDTO> getAllStudents() {
        return builder.buildListResponseDTO(repository.findAll());
    }

    public StudentResponseDTO getStudentById(Long id) {
        StudentEntity student = validateIdStudent(id);
        return builder.buildResponseDTO(student);
    }

    private StudentEntity validateIdStudent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("student not found"));
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

        List<StudentEntity> students = classEntity.getStudents();
        if (!CollectionUtils.isEmpty(students) && students.size() >= 30) {
            throw new ValidationException("Class must have a maximum of 30 students");
        }

        return classEntity;
    }

    public void deleteStudent(Long id) {
        getStudentById(id);
        repository.deleteById(id);
    }

    public StudentResponseDTO registerStudentInSquad(Long id, StudentUpdateDTO updateDTO) {
        StudentEntity student = validateIdStudent(id);
        SquadEntity squad = validateIdSquad(updateDTO.getIdSquad());
        List<StudentEntity> studentsInSquad = squad.getStudents();
        if (!CollectionUtils.isEmpty(studentsInSquad) && studentsInSquad.size() >= 5) {
            throw new ValidationException("Squad must have a maximum of 5 students");
        }
        student.setSquad(squad);
        return builder.buildResponseDTO(repository.save(student));
    }

    private SquadEntity validateIdSquad(Long idStudent) {
        return squadRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("squad not found"));
    }
}
