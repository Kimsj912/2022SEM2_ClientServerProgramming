import Exceptions.NullDataException;
import Objects.Student;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DSStudentIF extends Remote, Serializable {
    // GET
    public ArrayList<Student> getAllStudents() throws RemoteException, NullDataException;
    public Student getStudentById(String studentId) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException;
    // CREATE
    public boolean addStudent(Student student) throws RemoteException;
    // DELETE
    public boolean deleteStudentById(String studentId) throws RemoteException;
    // UPDATE
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException;
    // CHECK
    boolean isStudentIdExist(String studentId) throws RemoteException;
}
