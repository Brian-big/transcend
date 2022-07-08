package brian.big.students.models;

import brian.big.classes.models.Stream;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student{

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "admission_number", unique = true)
    private String admissionNumber;
    @ManyToOne
    private Stream stream;
    @Column(name="date_of_admission")
    private LocalDate dateOfAdmission;

    @OneToOne
    @JoinColumn(name = "prefect_id")
    private Prefect prefect;

    public Prefect getPrefect() {
        return prefect;
    }

    public void setPrefect(Prefect prefect) {
        this.prefect = prefect;
    }
}
