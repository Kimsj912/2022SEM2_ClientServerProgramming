import Exceptions.NullDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Data extends UnicastRemoteObject implements DataIF {
    protected static StudentList studentList;
    protected static CourseList courseList;
    private final Set<String> connectionServerSet; // connection server set

    protected Data() throws RemoteException{
        super();
        connectionServerSet = new HashSet<>();
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try{
            Data data = new Data();
            Naming.rebind("Data", data);

            studentList = new StudentList("Students.txt");
            courseList = new CourseList("Courses.txt");

            System.out.println("Database is Ready");

        } catch (ConnectException e){
            System.out.println("Server is already running.");
            System.out.println("Problem >> "+  e.detail.toString());
            System.exit(0);
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
            System.out.println("Data store is not ready.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("File is not found.");
            System.exit(0);
        }
    }

    @Override
    public Student getStudent(String id) throws RemoteException{
        System.out.println("Student requested.\n" + id);
        Student student  = studentList.getStudentRecord(id);
        System.out.println((student == null)?("Student is not found.\n>>" + id) : ("Student is found.\n>>" + student));
        return student;
    }


    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        ArrayList<Student> studentArrayList = studentList.getAllStudentRecords();
        if(studentArrayList == null) throw new NullDataException("Student data isn't initialized.");
        return studentArrayList;
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        studentList.addStudentRecord(student);
        System.out.println("Student added.\n>>" + student);
        return true;
    }

    @Override
    public boolean deleteStudent(String student) throws RemoteException{
        boolean result = studentList.deleteStudentRecord(student);
        System.out.println((result? "The Student has been successfully deleted.\n>>": "The Student is not found.\n >>")+student);
        return result;
    }


    @Override
    public Course getCourse(String id) throws RemoteException{
        System.out.println("Course requested.\n" + id);
        Course course  = courseList.getCourseRecord(id);
        System.out.println((course == null)?("Course is not found.\n>>" + id) : ("Course is found.\n>>" + course));
        return course;
    }



    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all courses data");
        ArrayList<Course> courseArrayList = courseList.getAllCoursesRecords();
        if(courseArrayList == null) throw new NullDataException("Course data isn't initialized.");
        return courseArrayList;
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        courseList.addCourseRecord(course);
        System.out.println("Course added.\n>>" + course);
        return true;
    }

    @Override
    public boolean deleteCourse(String course) throws RemoteException{
        boolean result = courseList.deleteCourseRecord(course);
        System.out.println((result? "The Course has been successfully deleted.\n>>": "The Course is not found.\n >>")+course);
        return result;
    }

    @Override
    public boolean addDataConnection(String id) throws RemoteException{
        if(connectionServerSet.contains(id)){
            System.out.println("The server is already connected.\n>>"+id);
            return false;
        }
        connectionServerSet.add(id);
        System.out.println("New Server Connected.\n>>" + id);
        return true;
    }

    @Override
    public boolean deleteDataConnection(String id) throws RemoteException{
        if(connectionServerSet.contains(id)){
            connectionServerSet.remove(id);
            System.out.println("Server connection terminated.\n>>" + id);
            return true;
        }
        System.out.println("Failed: Anonymous server disconnection requested.\n>>" + id);
        return false;
    }

}

