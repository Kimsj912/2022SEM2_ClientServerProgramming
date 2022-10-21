package Enums;

import java.io.IOException;

public enum EErrorMessage {
    // Input
    NO_INPUT(null,"Any word is inputted"),
    WRONG_INPUT_NUMBER("NumberFormatException", "Wrong input. Input Menu's number"),
    // Print
    ERROR_OCCURRED_WHILE_READING_INPUT("IOException", "Error occurred while reading input"),
    NULL_DATA("NullDataException", "No data found"),

    INVALID_INPUT("InvalidInputException", "Invalid input"),

    // Basic Error (Must be placed at the end)
    BASIC_ERROR("Exception","Error is occurred.");


    public String errorCode;
    public String message;
    public String getErrorCode(){return this.errorCode;}
    public String getMessage(){return this.message;}
    EErrorMessage(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
