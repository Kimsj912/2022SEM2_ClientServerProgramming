package Menus.Reservation;

import Menus.IMenuInterface;

public enum MSelectReservationMenu implements IMenuInterface {
    GET_RESERVATION("getReservationList", "get reservation with variable ways"),
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
    MSelectReservationMenu(String method, String description){
        this.method = method;
        this.description = description;
    }
}
