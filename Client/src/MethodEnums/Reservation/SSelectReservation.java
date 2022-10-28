package MethodEnums.Reservation;

import MethodEnums.IMenuInterface;

public enum SSelectReservation implements IMenuInterface {
    GET_RESERVATION("getReservation", "get reservation with variable ways"),
    MAKE_RESERVATION("makeReservation", "add course reservation"),
    DELETE_RESERVATION("deleteReservation", "delete course reservation");

    // Variables
    private String method;
    private String description;

    // Getters & Setters
    public String getMethod(){return method;}
    public String getDescription(){return description;}
    public void setMethod(String method){this.method = method;}
    public void setDescription(String description){this.description = description;}

    // Constructor
    SSelectReservation(String method, String description){
        this.method = method;
        this.description = description;
    }
}
