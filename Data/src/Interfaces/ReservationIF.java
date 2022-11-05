package Interfaces;

import Exceptions.NullDataException;
import Objects.Reservation;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReservationIF {
    // READ
    ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException;
    ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException;
    Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException;

    // CREATE
    boolean makeReservation(String courseId, String studentId) throws RemoteException;

    // DELETE
    boolean deleteReservation(String courseId, String studentId) throws RemoteException;
}
