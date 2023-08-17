package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import theo.conzi.scholarship.builder.SquadBuilder;
import theo.conzi.scholarship.dto.SquadRequestDTO;
import theo.conzi.scholarship.dto.SquadResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.SquadEntity;
import theo.conzi.scholarship.repository.ClassRepository;
import theo.conzi.scholarship.repository.SquadRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SquadServiceTest {
    @InjectMocks
    private SquadService service;

    @Mock
    private SquadRepository repository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private SquadBuilder builder;

    @Test
    void whenGetAllSquads_returnSuccess() {
        when(repository.findAll())
                .thenReturn(List.of(getSquadEntity()));
        when(builder.buildListResponseDTO(any()))
                .thenReturn(List.of(getSquadResponseDTO()));
        assertThat(service.getAllSquads())
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void whenGetSquadById_returnSuccess() {
        SquadEntity squadEntity = getSquadEntity();
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(squadEntity));
        when(builder.buildResponseDTO(any(SquadEntity.class)))
                .thenReturn(getSquadResponseDTO());
        assertThat(service.getSquadById(anyLong()))
                .isNotNull();
    }

    @Test
    void whenGetSquadById_returnError() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getSquadById(anyLong()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("squad not found");
    }

    @Test
    void whenCreateSquad_returnError() {
        when(classRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.createSquad(getSquadRequestDTO()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("class not found");
    }

    @Test
    void whenCreateSquad_returnSuccess() {
        when(classRepository.findById(anyLong()))
                .thenReturn(Optional.of(getClassEntity()));
        when(builder.buildEntity(any(), any()))
                .thenReturn(getSquadEntity());
        when(builder.buildResponseDTO(any()))
                .thenReturn(getSquadResponseDTO());
        assertNotNull(service.createSquad(getSquadRequestDTO()));
    }

    @Test
    void whenDeleteSquad_returnSuccess() {
        long id = anyLong();
        when(repository.findById(id))
                .thenReturn(Optional.of(getSquadEntity()));
        service.deleteSquad(id);
        verify(repository, times(1)).deleteById(id);
    }

    private SquadEntity getSquadEntity() {
        return SquadEntity.builder()
                .id(1L)
                .name("name")
                .build();
    }

    private SquadResponseDTO getSquadResponseDTO() {
        return SquadResponseDTO.builder()
                .id(1L)
                .name("name")
                .build();
    }

    private SquadRequestDTO getSquadRequestDTO() {
        return SquadRequestDTO.builder()
                .idClass(1L)
                .name("name")
                .build();
    }

    private ClassEntity getClassEntity() {
        return ClassEntity.builder()
                .id(1L)
                .name("name")
                .status("status")
                .duration("duration")
                .build();
    }
}