package theo.conzi.scholarship.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrganizerResponseDTO {

    private Long id;
    private String name;
    private String type;
}