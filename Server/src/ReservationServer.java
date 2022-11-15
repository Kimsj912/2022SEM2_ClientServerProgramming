import Exceptions.AlreadyExistException;
import Exceptions.NullDataException;
import Objects.Course;
import Objects.Reservation;
import Objects.Student;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ReservationServer extends UnicastRemoteObject implements Serializable, CSReservationIF {
    private final DSReservationIF data;
    private CourseServer courseServer;
    private StudentServer studentServer;

    public DSReservationIF getData(){return this.data;}
    public ReservationServer(DSReservationIF data) throws RemoteException{
        super();
        this.data = data;
    }
    public void association(CourseServer courseServer, StudentServer studentServer){
        this.courseServer = courseServer;
        this.studentServer = studentServer;
    }

    @Override
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException{
        System.out.println("get reservations by studentId : " + studentId);
        return data.getReservationsByStudentId(studentId);
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        System.out.println("get reservations by courseId : " + courseId);
        return data.getReservationsByCourseId(courseId);
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        System.out.println("get reservation by both id : " + courseId + ", " + studentId);
        return data.getReservationByBothId(courseId, studentId);
    }

    @Override
    public boolean makeReservation(String courseId, String studentId) throws RemoteException, NullDataException, AlreadyExistException{
        System.out.println("make reservation : " + courseId + ", " + studentId);
        Student student = studentServer.getData().getStudentById(studentId);
        if (student == null) throw new NullDataException("Student is not exist");
        if (student.getCompletedCourses().contains(courseId))
            throw new NullDataException("Student already reserved this course.");
        Course course = courseServer.getData().getCourseById(courseId);
        if (course == null) throw new NullDataException("Course is not exist");
        if (course.getPreCourse() != null) {
            for (String preCourse : course.getPreCourse()) {
                if (!student.getCompletedCourses().contains(preCourse)) {
                    throw new NullDataException("Student didn't take pre-course.");
                }
            }
        }
        if (data.makeReservation(courseId, studentId)) {
            System.out.println(" >> Success (" + courseId + ", " + studentId + ")");
            return true;
        } else {
            System.out.println(" >> Failed (" + courseId + ", " + studentId + ")");
            return false;
        }
    }

    @Override
    public boolean deleteReservation(String courseId, String studentId) throws RemoteException, NullDataException{
        System.out.println("delete reservation : (" + courseId + ", " + studentId + ")");
        if (data.deleteReservation(courseId, studentId)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }

}