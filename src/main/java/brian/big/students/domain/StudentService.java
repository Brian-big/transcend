package brian.big.students.domain;

import brian.big.students.models.Student;
import brian.big.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service("admissionService")
public class StudentService {

    private static final int startAdmNo = 1000;

    @Autowired
    private StudentRepository repo;

    public ResponseEntity<List<Student>> all(){
        List<Student> students = repo.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public Student admitStudent(Student student){
        Student lastStudent = repo.findTopByOrderByAdmissionNumberDesc();
        int lastAdmNo;
        if (lastStudent != null){
            lastAdmNo = lastStudent.getAdmissionNumber();
        }
        else lastAdmNo = startAdmNo;
        System.out.println("DEBUG:INSERT ------- last admission Number: "+ lastAdmNo);
        lastAdmNo++;
        System.out.println("DEBUG:INSERT ------- new admission Number: "+ lastAdmNo);
        student.setAdmissionNumber(lastAdmNo);

        Optional<Student> studentByAdmNo = repo.
                findByAdmissionNumber(student.getAdmissionNumber());
        if (studentByAdmNo.isPresent()){
            throw new IllegalStateException("admission number exists");
        }

        if (student.getDateOfBirth() == null){
            student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        }
        if (student.getDateOfAdmission() == null){
            student.setDateOfAdmission(LocalDate.now());
        }

        repo.save(student);

        return student;
    }

    public Student getStudentById(long id){
        Student student = repo.findById(id)
                .orElseThrow(() -> new IllegalStateException("No student with given id found"));
        return student;
    }

    @Transactional
    public Student updateStudent(Student student){
        if (!student.getFirstName().isBlank() &&
                !student.getSurname().isBlank() &&
                student.getDateOfBirth() != null &&
                student.getDateOfAdmission() !=null
        ){
            Student studentById = repo.findById(student.getId())
                    .orElseThrow(() -> new IllegalStateException(""));

            if (!Objects.equals(studentById.getFirstName(), student.getFirstName())){
                studentById.setFirstName(student.getFirstName());
            }
            if (!Objects.equals(studentById.getSurname(), student.getSurname())){
                studentById.setSurname(student.getSurname());
            }
            if (!Objects.equals(studentById.getDateOfBirth(), student.getDateOfBirth())){
                studentById.setDateOfBirth(student.getDateOfBirth());
            }
            if (!Objects.equals(studentById.getDateOfAdmission(), student.getDateOfAdmission())){
                studentById.setDateOfAdmission(student.getDateOfAdmission());
            }

        }
        return student;
    }

    public ResponseEntity<String> delete(long id) {
        ResponseEntity<String> response;
        Optional<Student> student = repo.findById(id);
        if (student.isPresent()){
            repo.deleteById(id);
            response = new ResponseEntity<>("OPERATION: Delete success!", HttpStatus.OK);
        }
        else response = new ResponseEntity<>("Student with id not found!", HttpStatus.NOT_FOUND);
        return response;
    }
}
