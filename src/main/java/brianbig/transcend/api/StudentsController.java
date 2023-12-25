package brianbig.transcend.api;

import brianbig.transcend.api.dto.StudentDto;
import brianbig.transcend.api.request.StudentCreateRequest;
import brianbig.transcend.api.request.StudentUpdateRequest;
import brianbig.transcend.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<StudentDto>> all() {
        var students = studentService.all().stream().map(StudentDto::from).toList();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id).map(StudentDto::from).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"adm"})
    public ResponseEntity<StudentDto> getStudentByAdmissionNumber(@RequestParam(name = "adm") int admNo) {
        return studentService.getStudentByAdmissionNumber(admNo).map(StudentDto::from).map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<StudentDto> add(@RequestBody StudentCreateRequest request) {
        var optionalStudent = studentService.admitStudent(request);

        return optionalStudent.map(StudentDto::from).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.of(
                ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        ).build());

    }

    @PutMapping
    public ResponseEntity<StudentDto> edit(@RequestBody StudentUpdateRequest request) {
        return studentService.updateStudent(request).map(StudentDto::from).map(ResponseEntity::ok)
                .orElse(ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)).build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.ok("item deleted");
    }

    @GetMapping(params = {"stream"})
    public ResponseEntity<List<StudentDto>> studentsInStream(@RequestParam(name = "stream") String streamId) {
        var students = studentService.getStudentsInStream(streamId).stream().map(StudentDto::from).toList();
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = {"form"})
    public ResponseEntity<List<StudentDto>> studentsPerForm(@RequestParam(name = "form") int form) {
        var students = studentService.getStudentsInForm(form).stream().map(StudentDto::from).toList();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/promote")
    public ResponseEntity<StudentDto> promote(@PathVariable String id) {
        return studentService.promote(id).map(StudentDto::from).map(ResponseEntity::ok)
                .orElse(ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)).build());
    }
}
