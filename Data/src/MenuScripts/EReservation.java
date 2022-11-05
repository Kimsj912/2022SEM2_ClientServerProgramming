package MenuScripts;

public enum EReservation {
    // Enter Item
    ENTER_COURSE_ID("Enter course ID\n>> "),
    ENTER_PROFESSOR("Enter professor Name\n>> "),
    ENTER_COURSE_NAME("Enter course name\n>> "),
    ENTER_PRE_COURSES("Enter preCourses (separated by comma)\n>> "),

    // Situation (CRUD)
    ADD_SUCCESS("Completed to make a reservation \n>> "),
    ADD_FAIL("Failed to add a course \n>> "),
    DELETE_SUCCESS("Completed to delete a reservation \n>> "),
    DELETE_FAIL("Failed to delete a course \n>> "),

    // Error
    NOT_FOUND("This course does not exist \n>> "),
    INVALID_COURSE_ID("Course ID must be 5 digits\n>> "),
    INVALID_COURSE_PROF_NAME("The professor's name must be a character string of 2 or more characters.\n>> "),
    INVALID_COURSE_NAME("Course name must be 2~40 characters\n>> "),
    DUPLICATED_COURSE_ID("Duplicated Course Id\n>> "),
    INVALID_PRE_COURSE("Invalid preCourse Id\n>> "),
    NO_EXIST_PRE_COURSE("The entered ID of the prerequisite course is a non-existent course ID.\n>> ");

    private final String message;
    public String getMessage(){return message;}
    EReservation(String message){
        this.message = message;
    }
}