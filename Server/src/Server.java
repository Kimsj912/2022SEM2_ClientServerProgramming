import Exceptions.NullDataException;

import java.lang.reflect.Array;
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
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        return data.getAllStudentData();
    }

    @Override
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all course data");
        return data.getAllCoursesData();
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        System.out.println("Student added.\n>> " + student);
        return data.addStudent(student);
    }
    @Override
    public boolean addCourse(Course course) throws RemoteException{
        System.out.println("Course added.\n>> " + course);
        return data.addCourse(course);
    }

    @Override
    public boolean deleteStudent(String student) throws RemoteException{
        System.out.println("Student deleted.\n>> " + student);
        return data.deleteStudent(student);
    }

    @Override
    public boolean deleteCourse(String student) throws RemoteException{
        System.out.println("Course deleted.\n>> " + student);
        return data.deleteCourse(student);
    }

    @Override
    public Student getStudent(String id) throws RemoteException{
        System.out.println("Student requested.\n>> " + id);
        return data.getStudent(id);
    }

    @Override
    public Course getCourse(String id) throws RemoteException{
        System.out.println("Course requested.\n>> " + id);
        return data.getCourse(id);
    }

    @Override
    public boolean addConnection(String id) throws RemoteException{
        if(clientList.contains(id)){
            System.out.println("The Client is already connected.\n>> "+id);
            return false;
        }
        clientList.add(id);
        System.out.println("New Client Connected\n>> " + id);
        return true;
    }

    @Override
    public boolean deleteConnection(String id) throws RemoteException{
        if(clientList.contains(id)) {
            System.out.println("Client Connection terminated.\n>> " + id);
            clientList.remove(id);
            return true;
        }
        System.out.println("Failed: Anonymous client disconnection requested.\n>> " + id);
        return false;
    }

}

