package brianbig.transcend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Guardian extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "national_id")
    private int nationalId;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "telephone")
    private String telephone;

    public Guardian() {
    }

    public Guardian(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String firstName, String surname, LocalDate dateOfBirth, int nationalId, String occupation, String telephone) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.nationalId = nationalId;
        this.occupation = occupation;
        this.telephone = telephone;
    }

    public Guardian(String firstName, String surname, LocalDate dateOfBirth, int nationalId, String occupation, String telephone) {
        this(null, null, null, firstName, surname, dateOfBirth, nationalId, occupation, telephone);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }
}
