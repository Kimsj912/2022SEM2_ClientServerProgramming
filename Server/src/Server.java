import java.net.MalformedURLException;
import java.rmi.Naming;
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
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    String info = "";
    @Override
    public void save(String str) throws RemoteException{
        System.out.println("save info : " + str);
        info = str;
    }

    @Override
    public String read() throws RemoteException{
        System.out.println("read info : " + info);
        return info;
    }
}

