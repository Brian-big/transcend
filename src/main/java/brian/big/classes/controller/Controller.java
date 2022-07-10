package brian.big.classes.controller;

import brian.big.classes.domain.ClassesService;
import brian.big.classes.domain.StreamsService;
import brian.big.classes.models.Form;
import brian.big.classes.models.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("classesController")
@RequestMapping("api/classes")
public class Controller {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private StreamsService streamsService;

    @GetMapping("/forms")
    public ResponseEntity<List<Form>> allForms(){
        return classesService.getAll();
    }

    @GetMapping("/forms/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable long id){
        return classesService.getFormById(id);
    }

    @PostMapping("/forms")
    public ResponseEntity<Form> add(@RequestBody Form form){
        return classesService.insert(form);
    }

    @PutMapping("/forms")
    public Form update(@RequestBody Form form){
        return classesService.update(form);
    }

    @DeleteMapping("/forms/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        return classesService.delete(id);
    }

    @GetMapping("/streams")
    public ResponseEntity<List<Stream>> allStreams(){
        return streamsService.getAll();
    }

    @GetMapping("/streams/{id}")
    public ResponseEntity<Stream> getStreamById(@PathVariable long id){
        return streamsService.getById(id);
    }

    @PostMapping("/streams")
    public ResponseEntity<Stream> add(@RequestBody Stream stream){
        return streamsService.insert(stream);
    }

    @PutMapping("/streams")
    public Stream update(@RequestBody Stream stream){
        return  streamsService.update(stream);
    }

    @DeleteMapping("/streams/{id}")
    public ResponseEntity<String> deleteStream(@PathVariable long id){
        return streamsService.delete(id);
    }
}
