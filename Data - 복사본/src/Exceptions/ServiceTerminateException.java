package Exceptions;

public class ServiceTerminateException extends Throwable {
    public ServiceTerminateException(){
        super("Service Terminated.");
    }
}
