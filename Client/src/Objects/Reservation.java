package Objects;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements IObject, Serializable {

    // Variables
    protected String reservationId;
    protected String courseId;
    protected String studentId;
    protected String reservationDate;


    // Getter & Setter
    public String getReservationId(){return reservationId;}
    public void setReservationId(String reservationId){this.reservationId = reservationId;}
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId){this.courseId = courseId;}
    public String getStudentId(){return studentId;}
    public void setStudentId(String studentId){this.studentId = studentId;}
    public String getReservationDate(){return reservationDate;}
    public void setReservationDate(String reservationDate){this.reservationDate = reservationDate;}

    // Constructor : Make a reservation
    public Reservation(String courseId, String studentId){
        this.reservationId = "R" + (new Date().getTime() + "").substring(5);
        this.courseId = courseId;
        this.studentId = studentId;
        this.reservationDate = new Date().toString();
    }

    // Constructor : For Decoding a reservation
    public Reservation(String line){
        String[] data = line.split(" ");
        this.reservationId = data[0];
        this.courseId = data[1];
        this.studentId = data[2];
        this.reservationDate = data[3];
    }

    // Method(toString) : For Encoding a reservation
    public String toString(){
        return this.reservationId + " " + this.courseId + " " + this.studentId + " " + this.reservationDate;
    }
}
