package brian.big.classes.domain;

import brian.big.classes.models.Stream;
import brian.big.classes.repository.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StreamsService {
    @Autowired
    private StreamRepository streamRepository;

    public ResponseEntity<List<Stream>> getAll(){
        List<Stream> streams = streamRepository.findAll();
        return new ResponseEntity<>(streams, HttpStatus.OK);
    }
    public ResponseEntity<Stream> getById(long id){
        Stream stream = streamRepository.findById(id)
                .orElseThrow();
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }

    public ResponseEntity<Stream> insert(Stream stream){
        Stream stream1 = streamRepository.save(stream);
        return new ResponseEntity<>(stream1, HttpStatus.OK);
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
}
