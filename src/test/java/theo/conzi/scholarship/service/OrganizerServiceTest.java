package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizerServiceTest {
    @InjectMocks
    private OrganizerService service;

    @Mock
    private OrganizerRepository repository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private OrganizerBuilder builder;

    @Test
    void whenGetAllOrganizers_returnSuccess() {
        when(repository.findAll())
                .thenReturn(List.of(getOrganizerEntity()));
        when(builder.buildListResponseDTO(any()))
                .thenReturn(List.of(getOrganizerResponseDTO()));
        assertThat(service.getAllOrganizers())
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void whenGetOrganizerById_returnSuccess() {
        OrganizerEntity organizerEntity = getOrganizerEntity();
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(organizerEntity));
        when(builder.buildResponseDTO(any(OrganizerEntity.class)))
                .thenReturn(getOrganizerResponseDTO());
        assertThat(service.getOrganizerById(anyLong()))
                .isNotNull();
    }

    @Test
    void whenGetOrganizerById_returnError() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getOrganizerById(anyLong()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("organizer not found");
    }

    @Test
    void whenCreateOrganizer_returnError() {
        when(classRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.createOrganizer(getOrganizerRequestDTO()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("class not found");
    }

    @Test
    void whenCreateOrganizer_returnTypeError() {
        OrganizerRequestDTO organizerRequestDTO = getOrganizerRequestDTO();
        organizerRequestDTO.setType("type");
        assertThatThrownBy(() -> service.createOrganizer(organizerRequestDTO))
                .isInstanceOf(ValidationException.class)
                .hasMessage("type invalid, only accepted - " + Arrays.toString(TypeEnum.values()));
    }

    @Test
    void whenCreateOrganizer_returnSuccess() {
        when(classRepository.findById(anyLong()))
                .thenReturn(Optional.of(getClassEntity()));
        when(builder.buildEntity(any(), any()))
                .thenReturn(getOrganizerEntity());
        when(builder.buildResponseDTO(any()))
                .thenReturn(getOrganizerResponseDTO());
        assertNotNull(service.createOrganizer(getOrganizerRequestDTO()));
    }

    @Test
    void whenDeleteOrganizer_returnSuccess() {
        long id = anyLong();
        when(repository.findById(id))
                .thenReturn(Optional.of(getOrganizerEntity()));
        service.deleteOrganizer(id);
        verify(repository, times(1)).deleteById(id);
    }

    private OrganizerEntity getOrganizerEntity() {
        return OrganizerEntity.builder()
                .id(1L)
                .name("name")
                .type(TypeEnum.COORDINATOR.name())
                .build();
    }

    private OrganizerResponseDTO getOrganizerResponseDTO() {
        return OrganizerResponseDTO.builder()
                .id(1L)
                .name("name")
                .type(TypeEnum.COORDINATOR.name())
                .build();
    }

    private OrganizerRequestDTO getOrganizerRequestDTO() {
        return OrganizerRequestDTO.builder()
                .idClass(1L)
                .name("name")
                .type(TypeEnum.COORDINATOR.name())
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