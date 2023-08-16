package theo.conzi.scholarship.dto;

import lombok.*;

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
}
