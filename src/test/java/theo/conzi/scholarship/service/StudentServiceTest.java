package theo.conzi.scholarship.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import theo.conzi.scholarship.builder.StudentBuilder;
import theo.conzi.scholarship.dto.StudentRequestDTO;
import theo.conzi.scholarship.dto.StudentResponseDTO;
import theo.conzi.scholarship.entity.ClassEntity;
import theo.conzi.scholarship.entity.StudentEntity;
import theo.conzi.scholarship.repository.ClassRepository;
import theo.conzi.scholarship.repository.SquadRepository;
import theo.conzi.scholarship.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @InjectMocks
    private StudentService service;
    @Mock
    private StudentRepository repository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private SquadRepository squadRepository;
    @Mock
    private StudentBuilder builder;


    @Test
    void whenGetAllStudents_returnSuccess() {
        when(repository.findAll())
                .thenReturn(List.of(getStudentEntity()));
        when(builder.buildListResponseDTO(any()))
                .thenReturn(List.of(getStudentResponseDTO()));
        assertThat(service.getAllStudents())
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void whenGetStudentsById_returnSuccess() {
        StudentEntity studentEntity = getStudentEntity();
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(studentEntity));
        when(builder.buildResponseDTO(any(StudentEntity.class)))
                .thenReturn(getStudentResponseDTO());
        assertThat(service.getStudentById(anyLong()))
                .isNotNull();
    }

    @Test
    void whenGetStudentById_returnError() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getStudentById(anyLong()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("student not found");
    }

    @Test
    void whenCreateStudent_returnError() {
        when(classRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.createStudent(getStudentRequestDTO()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("class not found");
    }

    @Test
    void whenCreateStudent_returnSuccess() {
        when(classRepository.findById(anyLong()))
                .thenReturn(Optional.of(getClassEntity()));
        when(builder.buildEntity(any(), any()))
                .thenReturn(getStudentEntity());
        when(builder.buildResponseDTO(any()))
                .thenReturn(getStudentResponseDTO());
        assertNotNull(service.createStudent(getStudentRequestDTO()));
    }

    @Test
    void whenDeleteStudent_returnSuccess() {
        long id = anyLong();
        when(repository.findById(id))
                .thenReturn(Optional.of(getStudentEntity()));
        service.deleteStudent(id);
        verify(repository, times(1)).deleteById(id);
    }

    private StudentEntity getStudentEntity() {
        return StudentEntity.builder()
                .id(1L)
                .name("name")
                .build();
    }

    private StudentResponseDTO getStudentResponseDTO() {
        return StudentResponseDTO.builder()
                .id(1L)
                .name("name")
                .className("className")
                .squadName("squadName")
                .build();
    }


    private StudentRequestDTO getStudentRequestDTO() {
        return StudentRequestDTO.builder()
                .idClass(1L)
                .name("name")
                .build();
    }

    private ClassEntity getClassEntity() {
        return ClassEntity.builder()
                .id(1L)
                .name("name")
                .status("waiting")
                .duration("duration")
                .build();
    }
}
