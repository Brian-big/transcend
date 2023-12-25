package brianbig.transcend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Teacher {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "employee_number", unique = true)
    private String employeeNumber;

}
