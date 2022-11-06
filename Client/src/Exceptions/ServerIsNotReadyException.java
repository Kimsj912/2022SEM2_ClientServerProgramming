package Exceptions;

import java.io.Serializable;

public class ServerIsNotReadyException extends Exception implements Serializable {
    public ServerIsNotReadyException(){
        super("Server is not ready.");
        System.exit(0);
    }
}
