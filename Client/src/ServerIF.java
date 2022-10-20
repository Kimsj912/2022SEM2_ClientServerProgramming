import Exceptions.NullDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote {
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;

    public boolean addStudent(Student student) throws RemoteException;
    public boolean deleteStudent(String student) throws RemoteException;

    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException;
    public boolean addCourse(Course course) throws RemoteException;

    public boolean deleteCourse(String student) throws RemoteException;

    public Student getStudent(String id) throws RemoteException;

    public Course getCourse(String id) throws RemoteException;

    public boolean addConnection(String id) throws RemoteException;
    public boolean deleteConnection(String id) throws RemoteException;

}
