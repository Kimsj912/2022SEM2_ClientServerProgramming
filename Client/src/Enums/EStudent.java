package Enums;

public enum EStudent {
    // Enter Item
    studentID("Enter student ID"),
    studentLastName("Enter student Last name"),
    studentFirstName("Enter student First name"),
    studentMajor("Enter student Major"),
    studentCompletedCourses("Enter completedCourses (separated by comma)"),

    // Situation
    DELETED("Student deleted\n>> "),
    NOT_FOUND("Student not found\n>> ");

    private String message;
    public String getMessage(){return message;}
    EStudent(String message){
        this.message = message;
    }
}
