package Enums;

public enum EMainMenu {
    GET_ALL_COURSE("getAllCourseList", "Get all courses"),
    GET_COURSE("getCourse", "Get course"),
    ADD_COURSE("addCourse", "Add course"),
    DELETE_COURSE("deleteCourse", "Delete course"),
    UPDATE_COURSE("updateCourse", "Update course"),
    GET_ALL_STUDENT("getAllStudentList", "Get all students"),
    GET_STUDENT("getStudent", "Get student"),
    ADD_STUDENT("addStudent", "Add student"),
    DELETE_STUDENT("deleteStudent", "Delete student"),
    UPDATE_STUDENT("updateStudent", "Update student"),
    MAKE_RESERVATION("makeReservation", "Make course reservation"),
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