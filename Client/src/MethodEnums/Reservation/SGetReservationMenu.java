package MethodEnums.Reservation;

import MethodEnums.IMenuInterface;

public enum SGetReservationMenu implements IMenuInterface {
    GET_RESERVATIONS_BY_STUDENT_ID("getReservationsByStudentId", "search reservation by student Id"),
    GET_RESERVATIONS_BY_COURSE_ID("getReservationsByCourseId", "search reservation by course Id"),
    GET_RESERVATION_BY_BOTH_ID("getReservationByBothId", "search reservation by student Id and course Id");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    SGetReservationMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
