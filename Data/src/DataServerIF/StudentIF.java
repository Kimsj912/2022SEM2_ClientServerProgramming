package DataServerIF;

import Exceptions.NullDataException;
import Objects.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface StudentIF extends Remote {
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
    public Student getStudentById(String studentId) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException;

    public boolean addStudent(Student student) throws RemoteException;
    public boolean deleteStudentById(String studentId) throws RemoteException;
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException;
}
