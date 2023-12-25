package brianbig.transcend.api.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record StudentCreateRequest(@NotBlank String firstName, @NotBlank String lastName,
                                   @NotBlank LocalDate dateOfBirth, @NotBlank String streamId,
                                   LocalDate dateOfAdmission) {
}
