package Enums;

public enum EMajor {
    COMPUTER_SCIENCE("CS"),
    ELECTRICAL_ENGINEERING( "EE"),
    MECHANICAL_ENGINEERING("ME");

    // Variables
    private String abbreviation;

    // Getters & Setters
    public String getAbbreviation(){return abbreviation;}
    public void setAbbreviation(String abbreviation){this.abbreviation = abbreviation;}

    // Constructor
    EMajor(String abbreviation){
        this.abbreviation = abbreviation;
    }

    // Methods
    public static String[] getMajorAbbrList(){
        String[] majorList = new String[EMajor.values().length];
        for(int i = 0; i < EMajor.values().length; i++){
            majorList[i] = EMajor.values()[i].getAbbreviation();
        }
        return majorList;
    }
}
