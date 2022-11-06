import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import MenuScripts.EStudent;
import Objects.Student;
import Utils.Input.InputCourseValue;
import Utils.Input.InputStudentValue;
import Utils.Print.Printer;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GetStudentClient extends CommonClient {
    private final InputStudentValue inputStudentValue;
    private final InputCourseValue inputCourseValue;
    private final CSStudentIF server;

    public GetStudentClient(CSStudentIF server){
        this.server = server;
        inputStudentValue = new InputStudentValue();
        inputCourseValue = new InputCourseValue();
    }

    public void getStudentById() throws RemoteException, IOException, ServiceTerminateException{
        try{
            String studentId = inputStudentValue.inputStudentIdWithValidation();
            Student student = server.getStudentById(studentId);
            Printer.printItem(student);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(EStudent.GET_FAIL.getMessage());
        }
    }

    public void getStudentsByName() throws RemoteException,IOException, ServiceTerminateException{
        try{
            String name = inputStudentValue.inputStudentNameWithValidation();
            ArrayList<Student> studentList = server.getStudentsByName(name);
            Printer.printList(studentList, Student.class);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(EStudent.GET_FAIL.getMessage());
        }
    }

    public void getStudentsByMajor() throws RemoteException,  IOException, ServiceTerminateException{
        try{
            String major = inputStudentValue.inputStudentMajorWithValidation();
            ArrayList<Student> studentList = server.getStudentsByMajor(major);
            Printer.printList(studentList, Student.class);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(EStudent.GET_FAIL.getMessage());
        }
    }

    public void getStudentsByCompletedCourseId() throws RemoteException, IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            ArrayList<Student> studentList = server.getStudentsByCompletedCourse(courseId);
            Printer.printList(studentList, Student.class);
        } catch (InvalidDataException | EmptyInputException | NullDataException e) {
            System.out.println(e.getMessage());
        }
    }
}