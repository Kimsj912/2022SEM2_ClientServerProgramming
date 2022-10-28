package MethodEnums.Student;


import MethodEnums.IMenuInterface;

public enum SGetStudentMenu implements IMenuInterface {
    GET_STUDENT_BY_ID("getStudentById", "Get student by student Id"),
    GET_STUDENTS_BY_NAME("getStudentsByName", "Get students by student's name"),
    GET_STUDENTS_BY_MAJOR("getStudentsByMajor", "Get students by student's major"),
    GET_STUDENTS_BY_COMPLETED_COURSE("getStudentsByCompletedCourseId", "Get students who have taken the course.");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    SGetStudentMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
