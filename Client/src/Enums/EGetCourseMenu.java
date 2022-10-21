package Enums;

public enum EGetCourseMenu {
    GET_COURSE_BY_ID("getCourseById", "search course by course Id"),
    GET_COURSE_BY_NAME("getCourseByName", "search course by course name"),
    GET_COURSE_BY_PROF("getCourseByProfName", "search course by course's Professor Name"),
    GET_COURSE_BY_PRE_COURSE("getCourseByPreCourse", "search course by course's preCourse");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    EGetCourseMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
