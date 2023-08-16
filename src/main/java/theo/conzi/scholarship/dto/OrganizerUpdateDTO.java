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
public class OrganizerUpdateDTO {

    @NotNull(message = "idClass is mandatory")
    private Long idClass;

}