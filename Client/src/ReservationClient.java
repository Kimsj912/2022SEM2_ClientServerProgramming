import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import ServerClientIF.ReservationIF;
import MenuScripts.ECourse;
import MenuScripts.EReservation;
import MenuScripts.EStudent;
import MethodEnums.Reservation.SGetReservationMenu;
import Objects.Reservation;
import Utils.Input.InputValue;
import Utils.Print.Printer;
import Utils.Validator.CourseValidator;
import Utils.Validator.StudentValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.util.ArrayList;

// Validator 까지는 에러만 던지지 trycatch를 하지 않는다.
public class ReservationClient extends CommonClient{
    private final ReservationIF server;
    public ReservationClient(ServerIF server){
        this.server = server;
    }

    public void getReservationList() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SGetReservationMenu.class, "Reservation Menu", this.getClass(), this);
    }

    public void getReservationsByStudentId() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = getStudentIdWithValidation();
        ArrayList<Reservation> reservationList = server.getReservationsByStudentId(studentId);
        Printer.printList(reservationList, Reservation.class);
    }

    public void getReservationsByCourseId() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
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
        try{
            String courseId = InputValue.getInputString(ECourse.ENTER_COURSE_ID.getMessage(), false);
            return CourseValidator.checkValidCourseId(courseId);
        } catch (InvalidDataException | EmptyInputException e){
            System.out.println(e.getMessage());
            return getCourseIdWithValidation();
        }
    }
    public String getStudentIdWithValidation() throws ServiceTerminateException, IOException{
        try{
            String studentId = InputValue.getInputString(EStudent.ENTER_STUDENT_ID.getMessage(), false);
            StudentValidator.checkValidStudentId(studentId);
            return studentId;
        } catch (InvalidDataException | EmptyInputException e){
            System.out.println(e.getMessage());
            return getStudentIdWithValidation();
        }
    }

}
