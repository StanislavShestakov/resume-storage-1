package pro.bolshakov.resumestorage.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<File> getAllFiles(File file){

        List<File> list = new ArrayList<>();
        if(file.isDirectory()){
            addSubFiles(file, list);
        }
        else {
            list.add(file);
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
