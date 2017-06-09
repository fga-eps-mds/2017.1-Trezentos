package fga.mds.gpp.trezentos.Model;

import java.util.ArrayList;

public class Groups {
    private String groupNumber;
    private ArrayList<String> helpers;
    private ArrayList<String> helped;

    public Groups(String groupNumber, ArrayList<String> helpers, ArrayList<String> helped){
        this.groupNumber = groupNumber;
        this.helpers = helpers;
        this.helped = helped;
    }

    public void setGroupNumber(String groupNumber){
        this.groupNumber = groupNumber;
    }

    public String getGroupNumber(){
        return groupNumber;
    }

    public ArrayList<String> getHelpers() {
        return helpers;
    }

    public void addHelpers(String helpers) {
        this.helpers.add(helpers);
    }

    public ArrayList<String> getHelped() {
        return helped;
    }

    public void addHelped(String helped) {
        this.helped.add(helped);
    }
}