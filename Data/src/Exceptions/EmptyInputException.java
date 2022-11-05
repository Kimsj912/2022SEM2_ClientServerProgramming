package Exceptions;


public class EmptyInputException extends Exception{
    public EmptyInputException(){
        super("Input is empty.");
    }
}
