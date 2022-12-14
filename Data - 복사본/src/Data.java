import Exceptions.NullDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import Interfaces.*;
import Objects.*;


public class Data extends UnicastRemoteObject implements DataIF {

    private static final String dataName = "Data";
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
        try{
            Data data = new Data();
            Naming.rebind(dataName, data);
            System.out.println("Database is Ready");
        } catch (ConnectException e){
            System.out.println("[Error] Connection Error Occurred");
        } catch (MalformedURLException | RemoteException e) {
            System.out.println("[Error] System is not ready.");
        } catch (IOException e) {
            System.out.println("[Error] File is not found.");
        } catch (Exception e) {
            System.out.println("[Error] Unknown Error Occurred");
        }finally {
            System.exit(0);
        }
    }

    @Override
    public Student getStudentById(String id) throws RemoteException{
        System.out.println("Student requested.\n" + id);
        Student student  = studentList.getStudentById(id);
        System.out.println((student == null)?("Student is not found.\n>>" + id) : ("Student is found.\n>>" + student));
        return student;
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException{
        return studentList.getStudentsByName(name);
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        return studentList.getStudentByMajor(major);
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        return studentList.getStudentsByCompletedCourse(courseId);
    }


    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        ArrayList<Student> studentArrayList = studentList.getAllStudentRecords();
        if(studentArrayList == null) throw new NullDataException("Student data isn't initialized.");
        return studentArrayList;
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        return studentList.addStudent(student);
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        return studentList.deleteStudentById(studentId);
    }

    @Override
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException{
        return studentList.updateStudentById(studentId, newStudent);
    }

    // Course
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all courses data");
        ArrayList<Course> courseArrayList = courseList.getAllCoursesRecords();
        if(courseArrayList == null) throw new NullDataException("Course data isn't initialized.");
        return courseArrayList;
    }

    @Override
    public Course getCourseById(String courseId) throws RemoteException{
        return courseList.getCourseById(courseId);
    }

    @Override
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException{
        return courseList.getCourseByCourseName(name);
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException{
        return courseList.getCourseByProfessor(professor);
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException{
        return courseList.getCoursesByPreCourseId(preCourseId);
    }

    @Override
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException{
        return courseList.getCourseBySemester(semester);
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        System.out.println("Course added.\n>>" + course);
        return courseList.addCourse(course);
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        return courseList.deleteCourseById(courseId);
    }

    @Override
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException{
        return courseList.updateCourseById(courseId, newCourse);
    }

    // Reservation
    @Override
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException{
        return reservationList.getReservationsByStudentId(studentId);
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        return reservationList.getReservationsByCourseId(courseId);
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        return reservationList.getReservationByBothId(courseId, studentId);
    }
    @Override
    public boolean makeReservation(String studentId, String courseId) throws RemoteException{
        Reservation reservation = new Reservation(studentId, courseId);
        return reservationList.makeReservation(reservation);

    }

    @Override
    public boolean deleteReservation(String courseId, String studentId){
        return reservationList.deleteReservationById(courseId, studentId);
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

