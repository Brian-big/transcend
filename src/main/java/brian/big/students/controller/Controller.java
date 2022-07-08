package brian.big.students.controller;

import brian.big.students.models.Student;
import brian.big.students.repository.GuardianRepository;
import brian.big.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("studentsController")
@RequestMapping("api/students")
public class Controller {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private GuardianRepository guardianRepo;

    @GetMapping
    public List<Student> all(){
        return studentRepo.findAll();
    }

    @PostMapping
    public Student add(@RequestBody Student student){
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setSurname(student.getSurname());
        student1.setDateOfBirth(LocalDate.of(2000, 1, 1));
        student1.setAdmissionNumber(student.getAdmissionNumber());
        return studentRepo.save(student1);
    }
}
