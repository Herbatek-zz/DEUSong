package dontKnowHotToNameItXD;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private Filter[] filters ={
            new Filter("zwykle", true), //0
            new Filter("wielki post", true), //1
            new Filter("wielkanoc", true), //2
            new Filter("adwent", true), //3
            new Filter("koledy", true), //4
            new Filter("", true), //5
    };

    private List<Song> songs = new ArrayList<>();

    public List<Song> newSearch(String text) {
    songs.clear();

    for (Filter filter : filters) {
        if (filter.getState())
           search(text, filter.getPath(), filter.getCategory());
    }
        return songs;
}


    private void search(String text, String directory, String category) {
        File dir = new File(directory);

        File [] files = dir.listFiles((dir1, name) -> name.contains(text) && name.endsWith(".pptx"));

        if(files != null)
            for (File a : files)
                songs.add(FileToSongConverter.toSong(a, category));

    }

    public Filter[] getFilters() {
        return filters;
    }

    public void setFilters(Filter[] filters) {
        this.filters = filters;
    }

    public boolean isAllFiltersTrue(Filter[] filters){
        for(Filter filter : filters) {
            if(!filter.getState())
                return false;
        }
        return true;
    }

    public Filter[] setAllFiltersFalse(Filter[] filters) {
        for(Filter filter : filters)
            filter.setState(false);
        return filters;
    }
}
