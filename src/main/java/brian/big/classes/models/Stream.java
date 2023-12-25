package brian.big.classes.models;

import jakarta.persistence.*;

@Entity
@Table
public class Stream {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Form form;
    @Column(name="name")
    private String name;

    public Stream() {
    }

    public Stream(int id, Form form, String name) {
        this.id = id;
        this.form = form;
        this.name = name;
    }

    public Stream(Form form, String name) {
        this.form = form;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
