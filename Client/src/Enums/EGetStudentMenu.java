package Enums;

public enum EGetStudentMenu {
    GET_STUDENT_BY_ID("getStudentById", "Get student by student Id"),
    GET_STUDENTS_BY_FIRST_NAME("getStudentsByFirstName", "Get students by student First name"),
    GET_STUDENTS_BY_LAST_NAME("getStudentsByLastName", "Get students by student's last name"),
    GET_STUDENTS_BY_MAJOR("getStudentsByMajor", "Get students by student's major"),
    GET_STUDENTS_BY_COMPLETED_COURSE("getStudentsByCompletedCourse", "Get students who have taken the course.");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    EGetStudentMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
