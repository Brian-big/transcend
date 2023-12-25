package brianbig.transcend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
public class Student extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Transient
    private Integer age;

    @Column(name = "admission_number", unique = true)
    private int admissionNumber;
    @ManyToOne(optional = false)
    private Stream stream;
    @Column(name = "date_of_admission")
    private LocalDate dateOfAdmission;

    @Column
    private User userAccount;

    public Student() {
    }

    public Student(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String firstName, String lastName, LocalDate dateOfBirth, Integer age, int admissionNumber, Stream stream, LocalDate dateOfAdmission, User userAccount) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.admissionNumber = admissionNumber;
        this.stream = stream;
        this.dateOfAdmission = dateOfAdmission;
        this.userAccount = userAccount;
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, int admissionNumber, Stream stream, LocalDate dateOfAdmission, User userAccount) {
        this(null, null, null, firstName, lastName, dateOfBirth, null, admissionNumber, stream, dateOfAdmission, userAccount);
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, int admissionNumber, Stream stream, LocalDate dateOfAdmission) {
        this(firstName, lastName, dateOfBirth, admissionNumber, stream, dateOfAdmission, null);
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, int admissionNumber, Stream stream) {
        this(firstName, lastName, dateOfBirth, admissionNumber, stream, null);
    }

    @Override
    protected void prePersist() {
        super.prePersist();
        if (getDateOfBirth() == null) {
            setDateOfBirth(LocalDate.of(2000, 1, 1));
        }
        if (getDateOfAdmission() == null) {
            setDateOfAdmission(LocalDate.now());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(int admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
