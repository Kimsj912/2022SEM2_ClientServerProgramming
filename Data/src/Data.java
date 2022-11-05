import Exceptions.NullDataException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import Lists.CourseList;
import Lists.ReservationList;
import Lists.StudentList;
import Objects.*;

public class Data extends UnicastRemoteObject implements DataIF {
    protected StudentList studentList;
    protected CourseList courseList;
    private ReservationList reservationList;
    private final Set<String> connectionServerSet; // connection server set

    protected Data() throws IOException{
        super();
        connectionServerSet = new HashSet<>();
        studentList = new StudentList("Students.txt");
        courseList = new CourseList("Courses.txt");
        reservationList = new ReservationList("Reservations.txt");
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try{
            Data data = new Data();
            Naming.rebind("Data", data);
            System.out.println("Database is Ready");
        } catch (ConnectException e){
            System.out.println("[Error] Connection Error Occurred");
        } catch (MalformedURLException e){
            System.out.println("[Error] Malformed URL");
        } catch (RemoteException e){
            e.detail.printStackTrace();
            System.out.println("[Error] System is not ready.");
        } catch (IOException e) {
            System.out.println("[Error] File is not found.");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("[Error] Unknown Error Occurred");
        }
    }

    @Override
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        ArrayList<Student> studentArrayList = studentList.getAllStudentRecords();
        if(studentArrayList == null) throw new NullDataException("Student data isn't initialized.");
        return studentArrayList;
    }
    @Override
    public Student getStudentById(String id) throws RemoteException{
        System.out.println("Get Student requested.(CourseId : " + id + ")");
        Student student  = studentList.getStudentById(id);
        System.out.println((student == null)?("Student is not found.\n>>" + id) : ("Student is found.\n>>" + student));
        return student;
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException{
        System.out.println("Get Student requested.(CourseName : " + name+")");
        ArrayList<Student> studentArrayList = studentList.getStudentsByName(name);
        System.out.println((studentArrayList.size() == 0)?("Student is not found.\n>>" + name) : ("Student is found.\n>>" + studentArrayList));
        return studentArrayList;
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        System.out.println("Get Student requested.(CourseMajor : " + major+")");
        ArrayList<Student> studentArrayList = studentList.getStudentsByMajor(major);
        System.out.println((studentArrayList.isEmpty())?("Student is not found.\n>>" + major) : ("Student is found.\n>>" + studentArrayList));
        return studentArrayList;
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        if(getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        System.out.println("Get Student requested.(CompletedCourseId : " + courseId+")");
        ArrayList<Student> studentArrayList = studentList.getStudentsByCompletedCourse(courseId);
        System.out.println((studentArrayList.isEmpty())?("Student is not found.\n>>" + courseId) : ("Student is found.\n>>" + studentArrayList));
        return studentArrayList;
    }


    @Override
    public boolean addStudent(Student student) throws RemoteException{
        System.out.println("Add Student requested.(Student : " + student + ")");
        boolean result = studentList.addStudent(student);
        System.out.println((result)?("Student is added.\n>>" + student) : ("Student is not added.\n>>" + student));
        return result;
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        System.out.println("Delete Student requested.(StudentId : " + studentId + ")");
        boolean result = studentList.deleteStudentById(studentId);
        System.out.println((result)?("Student is deleted.\n>>" + studentId) : ("Student is not deleted.\n>>" + studentId));
        return result;
    }

    @Override
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException{
        System.out.println("Update Student requested.(StudentId : " + studentId + " -> " + newStudent + ")");
        boolean result = studentList.updateStudentById(studentId, newStudent);
        System.out.println((result)?("Student is updated.\n>>" + studentId + " -> " + newStudent) : ("Student is not updated.\n>>" + studentId + " -> " + newStudent));
        return result;
    }

    // Course
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all courses data");
        ArrayList<Course> courseArrayList = courseList.getAllCoursesRecords();
        if(courseArrayList == null) throw new NullDataException("Course data isn't initialized.");
        return courseArrayList;
    }

    @Override
    public Course getCourseById(String courseId) throws RemoteException{
        System.out.println("Get Course requested.(CourseId : " + courseId+")");
        Course course = courseList.getCourseById(courseId);
        System.out.println((course == null)?("Course is not found.\n>>" + courseId) : ("Course is found.\n>>" + course));
        return course;
    }

    @Override
    public ArrayList<Course> getCoursesByName(String name) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(CourseName : " + name+")");
        ArrayList<Course> courseArrayList = courseList.getCourseByCourseName(name);
        System.out.println((courseArrayList.isEmpty())?("Course is not found.\n>>" + name) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String professor) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(Professor : " + professor+")");
        ArrayList<Course> courseArrayList = courseList.getCourseByProfessor(professor);
        System.out.println((courseArrayList.isEmpty())?("Course is not found.\n>>" + professor) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourseId(String preCourseId) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(PreCourseId : " + preCourseId+")");
        ArrayList<Course> courseArrayList = courseList.getCoursesByPreCourseId(preCourseId);
        System.out.println((courseArrayList.isEmpty())?("Course is not found.\n>>" + preCourseId) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public ArrayList<Course> getCoursesBySemester(String semester) throws RemoteException, NullDataException{
        System.out.println("Get Course requested.(Semester : " + semester+")");
        ArrayList<Course> courseArrayList = courseList.getCourseBySemester(semester);
        System.out.println((courseArrayList.isEmpty())?("Course is not found.\n>>" + semester) : ("Courses are found.\n>>" + courseArrayList));
        return courseArrayList;
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        System.out.println("Add Course requested. (Course : " + course+")");
        boolean result = courseList.addCourse(course);
        System.out.println((result)?("Success to add Course \n>>" + course) : ("Failed to add Course\n>>" + course));
        return courseList.addCourse(course);
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        System.out.println("Delete Course requested. (CourseId : " + courseId+")");
        boolean result = courseList.deleteCourseById(courseId);
        System.out.println((result)?("Success to delete Course \n>>" + courseId) : ("Failed to delete Course\n>>" + courseId));
        return result;
    }

    @Override
    public boolean updateCourseById(String courseId, Course newCourse) throws RemoteException{
        System.out.println("Update Course requested. (CourseId : " + courseId+" -> " + newCourse+")");
        boolean result = courseList.updateCourseById(courseId, newCourse);
        System.out.println((result)?("Success to update Course \n>>" + courseId + " -> " + newCourse) : ("Failed to update Course\n>>" + courseId + " -> " + newCourse));
        return result;
    }

    // Reservation
    @Override
    public ArrayList<Reservation> getReservationsByStudentId(String studentId) throws RemoteException, NullDataException{
        return reservationList.getReservationListByStudentId(studentId);
    }

    @Override
    public ArrayList<Reservation> getReservationsByCourseId(String courseId) throws RemoteException, NullDataException{
        return reservationList.getReservationListByCourseId(courseId);
    }

    @Override
    public Reservation getReservationByBothId(String courseId, String studentId) throws RemoteException, NullDataException{
        return reservationList.getReservationListByBothId(courseId, studentId);
    }
    @Override
    public boolean makeReservation(String studentId, String courseId) throws RemoteException{
        Reservation reservation = new Reservation(studentId, courseId);
        return reservationList.makeReservation(reservation);

    }

    @Override
    public boolean deleteReservation(String courseId, String studentId){
        return reservationList.deleteReservation(courseId, studentId);
    }

    @Override
    public boolean addDataConnection(String id) throws RemoteException{
        if(connectionServerSet.contains(id)){
            System.out.println("The server is already connected.\n>>"+id);
            return false;
        }
        connectionServerSet.add(id);
        System.out.println("New Server Connected.\n>>" + id);
        return true;
    }

    @Override
    public boolean deleteDataConnection(String id) throws RemoteException{
        if(connectionServerSet.contains(id)){
            connectionServerSet.remove(id);
            System.out.println("Server connection terminated.\n>>" + id);
            return true;
        }
        System.out.println("Failed: Anonymous server disconnection requested.\n>>" + id);
        return false;
    }


}

