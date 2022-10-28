package Exceptions;

import Enums.EErrorMessage;

public class InvalidInputException extends Exception {
    public InvalidInputException(){
        super(EErrorMessage.INVALID_INPUT.getMessage());
    }
}
