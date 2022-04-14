package crud.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workers")
public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String last_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_position", nullable = false)
    private Positions position;

    @ManyToMany()
    @JoinTable(name = "projects_to_workers",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )
    Set<Projects> projects = new HashSet<>();

    public Workers(){
    }

    public Workers(int id, Positions position, String name, String last_name) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.last_name = last_name;
    }
    public Workers(Positions position, String name, String last_name) {
        this.position = position;
        this.name = name;
        this.last_name = last_name;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
