package brianbig.transcend.service;

import brianbig.transcend.api.request.StreamCreateRequest;
import brianbig.transcend.api.request.StreamUpdateRequest;
import brianbig.transcend.entities.Stream;
import brianbig.transcend.entities.Student;
import brianbig.transcend.entities.enums.ClassForm;
import brianbig.transcend.repository.StreamRepository;
import brianbig.transcend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClassesService {
    private final StudentRepository studentRepository;
    private final StreamRepository streamRepository;


    @Autowired
    public ClassesService(StudentRepository studentRepository, StreamRepository streamRepository) {
        this.studentRepository = studentRepository;
        this.streamRepository = streamRepository;
    }


    public List<Student> studentPerForm(int form) {
        return studentRepository.studentsInForm(form);
    }


    public List<Stream> getAllStreams() {
        return streamRepository.findAll();
    }

    public Optional<Stream> getStreamById(String id) {
        return streamRepository.findById(id);
    }

    public Optional<Stream> addStream(StreamCreateRequest request) {
        var stream = new Stream(request.classForm(), request.name());
        return Optional.of(streamRepository.save(stream));
    }

    @Transactional
    public Optional<Stream> update(StreamUpdateRequest stream) {
        var optionalStream = streamRepository.findById(stream.id());
        if (optionalStream.isEmpty()) {
            throw new IllegalStateException("");
        }
        if (!Objects.equals(optionalStream.get().getForm(), stream.classForm())) {
            optionalStream.get().setForm(stream.classForm());
        }
        if (!Objects.equals(optionalStream.get().getName(), stream.name())) {
            optionalStream.get().setName(stream.name());
        }

        optionalStream.ifPresent(streamRepository::saveAndFlush);
        return optionalStream;

    }

    public void deleteStream(String id) {
        streamRepository.deleteById(id);
    }

    public List<Student> getStudentsInStream(String streamId) {
        return studentRepository.studentsInStream(streamId);

    }

    public Optional<Stream> getStream(String form, String name) {
        return streamRepository.findByFormAndNameIgnoreCase(ClassForm.from(form), name);
    }

}
