package Exceptions;


public class EmptyInputException extends Exception{
    public EmptyInputException(){
        super("Nothing was entered.");
    }
}
