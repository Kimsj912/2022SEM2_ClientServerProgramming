package Objects;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements IObject, Serializable {

    // Variables
    protected String courseId;
    protected String studentId;
    protected String reservationDate;


    // Getter & Setter
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId){this.courseId = courseId;}
    public String getStudentId(){return studentId;}
    public void setStudentId(String studentId){this.studentId = studentId;}
    public String getReservationDate(){return reservationDate;}
    public void setReservationDate(String reservationDate){this.reservationDate = reservationDate;}

    // Constructor : Make a reservation
    public Reservation(String courseId, String studentId){
        this.courseId = courseId;
        this.studentId = studentId;
        this.reservationDate = new Date().toInstant().toString();
    }

    // Constructor : For Decoding a reservation
    public Reservation(String line){
        String[] data = line.split(" ");
        this.courseId = data[0];
        this.studentId = data[1];
        this.reservationDate = data[2];
    }

    // Method(toString) : For Encoding a reservation
    public String toString(){
        return this.courseId + " " + this.studentId + " " + this.reservationDate;
    }
}
