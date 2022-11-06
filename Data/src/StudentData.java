import Exceptions.NullDataException;
import Lists.StudentList;
import Objects.Course;
import Objects.Student;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StudentData extends UnicastRemoteObject implements Serializable, DSStudentIF {
    // Attributes
    private final StudentList studentList;

    // Associations
    private CourseData courseData;
    public StudentData() throws IOException{
        this.studentList = new StudentList("Students.txt");
    }

    public void association(CourseData courseData){
        this.courseData = courseData;
    }

    @Override
    public ArrayList<Student> getAllStudents() throws RemoteException, NullDataException{
        System.out.println("returning all student data");
        ArrayList<Student> studentArrayList = studentList.getStudentList();
        System.out.println((studentArrayList.isEmpty()) ? ("Student is not found.") : ("Students are found.\n>>" + studentArrayList));
        return studentArrayList;
    }


    @Override
    public Student getStudentById(String id) throws RemoteException{
        System.out.println("Get Student requested.(CourseId : " + id + ")");
        Student student = studentList.getStudentById(id);
        System.out.println((student == null) ? ("Student is not found.\n>>" + id) : ("Student is found.\n>>" + student));
        return student;
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) throws RemoteException, NullDataException{
        System.out.println("Get Student requested.(CourseName : " + name + ")");
        ArrayList<Student> studentArrayList = studentList.getStudentsByName(name);
        System.out.println((studentArrayList.size() == 0) ? ("Student is not found.\n>>" + name) : ("Student is found.\n>>" + studentArrayList));
        return studentArrayList;
    }

    @Override
    public ArrayList<Student> getStudentsByMajor(String major) throws RemoteException, NullDataException{
        System.out.println("Get Student requested.(CourseMajor : " + major + ")");
        ArrayList<Student> studentArrayList = studentList.getStudentsByMajor(major);
        System.out.println((studentArrayList.isEmpty()) ? ("Student is not found.\n>>" + major) : ("Student is found.\n>>" + studentArrayList));
        return studentArrayList;
    }

    @Override
    public ArrayList<Student> getStudentsByCompletedCourse(String courseId) throws RemoteException, NullDataException{
        if (courseData.getCourseById(courseId) == null) throw new NullDataException("Course is not found.");
        System.out.println("Get Student requested.(CompletedCourseId : " + courseId + ")");
        ArrayList<Student> studentArrayList = studentList.getStudentsByCompletedCourse(courseId);
        System.out.println((studentArrayList.isEmpty()) ? ("Student is not found.\n>>" + courseId) : ("Student is found.\n>>" + studentArrayList));
        return studentArrayList;
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        System.out.println("Add Student requested.(Student : " + student + ")");
        boolean result = studentList.add(student);
        System.out.println((result) ? ("Student is added.\n>>" + student) : ("Student is not added.\n>>" + student));
        return result;
    }

    @Override
    public boolean deleteStudentById(String studentId) throws RemoteException{
        System.out.println("Delete Student requested.(StudentId : " + studentId + ")");
        boolean result = studentList.deleteStudentById(studentId);
        System.out.println((result) ? ("Student is deleted.\n>>" + studentId) : ("Student is not deleted.\n>>" + studentId));
        return result;
    }

    @Override
    public boolean updateStudentById(String studentId, Student newStudent) throws RemoteException{
        System.out.println("Update Student requested.(StudentId : " + studentId + " -> " + newStudent + ")");
        boolean result = studentList.updateStudentById(studentId, newStudent);
        System.out.println((result) ? ("Student is updated.\n>>" + studentId + " -> " + newStudent) : ("Student is not updated.\n>>" + studentId + " -> " + newStudent));
        return result;
    }

    @Override
    public boolean isStudentIdExist(String studentId) throws RemoteException{
        System.out.println("Is StudentId Exist requested.(StudentId : " + studentId + ")");
        boolean result = studentList.getStudentById(studentId) != null;
        System.out.println((result) ? ("StudentId is exist.\n>>" + studentId) : ("StudentId is not exist.\n>>" + studentId));
        return result;
    }
}