import Objects.Reservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationList{
    private final ArrayList<Reservation> reservationArrayList;
    public ReservationList(String name) throws IOException{
        reservationArrayList = new ArrayList<>();
        BufferedReader fileObj = new BufferedReader(new FileReader(name));
        while (fileObj.ready()) {
            String info = fileObj.readLine();
            if (!info.equals("")) {
                this.reservationArrayList.add(new Reservation(info));
            }
        }
        fileObj.close();
    }

    public boolean makeReservation(Reservation reservation){
        reservationArrayList.add(reservation);
        return true;
    }

    public boolean deleteReservationById(String courseId, String studentId){
        for(Reservation reservation: reservationArrayList){
            if(reservation.getCourseId().equals(courseId) && reservation.getStudentId().equals(studentId)){
                reservationArrayList.remove(reservation);
                return true;
            }
        }
        return false;
    }

    public Reservation getReservationByBothId(String studentId, String courseId){
        for (Reservation reservation : reservationArrayList) {
            if (reservation.getStudentId().equals(studentId) && reservation.getCourseId().equals(courseId)) {
                return reservation;
            }
        }
        return null;
    }

    public ArrayList<Reservation> getReservationsByStudentId(String studentId){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for(Reservation reservation : reservationArrayList){
            if(reservation.getStudentId().equals(studentId)){
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }

    public ArrayList<Reservation> getReservationsByCourseId(String courseId){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for(Reservation reservation : reservationArrayList){
            if(reservation.getCourseId().equals(courseId)){
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }



    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("ReservationList{\n");
        for(int i = 0; i < reservationArrayList.size(); i++){
            returnString.append("   ").append(reservationArrayList.get(i).toString()).append("\n");
        }
        return returnString + "}";
    }
}