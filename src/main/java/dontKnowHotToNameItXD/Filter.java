package dontKnowHotToNameItXD;

public class Filter {

    private String root = "piesni//";
    private String category;
    private Boolean state;
    private String path;


    public Filter(String category, Boolean state) {
        this.category = category;
        this.state = state;
        this.path = root + category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
