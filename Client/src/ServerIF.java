import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    public int add (int a,int b) throws RemoteException;
}
