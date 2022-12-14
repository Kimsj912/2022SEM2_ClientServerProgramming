import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Server {

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try {
            DSCourseIF courseData = (DSCourseIF) Naming.lookup("CourseData");
            DSStudentIF studentData = (DSStudentIF) Naming.lookup("StudentData");
            DSReservationIF reservationData = (DSReservationIF) Naming.lookup("ReservationData");

            CourseServer courseServer = new CourseServer(courseData);
            Naming.rebind("CourseServer", courseServer);

            StudentServer studentServer = new StudentServer(studentData);
            studentServer.association(courseServer);
            Naming.rebind("StudentServer", studentServer);

            ReservationServer reservationServer = new ReservationServer(reservationData);
            reservationServer.association(courseServer, studentServer);
            Naming.rebind("ReservationServer", reservationServer);

            System.out.println("Server is Ready");
        } catch (MalformedURLException e) {
            System.out.println("[Error] the name is not an appropriately formatted URL");
        } catch (RemoteException e) {
            System.out.println("[Error] the remote object could not be exported");
        } catch (NotBoundException e) {
            System.out.println("[Error] the name is not currently bound");
        } catch (Exception e) {
            System.out.println("[Error] Unknown Error Occurred");
        }
    }
}

