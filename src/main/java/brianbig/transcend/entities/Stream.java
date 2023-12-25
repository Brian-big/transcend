package brianbig.transcend.entities;

import brianbig.transcend.entities.enums.ClassForm;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "form_stream")
public class Stream extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ClassForm form;
    @Column(name = "name")
    private String name;


    public Stream() {
    }

    public Stream(String id, LocalDateTime createdAt, LocalDateTime updatedAt, ClassForm form, String name) {
        super(id, createdAt, updatedAt);
        this.form = form;
        this.name = name;
    }

    public Stream(ClassForm form, String name) {
        this(null, null, null, form, name);
    }

    public ClassForm getForm() {
        return form;
    }

    public void setForm(ClassForm form) {
        this.form = form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
