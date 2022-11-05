package Enums;

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

    public static String[] getMajorAbbrList(){
        String[] majorList = new String[EMajor.values().length];
        for(int i = 0; i < EMajor.values().length; i++){
            majorList[i] = EMajor.values()[i].getAbbreviation();
        }
        return majorList;
    }
    // Constructor
    EMajor(String major, String abbreviation){
        this.major = major;
        this.abbreviation = abbreviation;
    }
}
