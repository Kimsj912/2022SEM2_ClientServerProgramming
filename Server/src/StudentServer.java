import Exceptions.NullDataException;
import Objects.Student;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StudentServer extends UnicastRemoteObject implements Serializable, CSStudentIF {
    private final DSStudentIF data;
    private CourseServer courseServer;

    public DSStudentIF getData(){return this.data;}

    public StudentServer(DSStudentIF data) throws RemoteException{
        super();
        this.data = data;
    }

    public void association(CourseServer courseServer){
        this.courseServer = courseServer;
    }

    @Override
    public ArrayList<Student> getAllStudents() throws RemoteException, NullDataException{
        System.out.println("returning student's all data");
        return data.getAllStudents();
    }

    @Override
    public Student getStudentById(String id) throws RemoteException, NullDataException{
        System.out.println("get student by id : " + id);
        return data.getStudentById(id);
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException{
        System.out.println("get students by name : " + name);
        return data.getStudentsByName(name);
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        System.out.println("Get students by major : " + major);
        return data.getStudentsByMajor(major);
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        System.out.println("Get Student By Completed CourseID :" + courseId);
        return data.getStudentsByCompletedCourse(courseId);
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        System.out.print("Student added :" + student);
        if (data.addStudent(student)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        System.out.print("Student deleted :" + studentId);
        if (data.deleteStudentById(studentId)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }

    @Override
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException{
        System.out.println("Student updated :" + studentId);
        if (data.updateStudentById(studentId, newStudent)) {
            System.out.println(" >> Success");
            return true;
        } else {
            System.out.println(" >> Fail");
            return false;
        }
    }

    @Override
    public boolean isStudentIdExist(String studentId) throws RemoteException{
        System.out.println("Student id exist :" + studentId);
        return data.isStudentIdExist(studentId);
    }
}