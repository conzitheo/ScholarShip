package theo.conzi.scholarship.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import theo.conzi.scholarship.dto.StudentRequestDTO;
import theo.conzi.scholarship.dto.StudentResponseDTO;
import theo.conzi.scholarship.service.StudentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/students")
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO requestDTO) {
        return new ResponseEntity<>(service.createStudent(requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}








