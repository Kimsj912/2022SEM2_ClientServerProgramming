package MenuScripts;


public enum EStudent {
    // Enter Item
    ENTER_STUDENT_ID("Enter student ID\n>> "),
    ENTER_STUDENT_NAME("Enter student name\n>> "),
    ENTER_STUDENT_MAJOR("Enter student Major\n>> "),
    ENTER_COMPLETED_COURSE_ID("Enter completed course ID\n>> "),
    ENTER_WANNA_ENTER_STUDENT_COMPLETED_COURSES("Would you like to add completed course? (y/n)\n>> "),

    // Situation (CRUD)
    ADD_SUCCESS("The student has been added successfully.\n>> "),
    ADD_FAIL("Failed to add a student \n>>"),
    DELETE_SUCCESS("The student has been deleted successfully.\n>> "),

    DELETE_FAIL("Failed to delete a student.\n>> "),
    UPDATE_SUCCESS("The student has been updated successfully.\n>> "),
    UPDATE_FAIL("Failed to update a student \n>> "),

    // Error
    NOT_FOUND("This student does not exist \n>> "),
    INVALID_STUDENT_ID("ID must be 5 digits\n>> "),
    INVALID_STUDENT_NAME("Name must be under 20 characters\n>> "),
    INVALID_STUDENT_MAJOR("Major must be 2 characters in ME, EE, CS\n>> "),
    INVALID_STUDENT_COMPLETED_COURSES("Course ID must be 5 digits and Seperated by ','\n>> "),

    DUPLICATED_STUDENT_ID("Duplicated Student Id\n>> "),
    INVALID_YN_ANSWER("Invalid answer. Please enter 'y' or 'n'\n>> ");


    private final String message;
    public String getMessage(){return message;}
    EStudent(String message){
        this.message = message;
    }
}
