package brianbig.transcend.service;

import brianbig.transcend.entities.Stream;
import brianbig.transcend.entities.Student;
import brianbig.transcend.entities.enums.ClassForm;
import brianbig.transcend.repository.StreamRepository;
import brianbig.transcend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdmissionService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final StudentRepository studentRepository;
    private final StreamRepository streamRepository;

    @Autowired
    @Lazy
    public AdmissionService(StudentRepository studentRepository, StreamRepository streamRepository) {

        this.studentRepository = studentRepository;
        this.streamRepository = streamRepository;
    }

    @Transactional
    public Optional<Student> promoteStudent(String id) {
        var optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            log.debug("Student with ID: {} does not exist", id);
            throw new IllegalStateException("Student with given id does not exist");
        }

        Stream nextStream = handleStreamPromotion(optionalStudent.get().getStream());

        optionalStudent.get().setStream(nextStream);
        optionalStudent.ifPresent(studentRepository::saveAndFlush);
        return optionalStudent;
    }

    private Stream handleStreamPromotion(Stream currentStream) {
        ClassForm nextForm = getNextForm(currentStream.getForm());
        Optional<Stream> streamNext;

        //todo: recheck this to avoid possible null pointer exceptions
        if (nextForm == null)
            return null;

        streamNext = streamRepository.findByFormAndNameIgnoreCase(nextForm, currentStream.getName());
        if (streamNext.isPresent()) {
            return streamNext.get();
        } else {
            Stream promotedStream = new Stream(nextForm, currentStream.getName());
            promotedStream = streamRepository.saveAndFlush(promotedStream);
            return promotedStream;
        }

    }

    private ClassForm getNextForm(ClassForm currentForm) {
        ClassForm newForm;
        int currForm = ClassForm.form(currentForm);
        ClassForm formNext;
        if (currForm >= 4) {
            return null;
        } else {
            currForm++;
            newForm = ClassForm.from(currForm);
        }

        return newForm;

    }


}
