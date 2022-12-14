import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import Menus.MMainMenu;
import Menus.Course.MSelectCourseMenu;
import Menus.Reservation.MSelectReservationMenu;
import Menus.Student.MSelectStudentMenu;
import Utils.Print.Printer;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client extends CommonClient {
    private StudentClient studentClient;
    private CourseClient courseClient;
    private ReservationClient reservationClient;

    public static void main(String[] args) throws IOException, NotBoundException, ServiceTerminateException, EmptyInputException{
        Client client = new Client();
        client.run();
    }

    public Client () throws IOException, NotBoundException {
        try{
            CSCourseIF courseServer = (CSCourseIF) Naming.lookup("CourseServer");
            CSStudentIF studentServer = (CSStudentIF) Naming.lookup("StudentServer");
            CSReservationIF reservationServer = (CSReservationIF) Naming.lookup("ReservationServer");

            this.courseClient = new CourseClient(courseServer);
            this.studentClient = new StudentClient(studentServer);
            this.reservationClient = new ReservationClient(reservationServer);

            this.studentClient.initialize(courseServer);
        } catch (RemoteException e){
            e.printStackTrace();
            System.out.println("Server is not running");
            System.exit(0);
        }
    }

    public void run() throws IOException{
        while(true) {
            try{
                String method = Printer.selectMenu(MMainMenu.class, "Main Menu");
                if(method == null) return;
                invokeMethod(this.getClass(), method, this);
            } catch (EmptyInputException ignored){}
            catch (ServiceTerminateException e){exit();}
        }
    }

    public void selectCourse() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(MSelectCourseMenu.class, "Select Course Menu", CourseClient.class, this.courseClient);
    }

    public void selectStudent() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(MSelectStudentMenu.class, "Select Student Menu", StudentClient.class, this.studentClient);
    }

    public void selectReservation() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(MSelectReservationMenu.class, "Select Reservation Menu", ReservationClient.class, this.reservationClient);
    }

    public void exit(){
        System.out.println("Good Bye!");
        System.exit(0);
    }

}
