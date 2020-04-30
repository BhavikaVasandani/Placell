package com.project.placementcell;

public class ListData1 {
    private String Name;
    private String Gender;

    public ListData1(){


    }

    public ListData1( String Name, String Gender) {
        this.Name = Name;
        this.Gender= Gender;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGender() {
        return Gender;
    }

}