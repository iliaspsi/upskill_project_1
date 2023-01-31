package LockedMe.com.prototype.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*Class to control File objects and methods*/
/*based on the File Java class*/
/*search,delete,create and list files*/
/*are implemented here*/
public class FileOperationsController {

    public FileOperationsController() {
    }

    /*method to sort and list the contents of a directory*/
    /*calls the listfiles method from object File*/
    public ArrayList<String> listsortedfiles(String startDirectory) throws Exception{

        File dir = new File(startDirectory);
        if (!dir.exists()){
            throw new FileNotFoundException("Directory not found try again");
        }
        else {
            File[] files = dir.listFiles();
            ArrayList<String> folderNames = new ArrayList<String>();

            if (files != null) {
                for (File file : files) {
                    // Check if the filepath is a directory
                    if (file.isDirectory()) {
                        folderNames.add(file.getName());

                    } else {
                        folderNames.add(file.getName());
                        ;
                    }
                }
            }
            Collections.sort(folderNames);
            return folderNames;
        }

    }

    /*method to search for a file*/
    /*goes through all the files and checks the names*/
    /*it ignores case sensitivity using the corresponding */
    /* method from the String class*/
    /*if the file is found/not found it prints a message*/
    public void findFile(String name, String path) throws Exception
    {
        File directory = new File(path);
        if (!directory.exists()){
            throw new FileNotFoundException("Directory not found try again");
        }
        else {
            File[] list = directory.listFiles();
            boolean isfound = false;
            if (list != null) {
                for (File fil : list) {
                    if (fil.isDirectory()) {
                        System.out.println(fil.getName()+" is a directory searching in there as well");
                        findFile(name, fil.getPath());
                    } else if (name.equalsIgnoreCase(fil.getName())) {
                        System.out.println("File " + fil.getName() + " found in " + fil.getParentFile());
                        isfound = true;
                    }
                }
                if (!isfound) {
                    System.out.println("File not found at " + path);
                }
            }
        }
    }


    /*method to delete a file*/
    /*goes through all the files and checks the names*/
    /*it uses case sensitivity */
    /*if the file is found then it deletes it.*/
    /*if not found it prints the corresponding message*/
    public void deletefile(File file){
        String filename = file.getName();
        boolean isfiledeleted = file.delete();
        if (isfiledeleted){
            System.out.println("File " + filename +" deleted");
        }
    }

    /*method to create a file*/
    /*if the file exists it prints the corresponding message and notifies the user*/
    public void createfile(String path, String filename) throws Exception {
        String fileSeparator = System.getProperty("file.separator");
        File directory = new File(path);
        if (!directory.exists()) {
            throw new FileNotFoundException("Directory not found try again");
        } else {

            //absolute file name with path
            String absoluteFilePath = path + fileSeparator + filename;
            File file = new File(absoluteFilePath);
            if (file.createNewFile()) {
                System.out.println(absoluteFilePath + " File Created");
            } else System.out.println("File " + absoluteFilePath + " already exists");

        }
    }
}

