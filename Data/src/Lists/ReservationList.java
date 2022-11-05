package Lists;

import Objects.Reservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationList extends ArrayList<Reservation> {

    public ReservationList(String name) throws IOException{
        BufferedReader fileObj = new BufferedReader(new FileReader(name));
        while (fileObj.ready()) {
            String info = fileObj.readLine();
            if (!info.equals("")) {
                this.add(new Reservation(info));
            }
        }
        fileObj.close();

    }

    public boolean makeReservation(Reservation reservation){
        for(Reservation res: this){
            if(res.getCourseId().equals(reservation.getCourseId()) && res.getStudentId().equals(reservation.getStudentId())){
                System.out.println("new Reservation is rejected(Already exist)");
                return false;
            }
        }
        System.out.println("new reservation added (courseId: " + reservation.getCourseId() + ", studentId: " + reservation.getStudentId() + ")");
        this.add(reservation);
        return true;
    }

    public ArrayList<Reservation> getReservationListByStudentId(String studentId){
        System.out.println("get Reservation is requested.(studentId: " + studentId +")");
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getStudentId().equals(studentId)){
                System.out.println("Reservation is found.(courseId: " + this.get(i).getCourseId() + ", studentId: " + this.get(i).getStudentId() + ")");
                reservationList.add(this.get(i));
            }
        }
        return reservationList;
    }

    public ArrayList<Reservation> getReservationListByCourseId(String courseId){
        System.out.println("get Reservation is requested.(courseId: " + courseId +")");
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getCourseId().equals(courseId)){
                System.out.println("Reservation is found.(courseId: " + this.get(i).getCourseId() + ", studentId: " + this.get(i).getStudentId() + ")");
                reservationList.add(this.get(i));
            }
        }
        return reservationList;
    }

    public Reservation getReservationListByBothId(String courseId, String studentId){
        System.out.println("get Reservation is requested.(courseId: " + courseId + " studentId: " + studentId+")");
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getCourseId().equals(courseId) && this.get(i).getStudentId().equals(studentId)){
                System.out.println("Reservation is found.(courseId: " + this.get(i).getCourseId() + ", studentId: " + this.get(i).getStudentId() + ")");
                return this.get(i);
            }
        }
        return null;
    }

    public boolean deleteReservation(String courseId, String studentId){
        for(Reservation reservation: this){
            if(reservation.getCourseId().equals(courseId) && reservation.getStudentId().equals(studentId)){
                this.remove(reservation);
                System.out.println("reservation deleted (courseId: " + courseId + ", studentId: " + studentId + ")");
                return true;
            }
        }
        System.out.println("reservation not found (courseId: " + courseId + ", studentId: " + studentId + ")");
        return false;
    }
}
