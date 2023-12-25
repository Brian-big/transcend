package brianbig.transcend.api.request;

import brianbig.transcend.entities.Student;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record StudentUpdateRequest(@NotBlank String id, @NotBlank String firstName, @NotBlank String lastName,
                                   @NotBlank LocalDate dateOfBirth, @NotBlank String streamId,
                                   @NotBlank LocalDate dateOfAdmission) {
}
