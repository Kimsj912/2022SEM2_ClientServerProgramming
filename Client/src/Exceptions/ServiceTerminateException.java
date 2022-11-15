package Exceptions;

import java.io.Serializable;

public class ServiceTerminateException extends Exception implements Serializable {
    public ServiceTerminateException(){
        System.out.println("Terminate Service.");
        System.exit(0);
    }
}
