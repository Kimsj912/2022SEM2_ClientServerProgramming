package Exceptions;


import java.io.Serializable;

public class EmptyInputException extends Exception implements Serializable {
    public EmptyInputException(){
        super("Nothing was entered.");
    }
}
