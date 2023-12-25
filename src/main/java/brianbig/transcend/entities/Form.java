package brianbig.transcend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "form")
public class Form {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "form", unique = true)
    private int form;
    @Column(name = "verbose_name", unique = true)
    private String verboseName;

    public Form() {
    }

    public Form(int form, String verboseName) {
        this.form = form;
        this.verboseName = verboseName;
    }

    public Form(long id, int form, String verboseName) {
        this.id = id;
        this.form = form;
        this.verboseName = verboseName;
    }

    public Form(int form) {
        this.form = form;
        switch (form){
            case 1: verboseName = "One";
            break;
            case 2: verboseName = "Two";
            break;
            case 3: verboseName = "Three";
            break;
            case 4: verboseName = "Four";
            break;
            default: verboseName = String.valueOf(form);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public String getVerboseName() {
        return verboseName;
    }

    public void setVerboseName(String verboseName) {
        this.verboseName = verboseName;
    }

    @Override
    public String toString() {
        return "Form{" +
                "form=" + form +
                ", verboseName='" + verboseName + '\'' +
                '}';
    }
}
