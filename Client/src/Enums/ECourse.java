package Enums;

public enum ECourse {
    // Enter Item
    courseID("Enter course ID\n>> "),
    courseProfName("Enter professor Last Name\n>> "),
    courseName("Enter course name\n>> "),
    preCourses("Enter preCourses (separated by comma)\n>> "),

    // Situation
    DELETED("The course has been deleted successfully.\n>> "),
    NOT_FOUND("Course not found.\n>> "),
    INVALID_ID("ID must be 5 digits\n>> "),
    INVALID_PROF("The professor's name must be a character string of 2 or more characters.\n>> "),
    INVALID_NAME("Course name must be 2~40 characters\n>> "),
    DUPLICATED_ID("Duplicated Course Id\n>> "),
    INVALID_PRE_COURSE("Invalid preCourse Id\n>> "),
    NOT_EXIST_PRE_COURSE("The entered ID of the prerequisite course is a non-existent course ID.\n>> ");

    private String message;
    public String getMessage(){return message;}
    ECourse(String message){
        this.message = message;
    }
}