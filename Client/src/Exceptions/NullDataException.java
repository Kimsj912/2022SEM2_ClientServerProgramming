package Exceptions;


public class NullDataException extends Exception{
    public NullDataException(String message){
        super(message);
    }

    public NullDataException(){
        super("Data does not exist.");
    }

}
