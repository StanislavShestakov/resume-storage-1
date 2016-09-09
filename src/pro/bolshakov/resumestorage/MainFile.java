package pro.bolshakov.resumestorage;

import pro.bolshakov.resumestorage.util.FileUtil;

import java.io.File;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) throws IOException {
        File dir = new File(".\\src");
        for (File file: FileUtil.getAllFiles(dir)){
            System.out.println(file.getName());
        }
    }

}
