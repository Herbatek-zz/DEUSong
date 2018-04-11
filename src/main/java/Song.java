import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Song {

    private Filter[] filters ={
            new Filter("piesni//zwykle", true), //0
            new Filter("piesni//wielki post", true), //1
            new Filter("piesni//wielkanoc", true), //2
            new Filter("piesni//adwent", true), //3
            new Filter("piesni//koledy", true), //4
            new Filter("piesni//", true), //5


    };



    public List songs = new ArrayList();

    public void newSearch(String text) {
    songs.clear();

    for (Filter a: filters) {

        if (a.getState())
        {
           search(text,a.getPath());
        }
    }

}

private void search(String text, String directory) {

     File dir = new File(directory);
     songs.add(Arrays.asList(dir.listFiles((dir1, name) -> name.startsWith(text) && name.endsWith(".pptx"))));
 }

}
