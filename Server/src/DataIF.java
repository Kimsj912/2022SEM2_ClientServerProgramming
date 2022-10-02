import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote {
    public ArrayList<Student> getAllStudentData() throws RemoteException;

    public void addStudent(Student student) throws RemoteException;
    public void deleteStudent(String student) throws RemoteException;

    public ArrayList<Course> getAllCoursesData() throws RemoteException;
    public void addCourse(Course course) throws RemoteException;

    public void deleteCourse(String student) throws RemoteException;

    public Student getStudent(String id) throws RemoteException;

    public Course getCourse(String id) throws RemoteException;

    public void addDataConnection(String id) throws RemoteException;
    public void deleteDataConnection(String id) throws RemoteException;

}
