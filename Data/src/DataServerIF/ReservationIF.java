package DataServerIF;

import Exceptions.NullDataException;
import Objects.Reservation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReservationIF extends Remote {
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException;
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException;
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException;
    boolean makeReservation(String studentId, String courseId) throws RemoteException;
    boolean deleteReservation(String courseId, String studentId) throws RemoteException;

}
