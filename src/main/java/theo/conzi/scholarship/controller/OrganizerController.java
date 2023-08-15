package theo.conzi.scholarship.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import theo.conzi.scholarship.dto.OrganizerRequestDTO;
import theo.conzi.scholarship.dto.OrganizerResponseDTO;
import theo.conzi.scholarship.service.OrganizerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organizers")
public class OrganizerController {

    private final OrganizerService service;

    @GetMapping
    public ResponseEntity<List<OrganizerResponseDTO>> getAllOrganizers() {
        return new ResponseEntity<>(service.getAllOrganizers(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrganizerResponseDTO> getOrganizerById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOrganizerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizerResponseDTO> createOrganizer(@Valid @RequestBody OrganizerRequestDTO requestDTO) {
        return new ResponseEntity<>(service.createOrganizer(requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteOrganizer(@PathVariable Long id) {
        service.deleteOrganizer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}