import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import Interfaces.ReservationIF;
import Interfaces.ServerIF;
import MenuScripts.ECourse;
import MenuScripts.EReservation;
import MenuScripts.EStudent;
import MethodEnums.Student.SGetStudentMenu;
import Objects.Reservation;
import Utils.Input.InputValue;
import Utils.Print.Printer;
import Utils.Validator.CourseValidator;
import Utils.Validator.StudentValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ReservationClient extends CommonClient{
    private final ReservationIF server;
    public ReservationClient(ServerIF server){
        this.server = server;
    }

    public void getReservationList() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SGetStudentMenu.class, "Reservation Menu", this.getClass(), this);
    }
`
    public void getReservationByStudentId() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = getStudentIdWithValidation();
        ArrayList<Reservation> reservationList = server.getReservationsByStudentId(studentId);
        Printer.printList(reservationList, Reservation.class);
    }

    public void getReservationByCourseId() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        ArrayList<Reservation> reservationList = server.getReservationsByCourseId(courseId);
        Printer.printList(reservationList, Reservation.class);
    }

    public void getReservationByBothId() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        String studentId = getStudentIdWithValidation();
        Reservation reservation = server.getReservationByBothId(courseId, studentId);
        Printer.printItem(reservation);
    }

    public void makeReservation() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        String studentId = getStudentIdWithValidation();
        if(server.makeReservation(courseId, studentId))System.out.println(EReservation.ADD_FAIL.getMessage());
        else System.out.println(EReservation.ADD_FAIL.getMessage());
    }

    public void deleteReservation() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        String studentId = getStudentIdWithValidation();
        if(server.deleteReservation(courseId,studentId)){
            System.out.println(EReservation.DELETE_FAIL.getMessage());
        } else {
            System.out.println(EReservation.DELETE_FAIL.getMessage());
        }
    }


    // Validation Methods
    private String getCourseIdWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        String courseId = InputValue.getInputString(ECourse.ENTER_COURSE_ID.getMessage(), false);
        if(!CourseValidator.checkValidCourseId(courseId))
            throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
        return courseId;
    }
    public String getStudentIdWithValidation() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = InputValue.getInputString(EStudent.ENTER_STUDENT_ID.getMessage(), false);
        if(!StudentValidator.checkValidStudentId(studentId))
            throw new InvalidDataException(EStudent.INVALID_STUDENT_ID.getMessage());
        return studentId;
    }

}
