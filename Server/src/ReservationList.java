import java.util.ArrayList;
import java.util.Arrays;

public class ReservationList{
    private ArrayList<Reservation> reservationArrayList;

    public ReservationList(){
        reservationArrayList = new ArrayList<>();
    }

    public boolean addReservation(Reservation reservation){
        reservationArrayList.add(reservation);
        return true;
    }

    public boolean deleteReservation(Reservation reservation){
        reservationArrayList.remove(reservation);
        return true;
    }

    public String[] getReservationListByStudentId(String studentId){
        String[] reservationList = new String[reservationArrayList.size()];
        for(int i = 0; i < reservationArrayList.size(); i++){
            if(reservationArrayList.get(i).getStudentId().equals(studentId)){
                reservationList[i] = reservationArrayList.get(i).getCourseId();
            }
        }
        return reservationList;
    }

    public boolean deleteReservationById(Reservation reservation){
        for(int i = 0; i < reservationArrayList.size(); i++){
            if(reservationArrayList.get(i).getStudentId().equals(reservation.getStudentId())
                    && reservationArrayList.get(i).getCourseId().equals(reservation.getCourseId())){
                reservationArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ReservationList{"+ Arrays.toString(reservationArrayList.toArray()) +'}';
    }
}
