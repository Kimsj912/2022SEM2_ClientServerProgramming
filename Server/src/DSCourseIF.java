import Exceptions.NullDataException;
import Objects.Course;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DSCourseIF extends Remote, Serializable {
    // GET
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException;
    public Course getCourseById(String courseId) throws RemoteException;
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException;
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException;
    // CREATE
    public boolean addCourse(Course course) throws RemoteException;
    // DELETE
    public boolean deleteCourseById(String courseId) throws RemoteException;
    // UPDATE
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException;
    // CHECK
    public boolean isCourseIdExist(String courseId) throws RemoteException;

}
