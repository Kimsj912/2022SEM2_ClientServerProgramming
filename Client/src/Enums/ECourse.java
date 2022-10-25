package Enums;

public enum ECourse {
    // Enter Item
    courseID("Enter course ID\n>> "),
    courseProfName("Enter professor Last Name\n>> "),
    courseName("Enter course name\n>> "),
    preCourses("Enter preCourses (separated by comma)\n>> "),

    // Situation (CRUD)
    ADD_SUCCESS("The course has been added successfully.\n>> "),
    ADD_FAIL("The course has not been added.\n>> "),
    DELETE_SUCCESS("The course has been deleted successfully.\n>> "),
    DELETE_FAIL("The course has not been deleted.\n>> "),
    UPDATE_SUCCESS("The course has been updated successfully.\n>> "),
    UPDATE_FAIL("The course has not been updated.\n>> "),
    NOT_FOUND("Object.Course not found.\n>> "),

    // Error
    INVALID_COURSE_ID("Object.Course ID must be 5 digits\n>> "),
    INVALID_COURSE_PROF_NAME("The professor's name must be a character string of 2 or more characters.\n>> "),
    INVALID_COURSE_NAME("Object.Course name must be 2~40 characters\n>> "),
    DUPLICATED_COURSE_ID("Duplicated Object.Course Id\n>> "),
    INVALID_PRE_COURSE("Invalid preCourse Id\n>> "),
    NOT_EXIST_PRE_COURSE("The entered ID of the prerequisite course is a non-existent course ID.\n>> ");

    private final String message;
    public String getMessage(){return message;}
    ECourse(String message){
        this.message = message;
    }
}