//import Exceptions.NullDataException;
//import Objects.Course;
//import Objects.Reservation;
//import Objects.Student;
//
//import java.rmi.Remote;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//public interface DataIF extends Remote {
//    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
//
//    public boolean addStudent(Student student) throws RemoteException;
//    public boolean deleteStudent(String student) throws RemoteException;
//
//    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException;
//    public boolean addCourse(Course course) throws RemoteException;
//
//    public boolean deleteCourse(String student) throws RemoteException;
//
//    public Student getStudentById(String id) throws RemoteException;
//
//    public Course getCourse(String id) throws RemoteException;
//
//    public boolean addDataConnection(String id) throws RemoteException;
//    public boolean deleteDataConnection(String id) throws RemoteException;
//
//    public ArrayList<Reservation> getReservations() throws RemoteException;
//
//    void makeReservation(String studentId, String courseId) throws RemoteException;
//}

import Exceptions.NullDataException;
import Objects.Course;
import Objects.Reservation;
import Objects.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote {
    // Student
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
    public Student getStudentById(String studentId) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException;
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException;

    public boolean addStudent(Student student) throws RemoteException;
    public boolean deleteStudentById(String studentId) throws RemoteException;
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException;

    // Course
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException;
    public Course getCourseById(String courseId) throws RemoteException;
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException;

    public boolean addCourse(Course course) throws RemoteException;
    public boolean deleteCourseById(String courseId) throws RemoteException;
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException;

    // Reservation
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException;
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException;
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException;
    boolean makeReservation(String studentId, String courseId) throws RemoteException;
    boolean deleteReservation(String courseId, String studentId) throws RemoteException;

    // Connection
    public boolean addDataConnection(String id) throws RemoteException;
    public boolean deleteDataConnection(String id) throws RemoteException;

}
