import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;



public class Data extends UnicastRemoteObject implements DataIF {
    private static final long serialVersionUID = 4974527148936298033L;

    protected static StudentList studentList;
    protected static CourseList courseList;
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

            studentList = new StudentList("Students.txt");
            courseList = new CourseList("Courses.txt");

            System.out.println("Data is Ready");

        } catch (ConnectException e){
            System.out.println("Server is already running.");
            System.out.println("Problem >> "+  e.detail.toString());
            System.exit(0);
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
            System.out.println("Data store is not ready.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("File is not found.");
            System.exit(0);
        }
    }

    public ArrayList<Student> getAllStudentData() throws RemoteException{
        return studentList.getAllStudentRecords();
    }
    public ArrayList<Course> getAllCoursesData() throws RemoteException{
        return courseList.getAllCoursesRecords();
    }
}

