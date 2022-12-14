package Interfaces;

import Exceptions.NullDataException;
import Objects.Student;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface StudentIF {
    // READ
    ArrayList<Student> getAllStudentsWithPage(int page) throws RemoteException, NullDataException;
    Student getStudentById(String studentId) throws RemoteException, NullDataException;
    ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException;
    ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException;
    ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException;

    // CREATE
    boolean addStudent(Student student) throws RemoteException;

    // DELETE
    boolean deleteStudentById(String studentId) throws RemoteException;

    // UPDATE
    boolean updateStudentById(String studentId, Student newStudent) throws RemoteException;

}
