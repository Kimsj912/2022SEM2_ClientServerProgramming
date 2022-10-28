//package Enums;
//
//import Exceptions.EmptyInputException;
//import Exceptions.InvalidInputException;
//import Exceptions.NullDataException;
//
//import java.io.IOException;
//
//public enum EErrorMessage {
//    // Connection
//    SERVER_NOT_READY("Server is not ready. Please try again later."),
//
//    // Input
//    WRONG_INPUT_NUMBER(NumberFormatException.class, "Wrong input. Input Menu's number"),
//    Empty_Input(EmptyInputException.class ,"[Error] Any word is inputted"),
//
//    // Print
//    NULL_DATA(NullDataException.class, "No data found"),
//
//    INVALID_INPUT(InvalidInputException.class, "Invalid input"),
//
//    // Basic Error (Must be placed at the end)
//    BASIC_ERROR(Exception.class)"Error is occurred.");
//
//
//    public Class<? extends Exception> errorCode;
//    public String message;
//    public Class<? extends Exception> getErrorCode(){return this.errorCode;}
//    public String getMessage(){return this.message;}
//    EErrorMessage(Class<? extends Exception> errorCode, String message){
//        this.errorCode = errorCode;
//        this.message = message;
//    }
//}
