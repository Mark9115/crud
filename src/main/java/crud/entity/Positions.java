package crud.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    @Column(name = "name_position")
    String name_position;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Workers> workers = new ArrayList<>();

    public Positions() {
    }

    public Positions(int id, String name_position) {
        this.id = id;
        this.name_position = name_position;
    }

    public Positions(int id, String name_position, List<Workers> workers) {
        this.id = id;
        this.name_position = name_position;
        this.workers = workers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_position() {
        return name_position;
    }

    public void setName_position(String name_position) {
        this.name_position = name_position;
    }

    public List<Workers> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Workers> workers) {
        this.workers = workers;
    }
}
