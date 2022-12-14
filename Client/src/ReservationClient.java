import Exceptions.AlreadyExistException;
import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import MenuScripts.EReservation;
import Menus.Reservation.MGetReservationMenu;
import Objects.Reservation;
import Utils.Input.InputCourseValue;
import Utils.Input.InputStudentValue;
import Utils.Print.Printer;

import java.io.IOException;
import java.util.ArrayList;

// Validator 까지는 에러만 던지지 trycatch를 하지 않는다.
public class ReservationClient extends CommonClient{
    private final CSReservationIF server;
    private final InputCourseValue inputCourseValue;
    private final InputStudentValue inputStudentValue;

    public ReservationClient(CSReservationIF server){
        this.server = server;
        this.inputCourseValue = new InputCourseValue();
        this.inputStudentValue = new InputStudentValue();
    }

    public void getReservationList() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(MGetReservationMenu.class, "Reservation Menu", this.getClass(), this);
    }

    public void getReservationsByStudentId() throws IOException, ServiceTerminateException{
        try{
            String studentId = inputStudentValue.inputStudentIdWithValidation();
            ArrayList<Reservation> reservationList = server.getReservationsByStudentId(studentId);
            Printer.printList(reservationList, Reservation.class);
        } catch (NullDataException e){
            System.out.println(EReservation.RESERVATION_NOT_EXIST_BY_STUDENT_ID.getMessage());
        } catch (EmptyInputException ignored){}
    }

    public void getReservationsByCourseId() throws IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            ArrayList<Reservation> reservationList = server.getReservationsByCourseId(courseId);
            Printer.printList(reservationList, Reservation.class);
        } catch (NullDataException e){
            System.out.println(EReservation.RESERVATION_NOT_EXIST_BY_COURSE_ID.getMessage());
        } catch (EmptyInputException ignored){}
    }

    public void getReservationByBothId() throws IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            String studentId = inputStudentValue.inputStudentIdWithValidation();
            Reservation reservation = server.getReservationByBothId(courseId, studentId);
            Printer.printItem(reservation);
        }catch (NullDataException e){
            System.out.println(EReservation.RESERVATION_NOT_EXIST.getMessage());
        } catch (EmptyInputException ignored){}
    }

    public void makeReservation() throws IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            String studentId = inputStudentValue.inputStudentIdWithValidation();
            if(server.makeReservation(courseId, studentId)){
                System.out.println(EReservation.ADD_SUCCESS.getMessage());
            } else System.out.println(EReservation.ADD_FAIL.getMessage());
        } catch (NullDataException | AlreadyExistException e){
            System.out.println(e.getMessage());
        } catch (EmptyInputException ignored){}
    }

    public void deleteReservation() throws IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            String studentId =  inputStudentValue.inputStudentIdWithValidation();
            if(server.deleteReservation(courseId,studentId)){
                System.out.println(EReservation.DELETE_SUCCESS.getMessage());
            } else System.out.println(EReservation.DELETE_FAIL.getMessage());
        } catch (NullDataException e){
            System.out.println(e.getMessage());
        } catch (EmptyInputException ignored){}
    }
}
