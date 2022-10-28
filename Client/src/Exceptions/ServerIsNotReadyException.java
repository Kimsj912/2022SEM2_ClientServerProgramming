package Exceptions;

import Enums.EErrorMessage;

public class ServerIsNotReadyException extends Exception {
    public ServerIsNotReadyException(){
        super(EErrorMessage.SERVER_NOT_READY.getMessage());
        System.exit(0);
    }
}
