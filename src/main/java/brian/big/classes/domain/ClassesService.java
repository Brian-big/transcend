package brian.big.classes.domain;

import brian.big.classes.models.Form;
import brian.big.classes.repository.FormRepository;
import brian.big.students.domain.StudentService;
import brian.big.students.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ClassesService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private StudentService studentService;


    public ResponseEntity<List<Form>> getAll(){
        List<Form> forms = formRepository.findAll();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }
    public ResponseEntity<Form> getFormById(long id){
        Form form = formRepository.findById(id)
                .orElseThrow();
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    public ResponseEntity<Form> insert(Form form){
        Form form1 = formRepository.save(form);
        return new ResponseEntity<>(form1, HttpStatus.CREATED);
    }

    @Transactional
    public Form update(Form form){
        Form formById = formRepository.findById(form.getId())
                .orElseThrow(() -> new IllegalStateException(""));
        if (!Objects.equals(formById.getForm(), form.getForm())){
            formById.setForm(form.getForm());
        }
        if (!Objects.equals(formById.getVerboseName(), form.getVerboseName())){
            formById.setVerboseName(form.getVerboseName());
        }
        return formById;
    }

    public ResponseEntity<String> delete(long id){
        formRepository.deleteById(id);
        return new ResponseEntity<>("Form Deleted", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> studentPerForm(int form){
        return studentService.getStudentsInForm(form);
    }

}
