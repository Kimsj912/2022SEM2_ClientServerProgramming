
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable{
    // Variables
    protected String courseId;
    protected String profName;
    protected String lectName;
    protected ArrayList<String> preCourse;


    // Getter & Setter
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId){this.courseId = courseId;}
    public String getProfName() {return this.profName;}
    public void setProfName(String profName){this.profName = profName;}
    public String getLectName(){return lectName;}
    public void setLectName(String lectName){this.lectName = lectName;}
    public ArrayList<String> getPreCourse(){return preCourse;}
    public void setPreCourse(ArrayList<String> preCourse){this.preCourse = preCourse;}

    // Constructor
    public Course(String sCourseID, String sCourseName, String sProfName, ArrayList<String> sPreCourseArrList){
        this.courseId = sCourseID;
        this.profName = sProfName;
        this.lectName = sCourseName;
        this.preCourse = sPreCourseArrList;
    }
    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.courseId = stringTokenizer.nextToken();
    	this.profName = stringTokenizer.nextToken();
    	this.lectName = stringTokenizer.nextToken();
    	this.preCourse = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.preCourse.add(stringTokenizer.nextToken());
    	}
    }
    public boolean match(String courseId) {return this.courseId.equals(courseId);}
    public ArrayList<String> getCompletedCourses() {
        return this.preCourse;
    }
    public String toString() {
        String stringReturn = this.courseId + " " + this.profName + " " + this.lectName;
        for (int i = 0; i < this.preCourse.size(); i++) {
            stringReturn = stringReturn + " " + this.preCourse.get(i).toString();
        }
        return stringReturn;
    }
}
