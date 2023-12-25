package brianbig.transcend.api;

import brianbig.transcend.api.dto.StreamDto;
import brianbig.transcend.api.dto.StudentDto;
import brianbig.transcend.api.request.StreamCreateRequest;
import brianbig.transcend.api.request.StreamUpdateRequest;
import brianbig.transcend.service.ClassesService;
import brianbig.transcend.entities.Stream;
import brianbig.transcend.entities.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("classesController")
@RequestMapping("api/classes")
@Tag(name = "Classes & Streams")
public class ClassesController {

    private final ClassesService classesService;


    @Autowired
    @Lazy
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("/forms/{id}/students")
    public ResponseEntity<List<StudentDto>> formStudents(@PathVariable int id) {
        var students = classesService.studentPerForm(id).stream().map(StudentDto::from).toList();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/streams")
    public ResponseEntity<List<StreamDto>> allStreams() {
        var allStreams = classesService.getAllStreams().stream().map(StreamDto::from).toList();
        return ResponseEntity.ok(allStreams);
    }

    @GetMapping("/streams/{id}")
    public ResponseEntity<StreamDto> getStreamById(@PathVariable String id) {
        return classesService.getStreamById(id).map(StreamDto::from).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/streams")
    public ResponseEntity<StreamDto> add(@RequestBody StreamCreateRequest request) {
        return classesService.addStream(request).map(StreamDto::from).map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());

    }

    @PutMapping("/streams")
    public ResponseEntity<StreamDto> update(@RequestBody StreamUpdateRequest request) {
        return classesService.update(request).map(StreamDto::from).map(ResponseEntity::ok).orElse(
                ResponseEntity.noContent().build()
        );
    }

    @DeleteMapping("/streams/{id}")
    public ResponseEntity<String> deleteStream(@PathVariable String id) {
        classesService.deleteStream(id);
        return ResponseEntity.ok("Stream deleted");
    }

    @GetMapping("/streams/{id}/students")
    public ResponseEntity<List<StudentDto>> streamStudents(@PathVariable String id) {
        var students = classesService.getStudentsInStream(id).stream().map(StudentDto::from).toList();
        return ResponseEntity.ok(students);
    }
}
