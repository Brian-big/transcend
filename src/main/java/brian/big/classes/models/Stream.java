package brian.big.classes.models;

import javax.persistence.*;

@Entity
@Table
public class Stream {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Form form;
    @Column(name="name")
    private String name;
}
