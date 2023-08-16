package theo.conzi.scholarship.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrganizerResponseDTO {

    private Long id;
    private String name;
    private String type;
    private List<ClassResponseDTO> classes;
}