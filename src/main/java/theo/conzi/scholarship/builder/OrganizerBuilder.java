package theo.conzi.scholarship.builder;

import org.springframework.stereotype.Component;
import theo.conzi.scholarship.dto.OrganizerRequestDTO;
import theo.conzi.scholarship.dto.OrganizerResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.OrganizerEntity;

import java.util.List;

@Component
public class OrganizerBuilder {

    public OrganizerResponseDTO buildResponseDTO(OrganizerEntity organizer) {
        return OrganizerResponseDTO.builder()
                .id(organizer.getId())
                .name(organizer.getName())
                .type(organizer.getType())
                .build();
    }

    public List<OrganizerResponseDTO> buildListResponseDTO(List<OrganizerEntity> organizers) {
        return organizers.stream()
                .map(this::buildResponseDTO)
                .toList();
    }

    public OrganizerEntity buildEntity(OrganizerRequestDTO requestDTO, ClassEntity classEntity) {
        return OrganizerEntity.builder()
                .name(requestDTO.getName())
                .classes(List.of(classEntity))
                .type(requestDTO.getType())
                .build();
    }

}
