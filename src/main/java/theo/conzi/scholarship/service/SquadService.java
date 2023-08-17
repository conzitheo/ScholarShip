package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import theo.conzi.scholarship.builder.SquadBuilder;
import theo.conzi.scholarship.dto.SquadRequestDTO;
import theo.conzi.scholarship.dto.SquadResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.SquadEntity;
import theo.conzi.scholarship.repository.ClassRepository;
import theo.conzi.scholarship.repository.SquadRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SquadService {

    private final SquadRepository repository;
    private final ClassRepository classRepository;
    private final SquadBuilder builder;

    public List<SquadResponseDTO> getAllSquads() {
        return builder.buildListResponseDTO(repository.findAll());
    }

    public SquadResponseDTO getSquadById(Long id) {
        SquadEntity squad = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("squad not found"));
        return builder.buildResponseDTO(squad);
    }

    public SquadResponseDTO createSquad(SquadRequestDTO requestDTO) {
        ClassEntity classEntity = validateIdClass(requestDTO.getIdClass());
        SquadEntity squad = builder.buildEntity(requestDTO, classEntity);
        return builder.buildResponseDTO(repository.save(squad));
    }

    private ClassEntity validateIdClass(Long idClass) {
        return classRepository.findById(idClass)
                .orElseThrow(() -> new EntityNotFoundException("class not found"));
    }

    public void deleteSquad(Long id) {
        getSquadById(id);
        repository.deleteById(id);
    }


}
