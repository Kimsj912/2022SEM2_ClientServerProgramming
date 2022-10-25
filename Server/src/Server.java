import Exceptions.NullDataException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Server extends UnicastRemoteObject implements ServerIF {

    private static final String serverId = "server2345";
    private static DataIF data;
    private static HashSet<String> clientList;

    private ArrayList<Course> courseArrayList;
    private ArrayList<Student> studentArrayList;

    private boolean courseChanged = false;
    private boolean studentChanged = false;

    protected Server() throws RemoteException{
        super();
        clientList = new HashSet<>();
        this.courseArrayList = new ArrayList<>();
//        this.studentChanged = new ArrayList<>();
        this.courseChanged = false;
        this.studentChanged = false;
    }

    public static void main(String[] args){
        // RMI로 멀티프로세스가 되도록 짜야함.
        try {
            data = (DataIF) Naming.lookup("Data");
            data.addDataConnection(serverId);

            Server server = new Server();
            Naming.rebind("Server", server);

            System.out.println("Server is Ready");
        } catch (Exception  e) {
            System.out.println("Server is not ready.");
            System.exit(0);
        }
    }
    // Students
    @Override
    public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        ArrayList<Student> studentArrayList = data.getAllStudentData();
        if(studentArrayList == null) throw new NullDataException("Student data isn't initialized.");
        return studentArrayList;
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        System.out.println("Student added.\n>> " + student);
        return data.addStudent(student);
    }

    @Override
    public Student getStudentById(String id) throws RemoteException, NullDataException{
        return data.getStudentById(id);
    }

    @Override
    public ArrayList<Student> getStudentsByLastName(String lastName) throws RemoteException, NullDataException{
        ArrayList<Student> studentArrayList = data.getAllStudentData();
        ArrayList<Student> result = new ArrayList<>();
        for(Student student : studentArrayList){
            if(student.getLastName().equals(lastName)) result.add(student);
        }
        return result;
    }

    @Override
    public ArrayList<Student> getStudentsByFirstName(String firstName) throws RemoteException, NullDataException{
        ArrayList<Student> studentArrayList = data.getAllStudentData();
        ArrayList<Student> result = new ArrayList<>();
        for(Student student : studentArrayList){
            if(student.getFirstName().equals(firstName)) result.add(student);
        }
        return result;
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        ArrayList<Student> studentArrayList = data.getAllStudentData();
        ArrayList<Student> result = new ArrayList<>();
        for(Student student : studentArrayList){
            if(student.getMajor().equals(major)) result.add(student);
        }
        return result;
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        ArrayList<Student> studentArrayList = data.getAllStudentData();
        ArrayList<Student> result = new ArrayList<>();
        for(Student student : studentArrayList){
            if(student.getCompletedCourses().contains(courseId)){
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        return false;
    }

    @Override
    public boolean updateStudent(Student student, Student newStudent) throws RemoteException{
        return false;
    }


    // Courses
    @Override
    public ArrayList<Course> getAllCoursesData() throws RemoteException, NullDataException{
        System.out.println("returning all course data");
        return data.getAllCoursesData();
    }

    @Override
    public boolean addCourse(Course course) throws RemoteException{
        System.out.println("Course added.\n>> " + course);
        return data.addCourse(course);
    }

    @Override
    public Course getCourseById(String id) throws RemoteException, NullDataException{
        System.out.println("Course requested.\n>> " + id);
        return data.getCourse(id);
    }

    @Override
    public ArrayList<Course> getCoursesByName(String courseName) throws RemoteException, NullDataException{
        ArrayList<Course> courseArrayList = data.getAllCoursesData();
        ArrayList<Course> result = new ArrayList<>();
        for(Course course : courseArrayList){
            if(course.getCourseName().equals(courseName)){
                result.add(course);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Course> getCoursesByProfessor(String courseProfessor) throws RemoteException, NullDataException{
        ArrayList<Course> courseList = data.getAllCoursesData();
        ArrayList<Course> result = new ArrayList<>();
        for(Course course : courseList){
            if(course.getProfName().equals(courseProfessor)){
                result.add(course);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Course> getCoursesByPreCourse(String coursePreCourse) throws RemoteException, NullDataException{
        ArrayList<Course> courseList = data.getAllCoursesData();
        ArrayList<Course> result = new ArrayList<>();
        for(Course course : courseList){
            for(String preCourse : course.getPreCourse()){
                if(preCourse.equals(coursePreCourse)){
                    result.add(course);
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteCourseById(String courseId) throws RemoteException{
        System.out.println("Course delete Requested\n>> " + courseId);
        return data.deleteCourse(courseId);
    }

    @Override
    public boolean updateCourse(Course course, Course newCourse) throws RemoteException{
        System.out.println("Course Update Requested");
        return false;
    }

    @Override
    public boolean makeReservation(String studentId, String courseId) throws RemoteException{
        try{
            Student student = data.getStudentById(studentId);
            Course course = data.getCourse(courseId);
            ArrayList<Reservation> reservationList = data.getReservations();
            // StudentId, CourseId Exist?
            if(student == null || course == null) throw new NullDataException("Student or Course is not exist.");
            // Student is already enrolled in the course?
            if(student.getCompletedCourses().contains(courseId)) throw new NullDataException("Student already reserved this course.");
            // get Reservation List of the course and check if the student is already enrolled.
            for(Reservation reservation : reservationList){
                if(reservation.getCourseId().equals(courseId) && reservation.getStudentId().equals(studentId)){
                    throw new NullDataException("Student already enrolled this course.");
                }
            }
            //  Student is already enrolled in the pre-course?
            if(course.getPreCourse() != null){
                for(String preCourse : course.getPreCourse()){
                    if(!student.getCompletedCourses().contains(preCourse)){
                        throw new NullDataException("Student didn't take pre-course.");
                    }
                }
            }
            // if not, add the student to the reservation list. and return true.
            data.makeReservation(studentId, courseId);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean addConnection(String id) throws RemoteException{
        if(clientList.contains(id)){
            System.out.println("The Client is already connected.\n>> "+id);
            return false;
        }
        clientList.add(id);
        System.out.println("New Client Connected\n>> " + id);
        return true;
    }

    @Override
    public boolean deleteConnection(String id) throws RemoteException{
        if(clientList.contains(id)) {
            System.out.println("Client Connection terminated.\n>> " + id);
            clientList.remove(id);
            return true;
        }
        System.out.println("Failed: Anonymous client disconnection requested.\n>> " + id);
        return false;
    }

}

