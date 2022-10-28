package Exceptions;

import Enums.EErrorMessage;

public class EmptyInputException extends Exception{
    public EmptyInputException(){
        super(EErrorMessage.Empty_Input.getMessage());
    }
}
