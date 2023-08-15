package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import theo.conzi.scholarship.builder.OrganizerBuilder;
import theo.conzi.scholarship.dto.OrganizerRequestDTO;
import theo.conzi.scholarship.dto.OrganizerResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.OrganizerEntity;
import theo.conzi.scholarship.enums.TypeEnum;
import theo.conzi.scholarship.repository.ClassRepository;
import theo.conzi.scholarship.repository.OrganizerRepository;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganizerService {

    private final OrganizerRepository repository;
    private final ClassRepository classRepository;
    private final OrganizerBuilder builder;

    public List<OrganizerResponseDTO> getAllOrganizers() {
        return builder.buildListResponseDTO(repository.findAll());
    }

    public OrganizerResponseDTO getOrganizerById(Long id) {
        OrganizerEntity organizer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        return builder.buildResponseDTO(organizer);
    }

    public OrganizerResponseDTO createOrganizer(OrganizerRequestDTO requestDTO) {
        String validType = TypeEnum.getTypeByName(requestDTO.getType())
                .orElseThrow(() -> new ValidationException("type invalid, only accepted - " + Arrays.toString(TypeEnum.values())))
                .name();

        ClassEntity classEntity = validateIdClass(requestDTO.getIdClass());
        OrganizerEntity organizer = builder.buildEntity(requestDTO, classEntity);
        organizer.setType(validType);
        return builder.buildResponseDTO(repository.save(organizer));

    }

    private ClassEntity validateIdClass(Long idClass) {
        return classRepository.findById(idClass)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    public void deleteOrganizer(Long id) {
        getOrganizerById(id);
        repository.deleteById(id);
    }
}




