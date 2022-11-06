package Exceptions;

import java.io.Serializable;

public class InvalidInputException extends Exception implements Serializable {
    public InvalidInputException(String message){
        super(message);
    }
}
