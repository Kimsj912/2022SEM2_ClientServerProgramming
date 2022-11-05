package DataServerIF;

import Exceptions.NullDataException;
import Objects.Course;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CourseIF extends Remote {
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


}
