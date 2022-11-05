package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Reservation implements Serializable {
    String studentId;
    String courseId;

    public Reservation(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Reservation(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.courseId = stringTokenizer.nextToken();
        this.studentId = stringTokenizer.nextToken();
    }

    public String getStudentId() {
        return studentId;
    }
    public String getCourseId() {
        return courseId;
    }

    public boolean match(String studentId, String courseId) {
        return this.studentId.equals(studentId) && this.courseId.equals(courseId);
    }

    @Override
    public String toString(){
        return "Objects.Reservation{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
