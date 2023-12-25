package brianbig.transcend.repository;

import brianbig.transcend.entities.Stream;
import brianbig.transcend.entities.enums.ClassForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface StreamRepository extends JpaRepository<Stream, String> {
//    @Query("select s from Stream s where s.form = ?1 and s.name = ?2")
//    Optional<Stream> findStream(String form, String name);
@Query("select f from form_stream f where f.form = ?1 and upper(f.name) = upper(?2)")
Optional<Stream> findByFormAndNameIgnoreCase(@NonNull ClassForm form, @NonNull String name);
}
