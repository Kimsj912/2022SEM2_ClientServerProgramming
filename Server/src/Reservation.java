public class Reservation{
    String studentId;
    String courseId;

    public Reservation(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }
    public String getCourseId() {
        return courseId;
    }

    @Override
    public String toString(){
        return "Reservation{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
