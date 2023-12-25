package brianbig.transcend.service;

import brianbig.transcend.entities.Form;
import brianbig.transcend.entities.Stream;
import brianbig.transcend.repository.FormRepository;
import brianbig.transcend.repository.StreamRepository;
import brianbig.transcend.service.StudentService;
import brianbig.transcend.entities.Student;
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
    private final StreamRepository streamRepository;

    @Autowired
    public ClassesService(FormRepository formRepository, StudentService studentService, StreamRepository streamRepository) {
        this.formRepository = formRepository;
        this.studentService = studentService;
        this.streamRepository = streamRepository;
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

    public ResponseEntity<List<Stream>> getAllStreams() {
        List<Stream> streams = streamRepository.findAll();
        return new ResponseEntity<>(streams, HttpStatus.OK);
    }

    public ResponseEntity<Stream> getStreamById(long id) {
        Optional<Stream> stream = streamRepository.findById(id);
        return new ResponseEntity<>(stream.get(), HttpStatus.OK);
    }

    public Stream addStream(Stream stream) {
        return streamRepository.save(stream);
    }

    @Transactional
    public Stream update(Stream stream) {
        Stream streamById = streamRepository.findById(stream.getId())
                .orElseThrow(() -> new IllegalStateException(""));
        if (!Objects.equals(streamById.getForm(), stream.getForm())) {
            streamById.setForm(stream.getForm());
        }
        if (!Objects.equals(streamById.getName(), stream.getName())) {
            streamById.setName(stream.getName());
        }

        return streamById;
    }

    public ResponseEntity<String> deleteStream(long id) {
        streamRepository.deleteById(id);
        return new ResponseEntity<>("Stream deleted", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getStudentsInStream(int streamId) {
        return studentService.getStudentsInStream(streamId);

    }

    public Optional<Stream> getStream(int form, String name) {
        return streamRepository.findStream(form, name);
    }

}
