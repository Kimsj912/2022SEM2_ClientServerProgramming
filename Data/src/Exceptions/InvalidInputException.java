package Exceptions;

import java.io.Serializable;

public class InvalidInputException extends Exception implements Serializable {
    public InvalidInputException(){super("It is Invalid Input.");}
    public InvalidInputException(String message){super(message);}
}
