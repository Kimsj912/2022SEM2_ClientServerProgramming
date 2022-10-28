package Interfaces;

import Exceptions.NullDataException;
import Objects.Course;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CourseIF {
    // GET
    ArrayList<Course> getAllCoursesWithPage(int page) throws RemoteException, NullDataException;
    Course getCourseById(String courseId) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException;
    ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException;

    // ADD
    boolean addCourse(Course course) throws RemoteException;
    boolean deleteCourseById(String courseId) throws RemoteException;
    boolean updateCourseById(String courseId, Course newCourse) throws RemoteException;


}
