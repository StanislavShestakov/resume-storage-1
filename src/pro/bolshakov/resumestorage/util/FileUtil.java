package pro.bolshakov.resumestorage.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<File> getAllFiles(File directory){

        List<File> list = new ArrayList<>();
        if(directory.isDirectory()){
            addSubFiles(directory, list);
        }
        else {
            list.add(directory);
        }

        return list;
    }

    private static void addSubFiles(File directory, List<File> list){
        for (File file : directory.listFiles()){
            if(file.isDirectory()){
                addSubFiles(file, list);
            }
            else {
                list.add(file);
            }
        }
    }

}
