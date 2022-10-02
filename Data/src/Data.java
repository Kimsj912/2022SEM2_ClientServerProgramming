import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;



public class Data extends UnicastRemoteObject implements DataIF {
    protected static StudentList studentList;
    protected static CourseList courseList;
    private Set<String> set;

    protected Data() throws RemoteException{
        super();
        set = new HashSet<>();
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try{
            Data data = new Data();
            Naming.rebind("Data", data);

            studentList = new StudentList("Students.txt");
            courseList = new CourseList("Courses.txt");

            System.out.println("Database is Ready");

        } catch (ConnectException e){
            System.out.println("Server is already running.");
            System.out.println("Problem >> "+  e.detail.toString());
            System.exit(0);
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
            System.out.println("Data store is not ready.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("File is not found.");
            System.exit(0);
        }
    }

    @Override
    public Student getStudent(String id) throws RemoteException{
        System.out.println("Student requested.\n>>" + id);
        return studentList.getStudentRecord(id);
    }


    public ArrayList<Student> getAllStudentData() throws RemoteException{
        System.out.println("returning all student data");
        return studentList.getAllStudentRecords();
    }

    @Override
    public void addStudent(Student student) throws RemoteException{
        studentList.addStudentRecord(student);
        System.out.println("Student added.\n>>" + student);
    }

    @Override
    public void deleteStudent(String student) throws RemoteException{
        studentList.deleteStudentRecord(student);
        System.out.println("Student deleted.\n>>" + student);
    }


    @Override
    public Course getCourse(String id) throws RemoteException{
        System.out.println("Course requested.\n>>" + id);
        return courseList.getCourseRecord(id);
    }

    @Override
    public void addDataConnection(String id) throws RemoteException{
        set.add(id);
        System.out.println("Data connection added.\n>>" + id);
    }

    @Override
    public void deleteDataConnection(String id) throws RemoteException{
        set.remove(id);
        System.out.println("Data connection deleted.\n>>" + id);
    }

    public ArrayList<Course> getAllCoursesData() throws RemoteException{
        System.out.println("returning all courses data");
        return courseList.getAllCoursesRecords();
    }

    @Override
    public void addCourse(Course course) throws RemoteException{
        courseList.addCourseRecord(course);
        System.out.println("Course added.\n>>" + course);
    }

    @Override
    public void deleteCourse(String student) throws RemoteException{
        courseList.deleteCourseRecord(student);
        System.out.println("Course deleted.\n>>" + student);
    }


}

