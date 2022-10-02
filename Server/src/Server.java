import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Server extends UnicastRemoteObject implements ServerIF {
    private static DataIF data;
    private Set<String> set;

    protected Server() throws RemoteException{
        super();
        set = new HashSet<>();
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try {
            data = (DataIF) Naming.lookup("Data");
            System.out.println(data.getAllCoursesData());
            System.out.println(data.getAllStudentData());

            Server server = new Server();
            Naming.rebind("Server", server);

            System.out.println("Server is Ready");
        } catch (MalformedURLException | RemoteException  e) {
            e.printStackTrace();
            System.out.println("Server is not ready.");
            System.exit(0);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Student> getAllStudentData() throws RemoteException{
        return data.getAllStudentData();
    }
    public ArrayList<Course> getAllCoursesData() throws RemoteException{
        return data.getAllCoursesData();
    }

}

