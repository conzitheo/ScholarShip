package theo.conzi.scholarship.builder;

import org.springframework.stereotype.Component;
import theo.conzi.scholarship.dto.SquadRequestDTO;
import theo.conzi.scholarship.dto.SquadResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.SquadEntity;

import java.util.List;

@Component
public class SquadBuilder {
    public SquadResponseDTO buildResponseDTO(SquadEntity squad) {
        return SquadResponseDTO.builder()
                .id(squad.getId())
                .name(squad.getName())
                .className(squad.getClassEntity().getName())
                .build();
    }

    public List<SquadResponseDTO> buildListResponseDTO(List<SquadEntity> squads) {
        return squads.stream()
                .map(this::buildResponseDTO)
                .toList();
    }

    public SquadEntity buildEntity(SquadRequestDTO requestDTO, ClassEntity classEntity) {
        return SquadEntity.builder()
                .classEntity(classEntity)
                .name(requestDTO.getName())
                .build();
    }
}
