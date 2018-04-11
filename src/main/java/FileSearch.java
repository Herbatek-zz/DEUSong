import java.io.File;

public class FileSearch {

    File dir = new File(directory);
    File[] files = dir.listFiles((dir1, name) -> name.startsWith("temp") && name.endsWith(".txt"));


}
