package brian.big.classes.domain;

import brian.big.admissions.domain.AdmissionService;
import brian.big.classes.models.Stream;
import brian.big.classes.repository.StreamRepository;
import brian.big.students.domain.StudentService;
import brian.big.students.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StreamsService {
    @Autowired
    private StreamRepository streamRepository;
    @Autowired
    private StudentService studentService;

    public ResponseEntity<List<Stream>> getAll(){
        List<Stream> streams = streamRepository.findAll();
        return new ResponseEntity<>(streams, HttpStatus.OK);
    }
    public ResponseEntity<Stream> getById(long id){
        Optional<Stream> stream = streamRepository.findById(id);
        return new ResponseEntity<>(stream.get(), HttpStatus.OK);
    }

    public Stream insert(Stream stream){
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

    public ResponseEntity<String> delete(long id){
        streamRepository.deleteById(id);
        return new ResponseEntity<>("Stream deleted", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getStudentsInStream(int streamId){
        return studentService.getStudentsInStream(streamId);

    }

    public Optional<Stream> getStream(int form, String name) {
        return  streamRepository.findStream(form, name);
    }

}
