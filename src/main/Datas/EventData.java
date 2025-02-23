package Datas;

public class EventData {
    private int id;
    private String name;
    private String description;
    private String organizer;

    public EventData(int id, String name, String description, String organizer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organizer = organizer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}
