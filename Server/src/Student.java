
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student implements Serializable{
    // Variables
    protected String studentId;
    protected String name;
    protected String department;
    protected ArrayList<String> completedCoursesList;


    // Getter & Setter
    public String getStudentId(){return studentId;}
    public String getDepartment(){return department;}
    public String getName() {return this.name;}
    public ArrayList<String> getCompletedCourses() {return this.completedCoursesList;}
    public void setStudentId(String studentId){this.studentId = studentId;}
    public void setName(String name){this.name = name;}
    public void setDepartment(String department){this.department = department;}
    public void setCompletedCoursesList(ArrayList<String> completedCoursesList){this.completedCoursesList = completedCoursesList;}

    // Constructor
    public Student(String sStudentID, String name, String sStudentMajor, ArrayList<String> studentCompletedCourses){
        this.studentId = sStudentID;
        this.name = name;
        this.department = sStudentMajor;
        this.completedCoursesList = studentCompletedCourses;
    }

    public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.studentId = stringTokenizer.nextToken();
        this.name = stringTokenizer.nextToken();
        this.department = stringTokenizer.nextToken();
        this.completedCoursesList = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            this.completedCoursesList.add(stringTokenizer.nextToken());
        }
    }
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    public String toString() {
        String stringReturn = this.studentId + " " + this.name + " " + this.department;
        for (int i = 0; i < this.completedCoursesList.size(); i++) {
            stringReturn = stringReturn + " " + this.completedCoursesList.get(i).toString();
        }
        return stringReturn;
    }
}
