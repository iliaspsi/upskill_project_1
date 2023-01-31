package LockedMe.com.prototype;

import LockedMe.com.prototype.controller.ControlMenuController;
import LockedMe.com.prototype.controller.FileOperationsController;
import LockedMe.com.prototype.entity.ControlMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMePrototypeApplication {


    /*Application's main method*/
    /*calls methods from ControlMenuController class and handles all exceptions*/
    /*the app is kept alive at all times except when the user*/
    /*decides to exit*/
    public static void main(String[] args) {
        ControlMenuController m = new ControlMenuController();
        m.run_flag = true;
        m.setmenus();
        m.printwelcomescreen();
        m.printmenu(m.getmenuindicator());
        while (m.run_flag) {
            try {
                String selection = m.userselection();
                m.navigatemenu(m.getmenuindicator(), selection);
            } catch (Exception e) {
                //System.out.println(e.toString());
                System.out.println(e.getMessage());
                //System.out.println("Stacktrace: ");
                //e.printStackTrace();
            }
        }

    }
}









