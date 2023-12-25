package brianbig.transcend.api;

import brianbig.transcend.service.ClassesService;
import brianbig.transcend.entities.Form;
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

    @GetMapping("/forms")
    public ResponseEntity<List<Form>> allForms() {
        return classesService.getAll();
    }

    @GetMapping("/forms/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable long id) {
        return classesService.getFormById(id);
    }

    @PostMapping("/forms")
    public ResponseEntity<Form> add(@RequestBody Form form) {
        Form form1 = classesService.insert(form);
        return new ResponseEntity<>(form1, HttpStatus.OK);
    }

    @PutMapping("/forms")
    public Form update(@RequestBody Form form) {
        return classesService.update(form);
    }

    @DeleteMapping("/forms/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return classesService.delete(id);
    }

    @GetMapping("/forms/{id}/students")
    public ResponseEntity<List<Student>> formStudents(@PathVariable int id) {
        return classesService.studentPerForm(id);
    }

    @GetMapping("/streams")
    public ResponseEntity<List<Stream>> allStreams() {
        return classesService.getAllStreams();
    }

    @GetMapping("/streams/{id}")
    public ResponseEntity<Stream> getStreamById(@PathVariable long id) {
        return classesService.getStreamById(id);
    }

    @PostMapping("/streams")
    public ResponseEntity<Stream> add(@RequestBody Stream stream) {
        Stream stream1 = classesService.addStream(stream);
        return new ResponseEntity<>(stream1, HttpStatus.OK);
    }

    @PutMapping("/streams")
    public Stream update(@RequestBody Stream stream) {
        return classesService.update(stream);
    }

    @DeleteMapping("/streams/{id}")
    public ResponseEntity<String> deleteStream(@PathVariable long id) {
        return classesService.delete(id);
    }

    @GetMapping("/streams/{id}/students")
    public ResponseEntity<List<Student>> streamStudents(@PathVariable int id) {
        return classesService.getStudentsInStream(id);
    }
}
