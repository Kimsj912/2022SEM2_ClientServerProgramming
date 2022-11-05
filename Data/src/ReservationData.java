import DataServerIF.ReservationIF;
import Exceptions.NullDataException;
import Lists.ReservationList;
import Objects.Reservation;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ReservationData implements Serializable, ReservationIF {
    // Attributes
    private final ReservationList reservationList;
    // Associations
    private CourseData courseData;
    private StudentData studentData;

    public ReservationData() throws IOException{
        reservationList = new ReservationList("Reservations.txt");
    }
    public void association(CourseData courseData, StudentData studentData){
        this.courseData = courseData;
        this.studentData = studentData;
    }

    @Override
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException{
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        return reservationList.getReservationListByStudentId(studentId);
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        return reservationList.getReservationListByCourseId(courseId);
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        return reservationList.getReservationListByBothId(courseId, studentId);
    }

    @Override
    public boolean makeReservation(String courseId, String studentId) throws RemoteException, NullDataException{
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        Reservation reservation = new Reservation(studentId, courseId);
        return reservationList.makeReservation(reservation);

    }

    @Override
    public boolean deleteReservation(String courseId, String studentId) throws RemoteException, NullDataException{
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        return reservationList.deleteReservation(courseId, studentId);
    }

}