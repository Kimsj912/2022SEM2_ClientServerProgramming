package Exceptions;

import java.io.Serializable;

public class ServerProcessErrorException extends Exception implements Serializable {
    public ServerProcessErrorException(String message){
        super(message);
    }
}
