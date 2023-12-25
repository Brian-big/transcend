package brianbig.transcend.api.dto;

import brianbig.transcend.entities.Student;

import java.time.LocalDate;

public record StudentDto(String id, String firstName, String lastName, LocalDate dateOfBirth, int age,
                         int admissionNumber, String form, String streamName) {

    public static StudentDto from(Student student) {
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getAge(),
                student.getAdmissionNumber(), student.getStream().getForm().name(), student.getStream().getName());
    }
}
