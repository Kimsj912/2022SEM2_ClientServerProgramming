package MenuScripts;


import Enums.EMajor;

import java.util.Arrays;

public enum EStudent {
    // Enter Item
    ENTER_STUDENT_ID("Enter student ID.\n>> "),
    ENTER_STUDENT_NAME("Enter student name.\n>> "),
    ENTER_STUDENT_MAJOR("Enter student Major among theme."+ Arrays.toString(EMajor.getMajorAbbrList())+"\n>> "),
    ENTER_COMPLETED_COURSE_MULTI("Enter completed course ID.(separated by comma)\n>> "),

    // Situation (CRUD)
    GET_FAIL("Failed to get student."),
    ADD_SUCCESS("The student has been added successfully. "),
    ADD_FAIL("Failed to add a student "),
    ADD_FAIL_ALREADY_EXIST("Failed to add a student. The student already exists."),
    DELETE_SUCCESS("The student has been deleted successfully. "),

    DELETE_FAIL("Failed to delete a student. "),
    UPDATE_SUCCESS("The student has been updated successfully. "),
    UPDATE_FAIL("Failed to update a student  "),
    UPDATE_FAIL_INPUT_COMPLETED_COURSE_IS_NOT_EXIST("Failed to update a student. The course ID does not exist. "),

    // Error
    NOT_FOUND("This student does not exist   "),
    INVALID_STUDENT_ID("ID must be 8 digits "),
    INVALID_STUDENT_NAME("Name must be under 20 characters "),
    INVALID_STUDENT_MAJOR("Major must be one of the following: " + Arrays.toString(EMajor.getMajorAbbrList()));


    private final String message;
    public String getMessage(){return message;}
    EStudent(String message){
        this.message = message;
    }
}
