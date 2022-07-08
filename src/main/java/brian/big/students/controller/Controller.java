package brian.big.students.controller;

import brian.big.students.domain.AdmissionService;
import brian.big.students.models.Student;
import brian.big.students.repository.GuardianRepository;
import brian.big.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("studentsController")
@RequestMapping("api/students")
public class Controller {

    @Autowired
    AdmissionService admissionService;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private GuardianRepository guardianRepo;

    @GetMapping
    public List<Student> all(){
        return studentRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student){
        ResponseEntity<Student> response;
        if (admissionService.admitStudent(student) != null){
            response = new ResponseEntity<>(student, HttpStatus.CREATED);
        }
        else {
            response = new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }
    @PutMapping
    public ResponseEntity<Student> edit(@RequestBody Student student){
        ResponseEntity<Student> response;
        Student student1 = admissionService.updateStudent(student);
        if (student1 != null){
            response = new ResponseEntity<>(student1, HttpStatus.OK);
        }else response = new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
