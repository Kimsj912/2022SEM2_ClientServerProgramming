package Objects;

import java.io.Serializable;

public class Reservation implements IObject, Serializable {

    // Variables
    protected String reservationId;
    protected String courseId;
    protected String studentId;
    protected String reservationDate;
    protected boolean isPreReservation;
    protected boolean isReservation;


    // Getter & Setter
    public String getReservationId(){return reservationId;}
    public void setReservationId(String reservationId){this.reservationId = reservationId;}
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId){this.courseId = courseId;}
    public String getStudentId(){return studentId;}
    public void setStudentId(String studentId){this.studentId = studentId;}
    public String getReservationDate(){return reservationDate;}
    public void setReservationDate(String reservationDate){this.reservationDate = reservationDate;}
    public boolean isPreReservation(){return isPreReservation;}
    public void setPreReservation(boolean preReservation){isPreReservation = preReservation;}
    public boolean isReservation(){return isReservation;}
    public void setReservation(boolean reservation){isReservation = reservation;}

    // Constructor : Make a reservation
    public Reservation(String courseId, String studentId){
        this.courseId = courseId;
        this.studentId = studentId;
        this.reservationDate = null;
        this.isPreReservation = false;
        this.isReservation = false;
    }

    // Constructor : For Decoding a reservation
    public Reservation(String line){
        String[] data = line.split(" ");
        this.reservationId = data[0];
        this.courseId = data[1];
        this.studentId = data[2];
        this.reservationDate = data[3];
        this.isPreReservation = Boolean.parseBoolean(data[4]);
        this.isReservation = Boolean.parseBoolean(data[5]);
    }

    // Method(toString) : For Encoding a reservation
    public String toString(){
        return this.reservationId + " " + this.courseId + " " + this.studentId + " " + this.reservationDate + " " + this.isPreReservation + " " + this.isReservation;
    }
}
