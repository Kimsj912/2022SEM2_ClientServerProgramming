package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements IObject, Serializable{
    // Variables
    protected String courseId;
    protected String profName;
    protected String courseName;
    protected ArrayList<String> preCourse;


    // Getter & Setter
    public String getCourseId(){return courseId;}
    public void setCourseId(String courseId){this.courseId = courseId;}
    public String getProfName() {return this.profName;}
    public void setProfName(String profName){this.profName = profName;}
    public String getCourseName(){return courseName;}
    public void setCourseName(String courseName){this.courseName = courseName;}
    public ArrayList<String> getPreCourse(){return preCourse;}
    public void setPreCourse(ArrayList<String> preCourse){this.preCourse = preCourse;}

    // Constructor
    public Course(String sCourseID, String sCourseName, String sProfName, ArrayList<String> sPreCourseArrList){
        this.courseId = sCourseID;
        this.profName = sProfName;
        this.courseName = sCourseName;
        this.preCourse = sPreCourseArrList;
    }

    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.courseId = stringTokenizer.nextToken();
        this.profName = stringTokenizer.nextToken();
        this.courseName = stringTokenizer.nextToken();
        this.preCourse = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            this.preCourse.add(stringTokenizer.nextToken());
        }
    }
    public boolean match(String courseId) {return this.courseId.equals(courseId);}
    public String toString() {
        String stringReturn = this.courseId + " " + this.profName + " " + this.courseName;
        for (int i = 0; i < this.preCourse.size(); i++) {
            stringReturn = stringReturn + " " + this.preCourse.get(i).toString();
        }
        return stringReturn;
    }
}
