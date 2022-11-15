package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student implements IObject, Serializable{
    // Variables
    protected String studentId;
    protected String name;
    protected String major;
    protected ArrayList<String> completedCourses;


    // Getter & Setter
    public String getStudentId(){return studentId;}
    public String getName() {return this.name;}
    public String getMajor(){return major;}
    public ArrayList<String> getCompletedCourses() {return this.completedCourses;}
    public void setStudentId(String studentId){this.studentId = studentId;}
    public void setName(String name){this.name = name;}
    public void setMajor(String major){this.major = major;}
    public void setCompletedCourses(ArrayList<String> completedCourses){this.completedCourses = completedCourses;}

    // Constructor : Make a student
    public Student(String sStudentID, String name, String major){
        this.studentId = sStudentID;
        this.name = name;
        this.major = major;
        this.completedCourses = new ArrayList<>();
    }

    // Constructor : For Decoding a student
    public Student(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line);
    	this.studentId = stringTokenizer.nextToken();
    	this.name = stringTokenizer.nextToken() + " " + stringTokenizer.nextToken();
    	this.major = stringTokenizer.nextToken();
    	this.completedCourses = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.completedCourses.add(stringTokenizer.nextToken());
    	}
    }

    // Method(toString) : For Encoding a student
    public String toString() {
        StringBuilder stringReturn = new StringBuilder(this.studentId + " " + this.name + " " + " " + this.major);
        this.completedCourses.sort(String::compareTo);
        for (String s : this.completedCourses) {
            stringReturn.append(" ").append(s.toString());
        }
        return stringReturn.toString();
    }

}
