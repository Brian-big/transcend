package brianbig.transcend.service;

import brianbig.transcend.entities.Stream;
import brianbig.transcend.entities.enums.ClassForm;
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
    private final StudentService studentService;
    private final StreamRepository streamRepository;

    @Autowired
    public ClassesService(StudentService studentService, StreamRepository streamRepository) {
        this.studentService = studentService;
        this.streamRepository = streamRepository;
    }

    public ResponseEntity<List<Student>> studentPerForm(int form) {
        return studentService.getStudentsInForm(form);
    }


    public ResponseEntity<List<Stream>> getAllStreams() {
        List<Stream> streams = streamRepository.findAll();
        return new ResponseEntity<>(streams, HttpStatus.OK);
    }

    public ResponseEntity<Stream> getStreamById(String id) {
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

    public ResponseEntity<String> deleteStream(String id) {
        streamRepository.deleteById(id);
        return new ResponseEntity<>("Stream deleted", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getStudentsInStream(int streamId) {
        return studentService.getStudentsInStream(streamId);

    }

    public Optional<Stream> getStream(String form, String name) {
        return streamRepository.findByFormAndNameIgnoreCase(ClassForm.from(form), name);
    }

}
