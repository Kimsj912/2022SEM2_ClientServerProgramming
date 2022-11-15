import Exceptions.AlreadyExistException;
import Exceptions.NullDataException;
import Objects.Reservation;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CSReservationIF extends Remote, Serializable {
    // GET
    ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException;
    ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException;
    Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException;
    // CREATE
    boolean makeReservation(String courseId, String studentId) throws RemoteException, NullDataException, AlreadyExistException;
    // DELETE
    boolean deleteReservation(String courseId, String studentId) throws RemoteException, NullDataException;
}
