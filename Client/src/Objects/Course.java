package Objects;

import Exceptions.EmptyInputException;
import MenuScripts.ECourse;
import Utils.Validator.CourseValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Course implements IObject, Serializable{

    // Variables
    protected String courseId;
    protected String professor;
    protected String name;
    protected String semester;
    protected int maxCapacity;
    protected int currentStudents;
    protected ArrayList<String> preCourse;


    // Getter & Setter
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId) throws InvalidDataException, EmptyInputException{
        if(!CourseValidator.checkValidCourseId(courseId)) throw new InvalidDataException();
        this.courseId = courseId;}
    public String getProfessor(){return professor;}
    public void setProfessor(String professor) throws InvalidDataException, EmptyInputException{
        if(!CourseValidator.checkValidCourseProfessor(professor)) throw new InvalidDataException();
        this.professor = professor;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getSemester(){return semester;}
    public void setSemester(String semester) throws InvalidDataException{
        if(!CourseValidator.checkValidCourseSemester(semester.toUpperCase())) throw new InvalidDataException();
        this.semester = semester.toUpperCase();
    }
    public void setMaxCapacity(int maxCapacity) throws InvalidDataException{
        if(!CourseValidator.checkValidMaxCapacity(maxCapacity)) throw new InvalidDataException();
        this.maxCapacity = maxCapacity;
    }
    public int getMaxCapacity(){return maxCapacity;}
    public int getCurrentStudents(){return currentStudents;}
    public ArrayList<String> getPreCourse(){return preCourse;}
    public void setPreCourse(ArrayList<String> preCourse){this.preCourse = preCourse;}
    public void setPreCourse(String preCourseLine) throws InvalidDataException, EmptyInputException{
        ArrayList<String> preCourseList = new ArrayList<String>(Arrays.asList(preCourseLine.split(",")));
        for(String preCourse : preCourseList){
            if(!CourseValidator.checkValidCourseId(preCourse))
                throw new InvalidDataException(ECourse.INVALID_PRE_COURSE.getMessage());
        }
        this.preCourse = preCourseList;
    }


    // Constructor : Make a course
    public Course(String courseId, String professor, String name,  String semester, int maxCapacity,  ArrayList<String> preCourses){
        this.courseId = courseId;
        this.professor = professor;
        this.name = name;
        this.semester = semester;
        this.maxCapacity = maxCapacity;
        this.currentStudents = 0;
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
        StringBuilder stringReturn = new StringBuilder(this.courseId + " " + this.professor + " " + this.name);
        this.preCourse.sort(String::compareTo);
        for (String s : this.preCourse) {
            stringReturn.append(" ").append(s.toString());
        }
        return stringReturn.toString();
    }
}
