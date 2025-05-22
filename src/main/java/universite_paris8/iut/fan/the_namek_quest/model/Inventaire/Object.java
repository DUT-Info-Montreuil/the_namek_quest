package universite_paris8.iut.fan.the_namek_quest.model.Inventaire;

public abstract class Object {
    private int id;
    private String name;

    public Object(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
}

