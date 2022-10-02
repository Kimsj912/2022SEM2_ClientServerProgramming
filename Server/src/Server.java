import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Server extends UnicastRemoteObject implements ServerIF {

    private static final String serverId = "server2345";
    private static DataIF data;
    private static HashSet<String> clientList;

    protected Server() throws RemoteException{
        super();
        clientList = new HashSet<>();
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try {
            data = (DataIF) Naming.lookup("Data");
            data.addDataConnection(serverId);

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

    @Override
    public ArrayList<Student> getAllStudentData() throws RemoteException{
        System.out.println("returning all student data");
        return data.getAllStudentData();
    }

    @Override
    public ArrayList<Course> getAllCoursesData() throws RemoteException{
        System.out.println("returning all course data");
        return data.getAllCoursesData();
    }

    @Override
    public void addStudent(Student student) throws RemoteException{
        System.out.println("Student added.\n>>" + student);
        data.addStudent(student);
    }
    @Override
    public void addCourse(Course course) throws RemoteException{
        System.out.println("Course added.\n>>" + course);
        data.addCourse(course);
    }

    @Override
    public void deleteStudent(String student) throws RemoteException{
        System.out.println("Student deleted.\n>>" + student);
        data.deleteStudent(student);
    }

    @Override
    public void deleteCourse(String student) throws RemoteException{
        System.out.println("Course deleted.\n>>" + student);
        data.deleteCourse(student);
    }

    @Override
    public Student getStudent(String id) throws RemoteException{
        System.out.println("Student requested.\n>>" + id);
        return data.getStudent(id);
    }

    @Override
    public Course getCourse(String id) throws RemoteException{
        System.out.println("Course requested.\n>>" + id);
        return data.getCourse(id);
    }

    @Override
    public void addConnection(String id) throws RemoteException{
        System.out.println("Client connected.\n>>" + id);
        clientList.add(id);
    }

    @Override
    public void deleteConnection(String id) throws RemoteException{
        System.out.println("Client disconnected.\n>>" + id);
        clientList.remove(id);
    }

}

