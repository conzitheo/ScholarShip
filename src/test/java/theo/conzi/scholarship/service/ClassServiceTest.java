//package theo.conzi.scholarship.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import theo.conzi.scholarship.builder.ClassBuilder;
//import theo.conzi.scholarship.dto.ClassResponseDTO;
//import theo.conzi.scholarship.dto.SquadRequestDTO;
//import theo.conzi.scholarship.entity.ClassEntity;
//import theo.conzi.scholarship.repository.ClassRepository;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ClassServiceTest {
//    @InjectMocks
//    private ClassService service;
//
//    @Mock
//    private ClassRepository repository;
//    @Mock
//    private ClassBuilder builder;
//
//    @Test
//    void whenGetAllCLasses_returnSuccess() {
//        when(repository.findAll())
//                .thenReturn(List.of(getClassEntity()));
//        when(builder.buildListResponseDTO(any()))
//                .thenReturn(List.of(getClassResponseDTO()));
//        assertThat(service.getAllClasses())
//                .isNotNull()
//                .isNotEmpty();
//    }
//
//
//    @Test
//    void getClassById() {
//    }
//
//    @Test
//    void createClass() {
//    }
//
//    @Test
//    void deleteClass() {
//    }
//
//    private ClassEntity getClassEntity() {
//        return ClassEntity.builder()
//                .id(1L)
//                .name("name")
//                .status("status")
//                .duration("duration")
//                .build();
//    }
//
//    private ClassResponseDTO getClassResponseDTO() {
//        return ClassResponseDTO.builder()
//                .id(1L)
//                .name("name")
//                .build();
//    }
//
//    private SquadRequestDTO getSquadRequestDTO() {
//        return SquadRequestDTO.builder()
//                .idClass(1L)
//                .name("name")
//                .build();
//    }
//
//}
