package crud.model.objects;

public class Positions {
    int id;
    String name_position;

    public Positions() {
    }

    public Positions(int id, String name_position) {
        this.id = id;
        this.name_position = name_position;
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
}
