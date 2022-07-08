package brian.big.students.domain;

import brian.big.students.models.Student;
import brian.big.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service("admissionService")
public class AdmissionService {

    private static final int startAdmNo = 1000;

    @Autowired
    private StudentRepository repo;
    public Student admitStudent(Student student){
        Student lastStudent = repo.findTopByOrderByIdDesc();
        int lastAdmNo;
        if (lastStudent != null){
            lastAdmNo = lastStudent.getAdmissionNumber();
        }
        else lastAdmNo = startAdmNo;

        lastAdmNo++;
        student.setAdmissionNumber(lastAdmNo);
        if (student.getDateOfBirth() == null){
            student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        }
        if (student.getDateOfAdmission() == null){
            student.setDateOfAdmission(LocalDate.now());
        }

        repo.save(student);

        return student;
    }
    public Student editStudent(Student student){
        if (!student.getFirstName().isBlank() &&
                !student.getSurname().isBlank() &&
                student.getDateOfBirth() != null &&
                student.getDateOfAdmission() !=null
        ){
            repo.save(student);

        }
        return student;
    }
}
