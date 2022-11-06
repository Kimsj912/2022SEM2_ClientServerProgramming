import Exceptions.AlreadyExsitException;
import Exceptions.NullDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import Objects.*;

public class Data {

    public static void main(String[] args){
        try{
            CourseData courseData = new CourseData();
            StudentData studentData = new StudentData();
            ReservationData reservationData = new ReservationData();

            studentData.association(courseData);
            reservationData.association(courseData, studentData);

            Naming.rebind("CourseData", courseData);
            Naming.rebind("StudentData", studentData);
            Naming.rebind("ReservationData", reservationData);

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
}

