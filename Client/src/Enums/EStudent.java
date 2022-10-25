package Enums;

import static Constants.Constants.*;

public enum EStudent {
    // Enter Item
    studentID("Enter student ID"),
    studentLastName("Enter student Last name"),
    studentFirstName("Enter student First name"),
    studentMajor("Enter student Major"),
    studentCompletedCourses("Would you like to add completed course? (y/n)"),

    // Situation (CRUD)
    ADD_SUCCESS("The student has been added successfully.\n>> "),
    ADD_FAIL("The student has not been added.\n>>"),
    DELETE_SUCCESS("The student has been deleted successfully.\n>> "),

    NOT_FOUND("Object.Student not found.\n>> "),

    // Error

    INVALID_STUDENT_ID("ID must be "+STUDENT_ID_LENGTH+" digits\n>> "),
    INVALID_LAST_NAME("Last Name must be "+STUDENT_LAST_NAME_MIN_LENGTH+"~"+STUDENT_LAST_NAME_MAX_LENGTH+" characters \n>> "),
    INVALID_FIRST_NAME("First name must be "+STUDENT_FIRST_NAME_MIN_LENGTH+"~"+STUDENT_FIRST_NAME_MAX_LENGTH+" characters\n>> "),
    INVALID_MAJOR("Major must be "+STUDENT_MAJOR_LENGTH+" characters\n>> "),
    INVALID_COMPLETED_COURSES("Object.Course ID must be "+COURSE_ID_LENGTH+" digits and Seperated by ','\n>> "),
    INVALID_COMPLETED_COURSES_LENGTH("You can write only "+MAX_COMPLETED_COURSES+" completed courses \n>> "),

    DUPLICATED_STUDENT_ID("Duplicated Object.Student Id\n>> "),
    INVALID_PRE_COURSE("Invalid preCourse Id\n>> "),
    NOT_EXIST_PRE_COURSE("The entered ID of the prerequisite course is a non-existent course ID.\n>> "),
    INVALID_YN_ANSWER("Invalid answer. Please enter 'y' or 'n'\n>> ");


    private final String message;
    public String getMessage(){return message;}
    EStudent(String message){
        this.message = message;
    }
}
