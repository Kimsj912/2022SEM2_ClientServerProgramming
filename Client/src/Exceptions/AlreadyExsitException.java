package Exceptions;

import java.io.Serializable;

public class AlreadyExsitException extends Exception implements Serializable {
    public AlreadyExsitException(){
        super("Already Exist.");
    }
    public AlreadyExsitException(String message){
        super(message);
    }
}
