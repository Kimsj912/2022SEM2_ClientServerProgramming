package Objects;

import Exceptions.EmptyInputException;
import MenuScripts.ECourse;
import Utils.Validator.CourseValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Course implements IObject, Serializable{

    // Variables
    protected String courseId;
    protected String professor;
    protected String name;
    protected String semester;
    protected ArrayList<String> preCourse;

    // Getter & Setter
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId) throws InvalidDataException, EmptyInputException{
        this.courseId = CourseValidator.checkValidCourseId(courseId);
    }
    public String getProfessor(){return professor;}
    public void setProfessor(String professor) throws InvalidDataException, EmptyInputException{
        this.professor = CourseValidator.checkValidCourseProfessor(professor);
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getSemester(){return semester;}
    public void setSemester(String semester) throws InvalidDataException, EmptyInputException{
        this.semester = CourseValidator.checkValidCourseSemester(semester.toUpperCase());
    }
    public ArrayList<String> getPreCourse(){return preCourse;}
    public void setPreCourse(ArrayList<String> preCourse){this.preCourse = preCourse;}

    // Constructor : Make a course
    public Course(String courseId, String professor, String name,  String semester,  ArrayList<String> preCourses){
        this.courseId = courseId;
        this.professor = professor;
        this.name = name;
        this.semester = semester;
        this.preCourse = preCourses;
    }

    // Constructor : For Decoding a course
    public Course(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        this.courseId = stringTokenizer.nextToken();
        this.professor = stringTokenizer.nextToken();
        this.name = stringTokenizer.nextToken();
        this.semester = stringTokenizer.nextToken();
        this.preCourse = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            this.preCourse.add(stringTokenizer.nextToken());
        }
    }

    // Method(toString) : For Encoding a course
    public String toString() {
        StringBuilder stringReturn = new StringBuilder(
            this.courseId + " " + this.professor + " " + this.name+" "+this.semester
        );
        this.preCourse.sort(String::compareTo);
        for (String s : this.preCourse) stringReturn.append(" ").append(s);
        return stringReturn.toString();
    }
}
