import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Data extends UnicastRemoteObject implements DataIF {

    protected static StudentList studentList;
    private Set<String> set;

    protected Data() throws RemoteException{
        super();
        set = new HashSet<>();
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try{
            Data data = new Data();
            Naming.rebind("Data", data);
            System.out.println("Data is Ready");

            studentList = new StudentList("Students.txt");


        } catch (ConnectException e){
            System.out.println("Server is already running.");
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Student> getAllStudentData() throws RemoteException{
        return studentList.getAllStudentRecords();
    }
}

