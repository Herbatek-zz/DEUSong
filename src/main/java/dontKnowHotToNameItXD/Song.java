package dontKnowHotToNameItXD;

import javafx.beans.property.SimpleStringProperty;

public class Song {

    private SimpleStringProperty title;
    private SimpleStringProperty category;
    private SimpleStringProperty path;

    public Song(String title, String category, String path) {
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.path = new SimpleStringProperty(path);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getPath() {
        return path.get();
    }
}
