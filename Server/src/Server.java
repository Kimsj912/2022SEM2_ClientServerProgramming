import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerIF {
    protected Server() throws RemoteException{
        super();
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try{
            Server server = new Server();
            Naming.rebind("AddServer", server);
            System.out.println("Server is ready.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public int add (int a,int b) throws RemoteException{
        System.out.println("Server`s response !!!");
        return a+b;
    }
}

