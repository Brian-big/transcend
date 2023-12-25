package brianbig.transcend.api;

import brianbig.transcend.api.dto.StudentDto;
import brianbig.transcend.api.request.StudentCreateRequest;
import brianbig.transcend.service.StudentService;
import brianbig.transcend.entities.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("studentsController")
@RequestMapping("api/students")
@Tag(name = "Students")
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    @Lazy
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> all() {
        return studentService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(params = {"adm"})
    public ResponseEntity<Student> getStudentByAdmissionNumber(@RequestParam(name = "adm") int admNo) {
        Student student = studentService.getStudentByAdmissionNumber(admNo);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDto> add(@RequestBody StudentCreateRequest request) {

        Optional<Student> optionalStudent = studentService.admitStudent(request);

        return optionalStudent.map(StudentDto::from).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.of(
                ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        ).build());

    }

    @PutMapping
    public ResponseEntity<Student> edit(@RequestBody Student student) {
        ResponseEntity<Student> response;
        Student student1 = studentService.updateStudent(student);
        if (student1 != null) {
            response = new ResponseEntity<>(student1, HttpStatus.OK);
        } else response = new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return studentService.delete(id);
    }

    @GetMapping(params = {"stream"})
    public ResponseEntity<List<Student>> studentsInStream(@RequestParam(name = "stream") int streamId) {
        return studentService.getStudentsInStream(streamId);
    }

    @GetMapping(params = {"form"})
    public ResponseEntity<List<Student>> studentsPerForm(@RequestParam(name = "form") int form) {
        return studentService.getStudentsInForm(form);
    }

    @GetMapping("/{id}/promote")
    public ResponseEntity<Student> promote(@PathVariable String id) {
        Student student = studentService.promote(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
