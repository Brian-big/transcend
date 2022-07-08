package brian.big.students.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Prefect extends Student {
    @Column private String position;

    public Prefect() {
    }

    public Prefect(String position) {
        this.position = position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}