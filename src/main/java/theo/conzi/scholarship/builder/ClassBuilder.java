package theo.conzi.scholarship.builder;

import org.springframework.stereotype.Component;
import theo.conzi.scholarship.dto.ClassRequestDTO;
import theo.conzi.scholarship.dto.ClassResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;

import java.util.List;

@Component
public class ClassBuilder {

    public ClassResponseDTO buildResponseDTO(ClassEntity classEntity) {
        return ClassResponseDTO.builder()
                .id(classEntity.getId())
                .name(classEntity.getName())
                .status(classEntity.getStatus())
                .duration(classEntity.getDuration())
                .build();
    }

    public List<ClassResponseDTO> buildListResponseDTO(List<ClassEntity> classes) {
        return classes.stream()
                .map(this::buildResponseDTO)
                .toList();
    }

    public ClassEntity buildEntity(ClassRequestDTO requestDTO) {
        return ClassEntity.builder()
                .name(requestDTO.getName())
                .duration(requestDTO.getDuration())
                .build();
    }
}
