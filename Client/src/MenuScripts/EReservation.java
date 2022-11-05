package MenuScripts;

public enum EReservation {
    // Situation (CRUD)
    ADD_SUCCESS("Completed to make a reservation."),
    ADD_FAIL("Failed to add a course."),
    DELETE_SUCCESS("Completed to delete a reservation."),
    DELETE_FAIL("Failed to delete a course."),

    // Error
    RESERVATION_NOT_EXIST_BY_STUDENT_ID("There is no reservation by student id."),
    RESERVATION_NOT_EXIST_BY_COURSE_ID("There is no reservation by course id."),
    RESERVATION_NOT_EXIST("This reservation does not exist.");
    private final String message;
    public String getMessage(){return message;}
    EReservation(String message){
        this.message = message;
    }
}