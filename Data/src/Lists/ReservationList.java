package Lists;

import Exceptions.AlreadyExsitException;
import Objects.Reservation;

import java.io.IOException;
import java.util.ArrayList;

public class ReservationList extends CommonList<Reservation> {

    public ReservationList(String fileName) throws IOException{
        super(fileName);
        while (objFile.ready()) {
            String info = objFile.readLine();
            if (!info.equals("")) {
                this.add(new Reservation(info));
            }
        }
        objFile.close();
    }

    public boolean makeReservation(Reservation reservation) throws AlreadyExsitException{
        for(Reservation res: this){
            if(res.getCourseId().equals(reservation.getCourseId()) && res.getStudentId().equals(reservation.getStudentId())){
                throw new AlreadyExsitException("new Reservation is rejected(Already exist)");
            }
        }
        return this.add(reservation);
    }

    public ArrayList<Reservation> getReservationListByStudentId(String studentId){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getStudentId().equals(studentId)){
                reservationList.add(this.get(i));
            }
        }
        return reservationList;
    }

    public ArrayList<Reservation> getReservationListByCourseId(String courseId){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getCourseId().equals(courseId)){
                reservationList.add(this.get(i));
            }
        }
        return reservationList;
    }

    public Reservation getReservationListByBothId(String courseId, String studentId){
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getCourseId().equals(courseId) && this.get(i).getStudentId().equals(studentId)){
                return this.get(i);
            }
        }
        return null;
    }

    public boolean deleteReservation(String courseId, String studentId){
        boolean result = false;
        for(Reservation reservation: this){
            if(reservation.getCourseId().equals(courseId) && reservation.getStudentId().equals(studentId)){
                result = this.remove(reservation);
                break;
            }
        }
        return result;
    }
}
