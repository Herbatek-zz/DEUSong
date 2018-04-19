package dontKnowHotToNameItXD;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    private Filter[] filters ={
            new Filter("piesni//zwykle", true), //0
            new Filter("piesni//wielki post", true), //1
            new Filter("piesni//wielkanoc", true), //2
            new Filter("piesni//adwent", true), //3
            new Filter("piesni//koledy", true), //4
            new Filter("piesni//", true), //5
    };

    private List<File> songs = new ArrayList<>();

    public List<File> newSearch(String text) {
    songs.clear();

    for (Filter filter : filters) {
        if (filter.getState())
           search(text,filter.getPath());
    }
        return songs;
}


    private void search(String text, String directory) {
         File dir = new File(directory);

        File [] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".pptx");
            }
        });

        if(files != null)
        for (File a:files)
          songs.add(a);

    }

    // test
    public static void main(String[] args) {
        Search test = new Search();

        List<File> list = test.newSearch("gorz");
//        System.out.println(test.songs);
        for(File f : list) {
            System.out.println(f.getName());
        }


    }

}
