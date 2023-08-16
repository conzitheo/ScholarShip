package theo.conzi.scholarship.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDTO {

    @NotNull(message = "idSquad is mandatory")
    private Long idSquad;

}
