import Exceptions.AlreadyExsitException;
import Exceptions.NullDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import Objects.*;

public class Data extends UnicastRemoteObject implements DataIF {
    private final CourseData courseData;
    private final StudentData studentData;
    private final ReservationData reservationData;
    private final Set<String> connectionServerSet; // connection server set

    protected Data() throws IOException{
        super();
        connectionServerSet = new HashSet<>();
        courseData = new CourseData();
        studentData = new StudentData();
        reservationData = new ReservationData();
    }

    private void association(){
        studentData.association(courseData);
        reservationData.association(courseData, studentData);
    }

    public static void main(String[] args){
        try{
            Data data = new Data();
            data.association();
            Naming.rebind("Data", data);
            System.out.println("Database is Ready");
        } catch (ConnectException e){
            System.out.println("[Error] Connection Error Occurred");
        } catch (MalformedURLException e){
            System.out.println("[Error] Malformed URL");
        } catch (RemoteException e){
            e.detail.printStackTrace();
            System.out.println("[Error] System is not ready.");
        } catch (IOException e) {
            System.out.println("[Error] File is not found.");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("[Error] Unknown Error Occurred");
        }
    }

    // Student
    @Override
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        return studentData.getAllStudentData();
    }

    @Override
    public Student getStudentById(String studentId) throws RemoteException, NullDataException{
        return studentData.getStudentById(studentId);
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException{
        return studentData.getStudentsByName(name);
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        return studentData.getStudentsByMajor(major);
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        return studentData.getStudentsByCompletedCourse(courseId);
    }
    @Override
    public boolean addStudent(Student student) throws RemoteException{
        return studentData.addStudent(student);
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        return studentData.deleteStudentById(studentId);
    }

    @Override
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException{
        return studentData.updateStudentById(studentId, newStudent);
    }

    // Course
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        return courseData.getAllCoursesData();
    }
    @Override
    public Course getCourseById(String courseId) throws RemoteException{
        return courseData.getCourseById(courseId);
    }

    @Override
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException{
        return courseData.getCoursesByName(name);
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException{
        return courseData.getCoursesByProfessor(professor);
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException{
        return courseData.getCoursesByPreCourseId(preCourseId);
    }

    @Override
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException{
        return courseData.getCoursesBySemester(semester);
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        return courseData.addCourse(course);
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        return courseData.deleteCourseById(courseId);
    }

    @Override
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException{
        return courseData.updateCourseById(courseId, newCourse);
    }

    // Reservation
    @Override
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException{
        return reservationData.getReservationsByStudentId(studentId);
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        return reservationData.getReservationsByCourseId(courseId);
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        return reservationData.getReservationByBothId(courseId, studentId);
    }
    @Override
    public boolean makeReservation(String studentId, String courseId) throws RemoteException, NullDataException, AlreadyExsitException{
        return reservationData.makeReservation(studentId, courseId);
    }

    @Override
    public boolean deleteReservation(String courseId, String studentId) throws RemoteException, NullDataException{
        return reservationData.deleteReservation(courseId, studentId);
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

