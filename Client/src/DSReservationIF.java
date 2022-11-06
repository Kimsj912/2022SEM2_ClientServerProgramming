import Exceptions.AlreadyExsitException;
import Exceptions.NullDataException;
import Objects.Reservation;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DSReservationIF extends Remote, Serializable {
    // GET
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException;
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException;
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException;
    // CREATE
    boolean makeReservation(String courseId, String studentId) throws RemoteException, NullDataException, AlreadyExsitException;
    // DELETE
    boolean deleteReservation(String courseId, String studentId) throws RemoteException, NullDataException;

}
