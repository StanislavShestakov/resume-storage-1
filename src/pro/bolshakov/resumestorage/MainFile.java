package pro.bolshakov.resumestorage;

import pro.bolshakov.resumestorage.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFile {

    public static void main(String[] args) throws IOException {

//        printFilesDeeply(new File(".\\src"), 0);

        Path f = Paths.get(".\\src\\pro\\bolshakov\\resumestorage");
        Path f2 = f.resolve("MainArray.java");
        System.out.println(Files.exists(f2));
        System.out.println(f2.getFileName().toString());
        Files.newDirectoryStream(f).forEach(System.out::println);
    }

    public static void printFilesDeeply(File file, int level){
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(file.getName());
        if(file.isDirectory()){
            for(File inFile: file.listFiles()){
                printFilesDeeply(inFile, level + 1);
            }
        }
    }

}
