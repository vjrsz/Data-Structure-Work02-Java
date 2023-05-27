package File;

public class File {
    private String name;
    private String type;
    private int size;
    private String dateUpdate;
    private String dateCreated;

    public File(String name, String type, int size, String dateUpdate, String dateCreated) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.dateUpdate = dateUpdate;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
