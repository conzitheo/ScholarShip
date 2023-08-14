package theo.conzi.scholarship.builder;

import org.springframework.stereotype.Component;
import theo.conzi.scholarship.dto.StudentRequestDTO;
import theo.conzi.scholarship.dto.StudentResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.StudentEntity;

import java.util.List;
import java.util.Objects;

@Component
public class StudentBuilder {

    public StudentResponseDTO buildResponseDTO(StudentEntity student) {
        StudentResponseDTO dto = StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .className(student.getClassEntity().getName())
                .build();

        if (Objects.nonNull(student.getSquad())) {
            dto.setSquadName(student.getSquad().getName());
        }
        return dto;
    }

    public List<StudentResponseDTO> buildListResponseDTO(List<StudentEntity> students) {
        return students.stream()
                .map(this::buildResponseDTO)
                .toList();
    }

    public StudentEntity buildEntity(StudentRequestDTO requestDTO, ClassEntity classEntity) {
        return StudentEntity.builder()
                .name(requestDTO.getName())
                .classEntity(classEntity)
                .build();
    }
}
