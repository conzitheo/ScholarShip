package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import theo.conzi.scholarship.builder.ClassBuilder;
import theo.conzi.scholarship.dto.ClassRequestDTO;
import theo.conzi.scholarship.dto.ClassResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.enums.StatusEnum;
import theo.conzi.scholarship.repository.ClassRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassService {

    private final ClassRepository repository;
    private final ClassBuilder builder;


    public List<ClassResponseDTO> getAllClasses() {
        return builder.buildListResponseDTO(repository.listClass());
    }

    public ClassResponseDTO getClassById(Long id) {
        ClassEntity classEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("class not found"));
        return builder.buildResponseDTO(classEntity);
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
}
