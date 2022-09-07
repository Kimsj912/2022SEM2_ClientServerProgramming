import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args){
        ServerIF server;
        try{
            server = (ServerIF) Naming.lookup("AddServer");
            System.out.println("저장한 값 : " + server.read());
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
