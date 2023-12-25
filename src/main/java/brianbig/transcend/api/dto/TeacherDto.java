package brianbig.transcend.api.dto;

import brianbig.transcend.entities.Teacher;

import java.time.LocalDate;

public record TeacherDto(String id, String firstNam, String surname, LocalDate dateOfBirth, String employeeNumber) {

    public static TeacherDto from(Teacher teacher) {
        return new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getSurname(), teacher.getDateOfBirth(), teacher.getEmployeeNumber());
    }
}
