package brianbig.transcend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Teacher extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "employee_number", unique = true)
    private String employeeNumber;

    @Column
    private User user;

    public Teacher() {
    }

    public Teacher(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String firstName, String surname, LocalDate dateOfBirth, String employeeNumber, User user) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.employeeNumber = employeeNumber;
        this.user = user;
    }

    public Teacher(String firstName, String surname, LocalDate dateOfBirth, String employeeNumber, User user) {
        this(null, null, null, firstName, surname, dateOfBirth, employeeNumber, user);
    }

    public Teacher(String firstName, String surname, LocalDate dateOfBirth, String employeeNumber) {
        this(firstName, surname, dateOfBirth, employeeNumber, null);
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
