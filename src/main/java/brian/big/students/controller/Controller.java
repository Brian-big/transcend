package brian.big.students.controller;

import brian.big.students.domain.StudentService;
import brian.big.students.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("studentsController")
@RequestMapping("api/students")
public class Controller {

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> all(){
        return studentService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id){
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(params = {"adm"})
    public ResponseEntity<Student> getStudentByAdmissionNumber(@RequestParam(name = "adm") int admNo){
        Student student = studentService.getStudentByAdmissionNumber(admNo);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student){
        ResponseEntity<Student> response;
        if (studentService.admitStudent(student) != null){
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
        Student student1 = studentService.updateStudent(student);
        if (student1 != null){
            response = new ResponseEntity<>(student1, HttpStatus.OK);
        }else response = new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        return studentService.delete(id);
    }

    @GetMapping(params = {"stream"})
    public ResponseEntity<List<Student>> studentsInStream(@RequestParam(name = "stream") int streamId){
        return studentService.getStudentsInStream(streamId);
    }

    @GetMapping(params = {"form"})
    public ResponseEntity<List<Student>> studentsPerForm(@RequestParam(name = "form") int form){
        return studentService.getStudentsInForm(form);
    }
    @GetMapping("/{id}/promote")
    public ResponseEntity<Student> promote(@PathVariable int id){
        Student student = studentService.promote(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
