package theo.conzi.scholarship.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import theo.conzi.scholarship.dto.ClassRequestDTO;
import theo.conzi.scholarship.dto.ClassResponseDTO;
import theo.conzi.scholarship.service.ClassService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/classes")
public class ClassController {

    private final ClassService service;

    @GetMapping
    public ResponseEntity<List<ClassResponseDTO>> getAllClasses() {
        return new ResponseEntity<>(service.getAllClasses(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassResponseDTO> getClassById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getClassById(id), HttpStatus.OK);
    }

    //
    @PostMapping
    public ResponseEntity<ClassResponseDTO> createClass(@Valid @RequestBody ClassRequestDTO requestDTO) {
        return new ResponseEntity<>(service.createClass(requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable Long id) {
        service.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


