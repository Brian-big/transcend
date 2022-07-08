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


    public Student() {
    }

    public Student(String firstName, String surname, LocalDate dateOfBirth, String admissionNumber, Stream stream, LocalDate dateOfAdmission) {
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.admissionNumber = admissionNumber;
        this.stream = stream;
        this.dateOfAdmission = dateOfAdmission;
    }



    public Student(long id, String firstName, String surname, LocalDate dateOfBirth, String admissionNumber, Stream stream, LocalDate dateOfAdmission) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.admissionNumber = admissionNumber;
        this.stream = stream;
        this.dateOfAdmission = dateOfAdmission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

}
