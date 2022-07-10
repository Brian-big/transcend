package brian.big.classes.repository;

import brian.big.classes.models.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StreamRepository extends JpaRepository<Stream, Long> {
    @Query("select s from Stream s where s.form.form = ?1 and s.name = ?2")
    Optional<Stream> findStream(int form, String name);
}
