import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    public void save (String str) throws RemoteException;
    public String read () throws RemoteException;
}
