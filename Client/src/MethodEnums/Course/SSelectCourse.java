package MethodEnums.Course;

import MethodEnums.IMenuInterface;

public enum SSelectCourse implements IMenuInterface {
    GET_ALL_COURSES("GetAllCoursesWithPage", "get all courses"),
    GET_COURSE("getCourse", "get course with variable ways"),
    ADD_COURSE("addCourse", "add course"),
    DELETE_COURSE_BY_ID("deleteCourseById", "delete course by course Id"),
    UPDATE_COURSE_BY_ID("updateCourseById", "update course search by course Id");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    SSelectCourse(String method, String description){
        this.method = method;
        this.description = description;
    }
}
