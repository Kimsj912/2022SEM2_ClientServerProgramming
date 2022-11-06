package Exceptions;


import java.io.Serializable;

public class NullDataException extends Exception implements Serializable {
    public NullDataException(String message){
        super(message);
    }

    public NullDataException(){
        super("Data does not exist.");
    }

}
