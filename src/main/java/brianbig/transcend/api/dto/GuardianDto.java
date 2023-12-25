package brianbig.transcend.api.dto;

import brianbig.transcend.entities.Guardian;

import java.time.LocalDate;

public record GuardianDto(String id, String firstName, String surname, LocalDate dateOfBirth, int nationalId,
                          String occupation, String telephone) {

    public static GuardianDto from(Guardian guardian) {
        return new GuardianDto(guardian.getId(), guardian.getFirstName(), guardian.getSurname(), guardian.getDateOfBirth(),
                guardian.getNationalId(), guardian.getOccupation(), guardian.getTelephone());
    }
}
