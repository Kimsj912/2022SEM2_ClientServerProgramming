package Exceptions;

public class ServerIsNotReadyException extends Exception {
    public ServerIsNotReadyException(){
        super("Server is not ready.");
        System.exit(0);
    }
}
