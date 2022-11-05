import DataServerIF.CourseIF;
import DataServerIF.ReservationIF;
import DataServerIF.StudentIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataIF extends Remote, StudentIF, CourseIF, ReservationIF {

    // Connection
    public boolean addDataConnection(String id) throws RemoteException;
    public boolean deleteDataConnection(String id) throws RemoteException;

}
