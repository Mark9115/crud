package crud.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    @Column(name = "description")
    String description;

    @ManyToMany(mappedBy = "projects")
    private Set<Workers> workers = new HashSet<>();

    public Projects() {
    }

    public Projects(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Set<Workers> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Workers> workers) {
        this.workers = workers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
