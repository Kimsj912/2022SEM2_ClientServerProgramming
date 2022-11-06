import DataServerIF.ReservationIF;
import Exceptions.AlreadyExsitException;
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
        System.out.println("Get Reservation requested.(StudentId : " + studentId + ")");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        ArrayList<Reservation> reservationArrayList = reservationList.getReservationListByStudentId(studentId);
        System.out.println((reservationArrayList.isEmpty()) ? ("Course is not found.\n>>" + studentId) : ("Courses are found.\n>>" + reservationArrayList));
        return reservationArrayList;
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        System.out.println("Get Reservation requested.(CourseId : " + courseId + ")");
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        ArrayList<Reservation> reservationArrayList = reservationList.getReservationListByCourseId(courseId);
        System.out.println((reservationArrayList.isEmpty()) ? ("Course is not found.\n>>" + courseId) : ("Courses are found.\n>>" + reservationArrayList));
        return reservationArrayList;
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        System.out.println("Get Reservation requested.(CourseId : " + courseId + ", StudentId : " + studentId + ")");
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        Reservation reservation = reservationList.getReservationListByBothId(courseId, studentId);
        System.out.println((reservation == null) ? ("Reservation is not found.\n>>" + courseId + ", " + studentId) : ("Reservation is found.\n>>" + reservation));
        return reservation;
    }

    @Override
    public boolean makeReservation(String courseId, String studentId) throws RemoteException, NullDataException, AlreadyExsitException{
        System.out.println("Make Reservation requested.(CourseId : " + courseId + ", StudentId : " + studentId + ")");
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        Reservation reservation = new Reservation(studentId, courseId);
        boolean result = reservationList.makeReservation(reservation);
        System.out.println((result) ? ("Reservation is made.\n>>" + reservation) : ("Reservation is not made.\n>>" + reservation));
        return result;
    }

    @Override
    public boolean deleteReservation(String courseId, String studentId) throws RemoteException, NullDataException{
        System.out.println("Delete Reservation requested.(CourseId : " + courseId + ", StudentId : " + studentId + ")");
        if(courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        if(studentData.getStudentById(studentId) == null) throw new NullDataException("Student is not found.");
        boolean result = reservationList.deleteReservation(courseId, studentId);
        System.out.println((result) ? ("Success to delete Reservation.\n>>" + courseId + ", " + studentId) : ("Failed to delete Reservation\n>>" + courseId + ", " + studentId));
        return result;
    }

}