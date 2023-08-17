package theo.conzi.scholarship.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassResponseDTO {

    private Long id;
    private String name;
    private String status;
    private String duration;
    private List<StudentResponseDTO> students;
    private List<OrganizerResponseDTO> organizers;
}
