import DataServerIF.CourseIF;
import Exceptions.NullDataException;
import Lists.CourseList;
import Objects.Course;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CourseData implements Serializable, CourseIF {
    private final CourseList courseList;

    public CourseData() throws IOException{
        this.courseList = new CourseList("Courses.txt");
    }

    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all courses data");
        return courseList;
    }

    @Override
    public Course getCourseById(String courseId) throws RemoteException{
        System.out.println("Get Course requested.(CourseId : " + courseId + ")");
        Course course = courseList.getCourseById(courseId);
        System.out.println((course == null) ? ("Course is not found.\n>>" + courseId) : ("Course is found.\n>>" + course));
        return course;
    }

    @Override
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(CourseName : " + name + ")");
        ArrayList<Course> courseArrayList = courseList.getCourseByCourseName(name);
        System.out.println((courseArrayList.isEmpty()) ? ("Course is not found.\n>>" + name) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(Professor : " + professor + ")");
        ArrayList<Course> courseArrayList = courseList.getCourseByProfessor(professor);
        System.out.println((courseArrayList.isEmpty()) ? ("Course is not found.\n>>" + professor) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(PreCourseId : " + preCourseId + ")");
        ArrayList<Course> courseArrayList = courseList.getCoursesByPreCourseId(preCourseId);
        System.out.println((courseArrayList.isEmpty()) ? ("Course is not found.\n>>" + preCourseId) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(Semester : " + semester + ")");
        ArrayList<Course> courseArrayList = courseList.getCourseBySemester(semester);
        System.out.println((courseArrayList.isEmpty()) ? ("Course is not found.\n>>" + semester) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        System.out.println("Add Course requested. (Course : " + course + ")");
        boolean result = courseList.add(course);
        System.out.println((result) ? ("Success to add Course \n>>" + course) : ("Failed to add Course\n>>" + course));
        return result;
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        System.out.println("Delete Course requested. (CourseId : " + courseId + ")");
        boolean result = courseList.deleteCourseById(courseId);
        System.out.println((result) ? ("Success to delete Course \n>>" + courseId) : ("Failed to delete Course\n>>" + courseId));
        return result;
    }

    @Override
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException{
        System.out.println("Update Course requested. (CourseId : " + courseId + " -> " + newCourse + ")");
        boolean result = courseList.updateCourseById(courseId, newCourse);
        System.out.println((result) ? ("Success to update Course \n>>" + courseId + " -> " + newCourse) : ("Failed to update Course\n>>" + courseId + " -> " + newCourse));
        return result;
    }
}