import Exceptions.NullDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import Lists.CourseList;
import Lists.ReservationList;
import Lists.StudentList;
import Objects.*;

public class Data extends UnicastRemoteObject implements DataIF {
    protected static StudentList studentList;
    protected static CourseList courseList;
    private static ReservationList reservationList;
    private final Set<String> connectionServerSet; // connection server set

    protected Data() throws IOException{
        super();
        connectionServerSet = new HashSet<>();
        studentList = new StudentList("Students.txt");
        courseList = new CourseList("Courses.txt");
        reservationList = new ReservationList("Reservations.txt");
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try{
            Data data = new Data();
            Naming.rebind("Data", data);
            System.out.println("Database is Ready");
        } catch (ConnectException e){
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
    public Student getStudentById(String id) throws RemoteException{
        System.out.println("Objects.Student requested.\n" + id);
        Student student  = studentList.getStudentRecord(id);
        System.out.println((student == null)?("Objects.Student is not found.\n>>" + id) : ("Objects.Student is found.\n>>" + student));
        return student;
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        return null;
    }


    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        ArrayList<Student> studentArrayList = studentList.getAllStudentRecords();
        if(studentArrayList == null) throw new NullDataException("Objects.Student data isn't initialized.");
        return studentArrayList;
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        studentList.addStudentRecord(student);
        System.out.println("Objects.Student added.\n>>" + student);
        return true;
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        return false;
    }

    @Override
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException{
        return false;
    }

    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all courses data");
        ArrayList<Course> courseArrayList = courseList.getAllCoursesRecords();
        if(courseArrayList == null) throw new NullDataException("Objects.Course data isn't initialized.");
        return courseArrayList;
    }

    @Override
    public Course getCourseById(String courseId) throws RemoteException{
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        courseList.addCourseRecord(course);
        System.out.println("Objects.Course added.\n>>" + course);
        return true;
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        return false;
    }

    @Override
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        return null;
    }

    @Override
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException{
        return false;
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


    @Override
    public boolean makeReservation(String studentId, String courseId) throws RemoteException{

        return false;
    }

    @Override
    public boolean deleteReservation(String courseId, String studentId) throws RemoteException{
        return false;
    }

}

