import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import Exceptions.NullDataException;
import MenuScripts.ECourse;
import MethodEnums.Student.SGetStudentMenu;
import MenuScripts.EStudent;
import Objects.Student;
import Utils.Input.InputValue;
import Utils.Print.Printer;
import Utils.Validator.CourseValidator;
import Utils.Validator.StudentValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class StudentClient extends CommonClient {

    private static ServerIF server;

    public StudentClient(ServerIF server){
        StudentClient.server = server;
    }


    public void getAllStudents() throws RemoteException, NullDataException{
        ArrayList<Student> studentList = server.getAllStudents();
        Printer.printList(studentList, Student.class);
    }

    public void getStudent() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SGetStudentMenu.class, "Student Menu", this.getClass(), this);
    }

    public void getStudentById() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = getStudentIdWithValidation();
        Student student = server.getStudentById(studentId);
        Printer.printItem(student);
    }

    public void getStudentsByName() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String name = getStudentNameWithValidation();
        ArrayList<Student> studentList = server.getStudentsByName(name);
        Printer.printList(studentList, Student.class);
    }

    public void getStudentsByMajor() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String major = getStudentMajorWithValidation();
        ArrayList<Student> studentList = server.getStudentsByMajor(major);
        Printer.printList(studentList, Student.class);
    }

    public void getStudentsByCompletedCourse() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseId = getCompletedCourseIdWithValidation();
        ArrayList<Student> studentList = server.getStudentsByCompletedCourse(courseId);
        Printer.printList(studentList, Student.class);
    }

    public void addStudent() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = getStudentIdWithValidation();
        String name = getStudentNameWithValidation();
        String major = getStudentMajorWithValidation();

        Student student = new Student(studentId, name, major); // TODO: 학생이 처음 추가되는데 completedCourse가 있는게 이상하다. 로직상으로 안받게 해야지
        if(server.addStudent(student)) System.out.println(EStudent.ADD_SUCCESS.getMessage());
        else System.out.println(EStudent.ADD_FAIL.getMessage());
    }

    public void deleteStudentById() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = getStudentIdWithValidation();
        if(server.deleteStudentById(studentId)) System.out.println(EStudent.DELETE_SUCCESS.getMessage());
        else System.out.println(EStudent.DELETE_FAIL.getMessage());
    }

    public void updateStudentById() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId = getStudentIdWithValidation();
        Student student = server.getStudentById(studentId);
        if(student == null) throw new NullDataException(EStudent.NOT_FOUND.getMessage());
        Printer.printItem(student);

        String name = getStudentNameWithValidation();
        if(name == null) return;
        student.setName(name);

        String major = getStudentMajorWithValidation();
        if(major == null) return;
        student.setMajor(major);

        if(server.updateStudentById(studentId, student)) System.out.println(EStudent.UPDATE_SUCCESS.getMessage());
        else System.out.println(EStudent.UPDATE_FAIL.getMessage());
    }

    // Get Values with Validation
    private String getStudentIdWithValidation() throws IOException, ServiceTerminateException{
        try{
            String studentId = InputValue.getInputString(EStudent.ENTER_STUDENT_ID.getMessage(), false);
            return StudentValidator.checkValidStudentId(studentId);
        } catch (InvalidDataException | EmptyInputException e){
            System.out.println(e.getMessage());
            return getStudentIdWithValidation();
        }
    }
    private String getStudentNameWithValidation() throws IOException, ServiceTerminateException{
        try{
            String name = InputValue.getInputString(EStudent.ENTER_STUDENT_NAME.getMessage(), false);
            return StudentValidator.checkValidStudentName(name);
        } catch (InvalidDataException | EmptyInputException e){
            System.out.println(e.getMessage());
            return getStudentNameWithValidation();
        }
    }
    private String getStudentMajorWithValidation() throws IOException, ServiceTerminateException{
        try{
            String major = InputValue.getInputString(EStudent.ENTER_STUDENT_MAJOR.getMessage(), false);
            return StudentValidator.checkValidStudentMajor(major);
        } catch (InvalidDataException | EmptyInputException e){
            System.out.println(e.getMessage());
            return getStudentMajorWithValidation();
        }
    }
    private String getCompletedCourseIdWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        try{
            String courseId = InputValue.getInputString(EStudent.ENTER_COMPLETED_COURSE_ID.getMessage(), false);
            return CourseValidator.checkValidCourseId(courseId);
        } catch (InvalidDataException | EmptyInputException e){
            System.out.println(e.getMessage());
            return getCompletedCourseIdWithValidation();
        }
    }
}
