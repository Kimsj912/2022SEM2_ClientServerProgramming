package Enums;

import java.util.ArrayList;

public enum EMajor {
    COMPUTER_SCIENCE("Computer Science", "CS"),
    ELECTRICAL_ENGINEERING("Electrical Engineering", "EE"),
    MECHANICAL_ENGINEERING("Mechanical Engineering", "ME");

    // Variables
    private String major;
    private String abbreviation;

    // Getters & Setters
    public String getMajor(){return major;}
    public String getAbbreviation(){return abbreviation;}
    public void setMajor(String major){this.major = major;}
    public void setAbbreviation(String abbreviation){this.abbreviation = abbreviation;}

    public static ArrayList<String> getMajorList(){
        ArrayList<String> majorList = new ArrayList<String>();
        for(EMajor major : EMajor.values()){
            majorList.add(major.getMajor());
        }
        return majorList;
    }
    // Constructor
    EMajor(String major, String abbreviation){
        this.major = major;
        this.abbreviation = abbreviation;
    }
}
