package Exceptions;

import java.io.Serializable;

public class NullDataException extends Exception implements Serializable {
    public NullDataException(){super("Data does not exist.");}
    public NullDataException(String message){super(message);}

}
