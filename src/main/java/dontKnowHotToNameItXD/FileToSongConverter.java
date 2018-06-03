package dontKnowHotToNameItXD;

import java.io.File;

public class FileToSongConverter {

    public static Song toSong(File file, String category) {
        return new Song(file.getName(), category, file.getPath(), file);
    }

}
