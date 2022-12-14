package Exceptions;

import java.io.Serializable;

public class AlreadyExistException extends Exception implements Serializable {
    public AlreadyExistException(){
        super("Already Exist.");
    }
    public AlreadyExistException(String message){
        super(message);
    }
}
