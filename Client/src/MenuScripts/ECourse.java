package MenuScripts;

public enum ECourse {
    // Enter Item
    ENTER_COURSE_ID("Enter course ID\n>> "),
    ENTER_PROFESSOR("Enter professor Name\n>> "),
    ENTER_COURSE_NAME("Enter course name\n>> "),
    ENTER_PRE_COURSES_MULTI("Enter preCourses (separated by comma)\n>> "),
    ENTER_SEMESTER("Enter semester (ex. 2022-01, 2022-SM, 2022-02, 2022-WT) \n>> "),
    ENTER_MAX_CAPACITY("Enter max capacity\n>> "),

    // Situation (CRUD)
    GET_FAIL("Failed to get course"),
    ADD_SUCCESS("The course has been added successfully. "),
    ADD_FAIL("Failed to add a course."),
    ADD_FAIL_ALREADY_EXIST("Failed to add a course. The course already exists."),
    ADD_FAIL_PRE_COURSE_IS_NOT_EXIST("Failed to add a course. The preCourse is not exist."),
    DELETE_SUCCESS("The course has been deleted successfully."),
    DELETE_FAIL("Failed to delete a course."),
    UPDATE_SUCCESS("The course has been updated successfully."),
    UPDATE_FAIL("Failed to update a course."),

    // Error
    NOT_FOUND("This course does not exist."),
    INVALID_COURSE_ID("Course ID must be 5 digits."),
    INVALID_COURSE_PROFESSOR("The professor's name must be a character string of 2 or more characters."),
    INVALID_COURSE_NAME("Course name must be 2~40 characters."),
    INVALID_COURSE_SEMESTER("Semester must be in the format of yyyy-ss (ss : 01,SM,02,WT)."),
    INVALID_PRE_COURSE("Invalid preCourse Ids. Please enter the valid preCourse Ids separated by commas."),
    INVALID_MAX_CAPACITY("Max capacity must be a positive integer."),
    DUPLICATED_COURSE_ID("Duplicated Course Id."),
    NO_EXIST_PRE_COURSE("The entered ID of the prerequisite course is a non-existent course ID.");

    private final String message;
    public String getMessage(){return message;}
    ECourse(String message){
        this.message = message;
    }
}