package Exceptions;

public class AlreadyExsitException extends Exception{
    public AlreadyExsitException(){
        super("Already Exist.");
    }
    public AlreadyExsitException(String message){
        super(message);
    }
}
