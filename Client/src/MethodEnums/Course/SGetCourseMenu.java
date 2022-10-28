package MethodEnums.Course;

import MethodEnums.IMenuInterface;

public enum SGetCourseMenu implements IMenuInterface {
    GET_COURSE_BY_ID("getCourseById", "search course by course Id"),
    GET_COURSES_BY_NAME("getCoursesByName", "search course by course name"),
    GET_COURSES_BY_PROF("getCoursesByProfessor", "search course by course's Professor Name"),
    GET_COURSES_BY_PRE_COURSE("getCoursesByPreCourseId", "search course by course's preCourse");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    SGetCourseMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
