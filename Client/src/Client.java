import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import MethodEnums.EMainMenu;
import MethodEnums.Course.SSelectCourse;
import MethodEnums.Reservation.SSelectReservation;
import MethodEnums.Student.SSelectStudent;
import Utils.Input.InputValue;
import Utils.Print.Printer;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client extends CommonClient {
    private static final String serverName = "Server";
    protected static ServerIF server;

    private String clientId;
    private final StudentClient studentClient;
    private final CourseClient courseClient;
    private final ReservationClient reservationClient;

    public static void main(String[] args) throws IOException, NotBoundException, ServiceTerminateException, EmptyInputException{
        Client client = new Client();
        client.run();
    }

    public Client () throws IOException, NotBoundException, ServiceTerminateException, EmptyInputException{
        // Login Token °úÁ¤
        try{
            server = (ServerIF) Naming.lookup(serverName);
            clientId = InputValue.getInputString("Enter username: ", false);
            server.addConnection(clientId);
        } catch (RemoteException e){
            System.out.println("Server is not running");
            System.exit(0);
        }

        this.studentClient = new StudentClient(server);
        this.courseClient = new CourseClient(server);
        this.reservationClient = new ReservationClient(server);
    }

    public void run() throws IOException, ServiceTerminateException, EmptyInputException{
        while(true) {
            String method = Printer.selectMenu(EMainMenu.class, "Main Menu");
            if(method == null) return;
            invokeMethod(this.getClass(), method, this);
        }
    }

    // ------------------ Main Menu ------------------
    public void selectCourse() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SSelectCourse.class, "Select Course Menu", CourseClient.class, this.courseClient);
    }

    public void selectStudent() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SSelectStudent.class, "Select Student Menu", StudentClient.class, this.studentClient);
    }

    public void selectReservation() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SSelectReservation.class, "Select Reservation Menu", ReservationClient.class, this.reservationClient);
    }

    public void exit() throws RemoteException{
        if (server.deleteConnection(clientId)) {
            System.out.println("Good Bye!");
            System.exit(0);
        } else {
            System.out.println("Error: Connection is not deleted");
        }
    }

}
