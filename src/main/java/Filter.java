import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;

public class Filter {

    private String path;
    private Boolean state;


    public Filter(String path, Boolean state) {
        this.path = path;
        this.state = state;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
