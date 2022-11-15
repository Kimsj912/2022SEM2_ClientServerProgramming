import Exceptions.NullDataException;
import Objects.Course;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CSCourseIF extends Remote, Serializable {
    // GET
    ArrayList<Course> getAllCourses() throws RemoteException, NullDataException;
    Course getCourseById(String courseId) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException;
    // CREATE
    boolean addCourse(Course course) throws RemoteException;
    // DELETE
    boolean deleteCourseById(String courseId) throws RemoteException;
    // UPDATE
    boolean updateCourseById(String courseId, Course newCourse) throws RemoteException;
    // CHECK
    boolean isCourseIdExist(String courseId) throws RemoteException;
    boolean isMultiCourseIdExist(ArrayList<String> courseIdList) throws RemoteException;
}
