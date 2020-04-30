package com.project.placementcell;

public class ListData {
    private String name;
    private String description;

    public ListData(){


    }

    public ListData( String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

}
