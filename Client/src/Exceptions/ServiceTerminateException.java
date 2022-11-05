package Exceptions;

public class ServiceTerminateException extends Throwable {
    public ServiceTerminateException(){
        System.out.println("Terminate Service.");
        System.exit(0);
    }
}
