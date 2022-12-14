package MethodEnums;

public enum EMainMenu implements IMenuInterface {
    SELECT_COURSE("selectCourse", "View or manipulate the course"),
    SELECT_STUDENT("selectStudent", "View or manipulate the student"),
    SELECT_RESERVATION("selectReservation", "View or manipulate the course reservation"),
    EXIT("exit", "Program exit");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}


    EMainMenu(String method, String description){
        this.method = method;
        this.description = description;
    }

}