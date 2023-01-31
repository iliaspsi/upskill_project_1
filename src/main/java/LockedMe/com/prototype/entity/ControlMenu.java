package LockedMe.com.prototype.entity;

import java.util.ArrayList;

/*ControlMenu class*/
/*This class represents the menu object*/
/*menus are dynamically created depending on the menu indicator flag */
/* which represents the menu level */
public class ControlMenu {

    /*class attributes*/
    private int menuindicator=0;
    private String userprompt;
    private String welcomemenu;
    private ArrayList<String> menu1 = new ArrayList<String>();
    private ArrayList<String> menu2 = new ArrayList<String>();;
    private String userselection;
    private String folderPath;

    /*constructor*/
    public ControlMenu() {
    }

    /*getters and setters*/
    public String getUserprompt() {
        return userprompt;
    }

    public void setUserprompt(String userprompt) {
        this.userprompt = userprompt;
    }
    public int getMenuindicator() {
        return menuindicator;
    }

    public void setMenuindicator(int menuindicator) {
        this.menuindicator = menuindicator;
    }

    public String getWelcomemenu() {
        return welcomemenu;
    }

    public void setWelcomemenu(String welcomemenu) {
        this.welcomemenu = welcomemenu;
    }

    public ArrayList<String> getMenu1() {
        return menu1;
    }

    public void setMenu1(ArrayList<String> menu1) {
        this.menu1 = menu1;
    }

    public ArrayList<String> getMenu2() {
        return menu2;
    }

    public void setMenu2(ArrayList<String> menu2) {
        this.menu2 = menu2;
    }

    public String getUserselection() {
        return userselection;
    }

    public void setUserselection(String userselection) {
        this.userselection = userselection;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
}
