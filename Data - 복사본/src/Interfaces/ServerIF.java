package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote, CourseIF, StudentIF, ReservationIF {
    public boolean addConnection(String id) throws RemoteException;
    public boolean deleteConnection(String id) throws RemoteException;
}
