package Enums;

import Interfaces.ISubMenuInterface;

public enum IUpdateCourseMenu implements ISubMenuInterface {
    UPDATE_COURSE_BY_ID("updateCourseById", "update course search by course Id"),
    UPDATE_COURSE_BY_NAME("updateCourseByName", "update course search  by course name"),
    UPDATE_COURSE_BY_PROF("updateCourseByProfName", "update course search by course's Professor Name"),
    UPDATE_COURSE_BY_PRE_COURSE("updateCourseByPreCourse", "update course search by course's preCourse");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    IUpdateCourseMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
