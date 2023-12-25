package brianbig.transcend.classes.domain;

import brianbig.transcend.classes.models.Form;
import brianbig.transcend.classes.repository.FormRepository;
import brianbig.transcend.students.domain.StudentService;
import brianbig.transcend.students.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClassesService {

    private final FormRepository formRepository;

    private final StudentService studentService;

    @Autowired
    public ClassesService(FormRepository formRepository, StudentService studentService) {
        this.formRepository = formRepository;
        this.studentService = studentService;
    }


    public ResponseEntity<List<Form>> getAll() {
        List<Form> forms = formRepository.findAll();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    public ResponseEntity<Form> getFormById(long id) {
        Optional<Form> form = formRepository.findById(id);
        return new ResponseEntity<>(form.get(), HttpStatus.OK);
    }

    public Form insert(Form form) {
        return formRepository.save(form);
    }

    @Transactional
    public Form update(Form form) {
        Form formById = formRepository.findById(form.getId())
                .orElseThrow(() -> new IllegalStateException(""));
        if (!Objects.equals(formById.getForm(), form.getForm())) {
            formById.setForm(form.getForm());
        }
        if (!Objects.equals(formById.getVerboseName(), form.getVerboseName())) {
            formById.setVerboseName(form.getVerboseName());
        }
        return formById;
    }

    public ResponseEntity<String> delete(long id) {
        formRepository.deleteById(id);
        return new ResponseEntity<>("Form Deleted", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> studentPerForm(int form) {
        return studentService.getStudentsInForm(form);
    }

    public Form getForm(int currentForm) {
        return formRepository.findByForm(currentForm);
    }
}
