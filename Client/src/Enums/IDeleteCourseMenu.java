package Enums;

import Interfaces.ISubMenuInterface;

public enum IDeleteCourseMenu implements ISubMenuInterface {
    DELETE_COURSE_BY_ID("deleteCourseById", "delete course by course Id"),
    DELETE_COURSE_BY_NAME("deleteCourseByName", "delete course by course name"),
    DELETE_COURSE_BY_PROF("deleteCourseByProfName", "delete course by course's Professor Name"),
    DELETE_COURSE_BY_PRE_COURSE("deleteCourseByPreCourse", "delete course by course's preCourse");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    IDeleteCourseMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
