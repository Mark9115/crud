package crud.model.objects;

public class Workers {
    private int id;
    private int id_position;
    private String name;
    private String last_name;
    private String name_position;

    public Workers(){

    }

    public Workers(int id_position, String name, String last_name) {
        this.id_position = id_position;
        this.name = name;
        this.last_name = last_name;
    }

    public Workers(int id, int id_position, String name, String last_name) {
        this.id = id;
        this.id_position = id_position;
        this.name = name;
        this.last_name = last_name;
    }

    public Workers(int id, int id_position,String name_position, String name, String last_name) {
        this.id = id;
        this.id_position = id_position;
        this.name = name;
        this.last_name = last_name;
        this.name_position = name_position;
    }

    public Workers(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_position() {
        return id_position;
    }

    public void setId_position(int id_position) {
        this.id_position = id_position;
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

    public String getName_position() {
        return name_position;
    }

    public void setName_position(String name_position) {
        this.name_position = name_position;
    }
}
