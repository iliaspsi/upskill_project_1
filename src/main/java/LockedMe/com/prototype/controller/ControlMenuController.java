package LockedMe.com.prototype.controller;

import LockedMe.com.prototype.entity.ControlMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*Class to control the ControlMenu*/
/*it handles the menu creation and the interaction */
/*with the users. It also invokes the FileOperationsController*/
/*when the user selects to create/list or delete files*/
public class ControlMenuController {

    public boolean run_flag;
    public String path="/Users/iliaspsi/Desktop/Test_Folder";
    ControlMenu menu = new ControlMenu();
    FileOperationsController fo = new FileOperationsController();

    /*method to initialize ControlMenu's attributes*/
    public void setmenus(){
        String welcomemsg = "Welcome to LockedMe.com prototype application!!! \n" +
                "Version 1.0 -- 23/01/2023\n"+
                "Developed by Ilias Psimoulis\n\n";
        String userprompt = "Please select one of the following options by typing the number and hit enter:";
        ArrayList<String> menu1 = new ArrayList<String>();
        menu1.add("1--> Enter main menu");
        menu1.add("2--> Exit");
        Collections.sort(menu1);
        ArrayList<String> menu2 = new ArrayList<String>();
        menu2.add("0--> Change default directory");
        menu2.add("1--> List all files or folders in ascending order");
        menu2.add("2--> Add a file to the existing directory");
        menu2.add("3--> Delete a file or folder from the existing list");
        menu2.add("4--> Search for a file of folder in the directory");
        menu2.add("5--> Return to main menu");
        menu2.add("6--> Exit application");

        Collections.sort(menu2);
        menu.setWelcomemenu(welcomemsg);
        menu.setMenu1(menu1);
        menu.setMenu2(menu2);
        menu.setUserprompt(userprompt);
    }

    /*method to print the welcome message*/
    public void printwelcomescreen(){
        System.out.println(menu.getWelcomemenu());
    }

    /*method to print the menu lists depending on the menu indicator*/
    public void printmenu(int menuindicator){
        System.out.println(menu.getUserprompt());
        if(menuindicator==0) {
            for (String text : menu.getMenu1()) {
                System.out.println(text);
            }
        }
        else{
                for (String text : menu.getMenu2()) {
                    System.out.println(text);
                }
            }
    }

    /*method used to get input from the user*/
    public String userselection(){
        Scanner reader = new Scanner(System.in);
        menu.setUserselection(reader.nextLine()); // use instead of next() to handle empty input
        return menu.getUserselection();
    }

    /*menuindicator getter and setter*/
    public int getmenuindicator(){
        return menu.getMenuindicator();
    }

    public void setmenuindicator(){
        if (menu.getMenuindicator()==1){
            menu.setMenuindicator(0);
        }else {
            menu.setMenuindicator(1);
        }

    }

    /*menu navigation method*/
    /*uses switch statements depending on the menuindicator value*/
    public void navigatemenu(int menuindicator,String userselection) throws Exception {
        if (menuindicator ==0) {
            switch (userselection) {
                case "1":
                    setmenuindicator();
                    printmenu(getmenuindicator());
                    break;
                case "2":
                    System.out.println("Exit");
                    run_flag = false;
                    break;
            }
        }
        else {
                switch (userselection) {
                    case "0":
                        System.out.println("Current default directory is: "+ path);
                        System.out.println("Enter new default directory path:");
                        path = userselection();
                        promptEnterKey();
                        printmenu(getmenuindicator());
                        break;
                    case "1":
                        System.out.println("Enter directory path:");
                        String dir = userselection();
                        //System.out.println(path.length());
                        if (dir.isEmpty()) {

                            System.out.println(path);
                            getlistfiles(path);
                        }else {
                            System.out.println(dir);
                            getlistfiles(dir);
                        }
                        promptEnterKey();
                        printmenu(getmenuindicator());
                        break;
                    case "2" :
                        //try {
                            createfile();
                        /*}catch(Exception e){
                            System.out.println("IO exception");
                        }*/
                        promptEnterKey();
                        printmenu(getmenuindicator());
                        break;
                    case "3":
                        System.out.println("Current path is "+path);
                        System.out.println("Type filename to delete");
                        String filetodelete = userselection();
                        deletefile(path,filetodelete);
                        promptEnterKey();
                        printmenu(getmenuindicator());
                        break;
                    case "4":
                        System.out.println("Current path is "+path);
                        searchfile();
                        promptEnterKey();
                        printmenu(getmenuindicator());
                        break;
                    case "5":
                        System.out.println("return to main menu");
                        setmenuindicator();
                        printmenu(getmenuindicator());
                        break;
                    case "6" :
                        System.out.println("Exit application");
                        run_flag = false;
                        break;
                    default:
                        if (userselection.isEmpty()){
                            printmenu(getmenuindicator());
                        }else {
                            System.out.println("Invalid selection please try again");
                            printmenu(getmenuindicator());
                        }
                }
            }
    }

    /*method called by the menu navigator*/
    /*when the user selects to list sorted files*/
    /*it calls the corresponding method from FileOperationsController object*/
    public void getlistfiles(String path) throws Exception {
        ArrayList<String> dirList = fo.listsortedfiles(path);
        for (String s : dirList) {
            System.out.println(s);
        }
    }

    /*method called by the menu navigator*/
    /*when the user selects to search for a file*/
    /*it calls the corresponding method from FileOperationsController object*/
    /*user is able to type in a directory or use the default*/
    public void searchfile() throws Exception{
        System.out.println("Enter the file to be searched.. " );
        String name = userselection();
        System.out.println("Enter the directory where to search ");
        String directory = userselection();
        if (directory.isEmpty()){
            directory = path;
        }
        fo.findFile(name,directory);
    }

    /*method called by the menu navigator*/
    /*when the user selects to delete a file*/
    /*it calls the corresponding method from FileOperationsController object*/
    /*user is able to type in a directory or use the default*/
    /*upon success or FNF the program prints a message*/
    public void deletefile(String path,String filetodelete){
        System.out.println("Enter the directory where to delete ");
        String directory = userselection();
        if (!directory.isEmpty()){
            path = directory;
        }
        File f = new File(path);
        File[] dir = f.listFiles();
        boolean isDeleted = true;
        for (File file:dir ){
            if (file.getName().equals(filetodelete)){
                fo.deletefile(file);
                isDeleted = true;
                break;
            }
            else{
                isDeleted = false;
            }
        }
        if (!isDeleted){
            System.out.println("File not found");
        }
    }

    /*method called by the menu navigator*/
    /*it is used to prompt the user to press*/
    /*to proceed with the app's flow*/
    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /*method called by the menu navigator*/
    /*when the user selects to create a file*/
    /*it calls the corresponding method from FileOperationsController object*/
    /*user is able to type in a directory or use the default*/
    /*upon success the program prints a message with the filename created and the location*/
    /*the method throws a checked exception when wrong input is provided by the user*/
    public void createfile() throws Exception{
        System.out.println("Please enter directory path or leave blank for default path");
        String userpath = userselection();
        System.out.println("Please enter filename to be created");
        String filename = userselection();
        if (userpath.isEmpty()){
            fo.createfile(path,filename);
        }else{
            fo.createfile(userpath,filename);
        }

    }
}
