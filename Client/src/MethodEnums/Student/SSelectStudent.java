package MethodEnums.Student;

import MethodEnums.IMenuInterface;

public enum SSelectStudent implements IMenuInterface {
    GET_ALL_COURSES("getAllStudents", "get all students"),
    GET_COURSE("getStudent", "get student with variable ways"),
    ADD_COURSE("addStudent", "add student"),
    DELETE_COURSE_BY_ID("deleteStudentById", "delete student by student Id"),
    UPDATE_COURSE_BY_ID("updateStudentById", "update student search by student Id");


    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    SSelectStudent(String method, String description){
        this.method = method;
        this.description = description;
    }
}
