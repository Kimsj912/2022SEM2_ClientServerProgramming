import Exceptions.NullDataException;
import Objects.Course;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CourseServer extends UnicastRemoteObject implements Serializable, CSCourseIF {
    public final DSCourseIF data;
    public DSCourseIF getData(){return this.data;}

    public CourseServer(DSCourseIF data) throws RemoteException{
        super();
        this.data = data;
    }

    @Override
    public ArrayList<Course> getAllCourses() throws RemoteException, NullDataException{
        System.out.println("returning course's all data");
        ArrayList<Course> courseArrayList = data.getAllCoursesData();
        System.out.println(" >> Success (course : " + courseArrayList + ")");
        return courseArrayList;
    }

    @Override
    public Course getCourseById(String id) throws RemoteException, NullDataException{
        System.out.println("get course by id " + id);
        Course course = data.getCourseById(id);
        System.out.println(" >> Success (course : " + course + ")");
        return course;
    }

    @Override
    public ArrayList<Course> getCoursesByName(String courseName) throws RemoteException, NullDataException{
        System.out.println("get courses by name " + courseName);
        ArrayList<Course> courseList = data.getCoursesByName(courseName);
        System.out.println(" >> Success (course : " + courseList.toString() + ")");
        return courseList;
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String courseProfessor) throws RemoteException, NullDataException{
        System.out.println("get courses by professor : " + courseProfessor);
        ArrayList<Course> courseList = data.getCoursesByProfessor(courseProfessor);
        System.out.println(" >> Success (course : " + courseList.toString() + ")");
        return courseList;
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException{
        System.out.println("get courses by preCourseId : " + preCourseId);
        ArrayList<Course> courseList = data.getCoursesByPreCourseId(preCourseId);
        System.out.println(" >> Success (course : " + courseList.toString() + ")");
        return courseList;
    }

    @Override
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException{
        System.out.println("get courses by semester : " + semester);
        ArrayList<Course> courseList = data.getCoursesBySemester(semester);
        System.out.println(" >> Success (course : " + courseList.toString() + ")");
        return courseList;
    }

    @Override
    public boolean isCourseIdExist(String courseId) throws RemoteException{
        System.out.println("is course exist : " + courseId);
        boolean isExist = data.getCourseById(courseId) != null;
        System.out.println(">> " + (isExist));
        return isExist;
    }

    @Override
    public boolean isMultiCourseIdExist(ArrayList<String> courseIdList) throws RemoteException{
        System.out.println("is multi course exist : " + courseIdList.toString());
        boolean isExist = true;
        for (String courseId : courseIdList) {
            if (!data.isCourseIdExist(courseId)) {
                isExist = false;
                break;
            }
        }
        System.out.println(">> " + isExist);
        return isExist;
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        System.out.print("Course added : " + course);
        if (data.addCourse(course)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        System.out.println("Course deleted : " + courseId);
        if (data.deleteCourseById(courseId)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }

    @Override
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException{
        System.out.println("Course updated : " + courseId);
        if (data.updateCourseById(courseId, newCourse)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }
}