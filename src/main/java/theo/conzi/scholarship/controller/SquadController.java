package theo.conzi.scholarship.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import theo.conzi.scholarship.dto.SquadRequestDTO;
import theo.conzi.scholarship.dto.SquadResponseDTO;
import theo.conzi.scholarship.service.SquadService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/squads")
public class SquadController {

    private final SquadService service;

    @GetMapping
    public ResponseEntity<List<SquadResponseDTO>> getAllSquads() {
        return new ResponseEntity<>(service.getAllSquads(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SquadResponseDTO> getSquadById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getSquadById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SquadResponseDTO> createSquad(@Valid @RequestBody SquadRequestDTO requestDTO) {
        return new ResponseEntity<>(service.createSquad(requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSquad(@PathVariable Long id) {
        service.deleteSquad(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
