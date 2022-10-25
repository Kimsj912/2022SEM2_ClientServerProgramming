package Interfaces;

import Exceptions.NullDataException;
import Objects.Course;
import Objects.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote {
    // Student
    public boolean addStudent(Student student) throws RemoteException;
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
    public Student getStudentById(String id) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByLastName(String lastName) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByFirstName(String firstName) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException;
    public boolean deleteStudentById(String studentId) throws RemoteException;
    public boolean updateStudent(Student student, Student newStudent) throws RemoteException;


    public boolean addCourse(Course course) throws RemoteException;
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException;
    public Course getCourseById(String id) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByName(String courseName) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByProfessor(String courseProfessor) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByPreCourse(String coursePreCourse) throws RemoteException, NullDataException;

    public boolean deleteCourseById(String courseId) throws RemoteException;
    public boolean updateCourse(Course course, Course newCourse) throws RemoteException;


    public boolean makeReservation(String studentId, String courseId) throws RemoteException;


    public boolean addConnection(String id) throws RemoteException;
    public boolean deleteConnection(String id) throws RemoteException;
}
