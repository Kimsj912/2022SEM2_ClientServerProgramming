package MenuScripts;

public enum ECourse {
    // Enter Item
    ENTER_COURSE_ID("Enter course ID\n>> "),
    ENTER_PROFESSOR("Enter professor Name\n>> "),
    ENTER_COURSE_NAME("Enter course name\n>> "),
    ENTER_PRE_COURSES_SINGLE("Enter preCourse ID\n>> "),
    ENTER_PRE_COURSES_MULTI("Enter preCourses (separated by comma)\n>> "),
    ENTER_SEMESTER("Enter semester (ex. 2022-01, 2022-sm, 2022-02, 2022-wt) \n>> "),
    ENTER_MAX_CAPACITY("Enter max capacity\n>> "),

    // Situation (CRUD)
    ADD_SUCCESS("The course has been added successfully.\n>> "),
    ADD_FAIL("Failed to add a course \n>> "),
    DELETE_SUCCESS("The course has been deleted successfully.\n>> "),
    DELETE_FAIL("Failed to delete a course \n>> "),
    UPDATE_SUCCESS("The course has been updated successfully.\n>> "),
    UPDATE_FAIL("Failed to update a course \n>> "),

    // Error
    NOT_FOUND("This course does not exist \n>> "),
    INVALID_COURSE_ID("Course ID must be 5 digits\n>> "),
    INVALID_COURSE_PROFESSOR("The professor's name must be a character string of 2 or more characters.\n>> "),
    INVALID_COURSE_NAME("Course name must be 2~40 characters\n>> "),
    INVALID_COURSE_SEMESTER("Semester must be in the format of yyyy-ss (ss : 01,SM,02,WT).\n>> "),
    INVALID_PRE_COURSE("Invalid preCourse Id\n>> "),
    INVALID_MAX_CAPACITY("Max capacity must be a positive integer\n>> "),
    DUPLICATED_COURSE_ID("Duplicated Course Id\n>> "),
    NO_EXIST_PRE_COURSE("The entered ID of the prerequisite course is a non-existent course ID.\n>> ");

    private final String message;
    public String getMessage(){return message;}
    ECourse(String message){
        this.message = message;
    }
}