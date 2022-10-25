package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student implements IObject, Serializable{
    // Variables
    protected String studentId;
    protected String lastName;
    private String firstName;
    protected String major;
    protected ArrayList<String> completedCoursesList;


    // Getter & Setter
    public String getStudentId(){return studentId;}
    public String getLastName() {return this.lastName;}
    public String getFirstName(){return firstName;}
    public String getMajor(){return major;}
    public ArrayList<String> getCompletedCourses() {return this.completedCoursesList;}
    public void setStudentId(String studentId){this.studentId = studentId;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setMajor(String major){this.major = major;}
    public void setCompletedCoursesList(ArrayList<String> completedCoursesList){this.completedCoursesList = completedCoursesList;}

    // Constructor
    public Student(String sStudentID, String lastName,String firstName, String major, ArrayList<String> studentCompletedCourses){
        this.studentId = sStudentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.major = major;
        this.completedCoursesList = studentCompletedCourses;
    }

    public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.lastName = stringTokenizer.nextToken();
        this.firstName = stringTokenizer.nextToken();
    	this.major = stringTokenizer.nextToken();
    	this.completedCoursesList = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.completedCoursesList.add(stringTokenizer.nextToken());
    	}
    }
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    public String toString() {
        StringBuilder stringReturn = new StringBuilder(this.studentId + " " + this.lastName + " " + this.firstName+ " " + this.major);
        for (String s : this.completedCoursesList) {
            stringReturn.append(" ").append(s.toString());
        }
        return stringReturn.toString();
    }

}
