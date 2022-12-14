import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import Exceptions.NullDataException;
import MenuScripts.ECourse;
import Menus.Student.MGetStudentMenu;
import MenuScripts.EStudent;
import Objects.Student;
import Utils.Input.InputStudentValue;
import Utils.Print.Printer;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class StudentClient extends CommonClient {
    // Server Interface
    private final CSStudentIF server;
    private CSCourseIF courseServer;
    // Input Class
    private final InputStudentValue inputStudentValue;
    // Sub Class
    private final GetStudentClient getStudentClient;

    // Constructor
    public StudentClient(CSStudentIF server){
        this.server = server;
        this.getStudentClient = new GetStudentClient(server);
        this.inputStudentValue = new InputStudentValue();
    }

    public void initialize(CSCourseIF courseServer){
        this.courseServer = courseServer;
    }


    public void getAllStudents() throws RemoteException, NullDataException{
        try{
            ArrayList<Student> studentList = server.getAllStudents();
            Printer.printList(studentList, Student.class);
        } catch (RemoteException e){
            System.out.println(e.getMessage());
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getStudent() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(MGetStudentMenu.class, "Student Menu", GetStudentClient.class, this.getStudentClient);
    }

    public void addStudent() throws RemoteException, IOException, ServiceTerminateException{
        try{
            String studentId;
            while(true){
                studentId = inputStudentValue.inputStudentIdWithValidation();
                if(server.isStudentIdExist(studentId)) System.out.println(EStudent.ADD_FAIL_ALREADY_EXIST.getMessage());
                else break;
            }
            String name = inputStudentValue.inputStudentNameWithValidation();
            String major = inputStudentValue.inputStudentMajorWithValidation();
            Student student = new Student(studentId, name, major); // TODO: 학생이 처음 추가되는데 completedCourse가 있는게 이상하다. 로직상으로 안받게 해야지
            if(server.addStudent(student)) System.out.println(EStudent.ADD_SUCCESS.getMessage());
            else System.out.println(EStudent.ADD_FAIL.getMessage());
        } catch (EmptyInputException | InvalidDataException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudentById() throws RemoteException, IOException, ServiceTerminateException{
        try {
            String studentId = inputStudentValue.inputStudentIdWithValidation();
            if(server.deleteStudentById(studentId)) System.out.println(EStudent.DELETE_SUCCESS.getMessage());
            else System.out.println(EStudent.DELETE_FAIL.getMessage());
        } catch (EmptyInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudentById() throws IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String studentId;
        Student student;
        try{
            studentId = inputStudentValue.inputStudentIdWithValidation();
            student = server.getStudentById(studentId);
            if(student == null) throw new NullDataException(EStudent.NOT_FOUND.getMessage());
            System.out.println("Current Student Info");
            Printer.printItem(student);
        } catch (EmptyInputException | NullDataException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Input new student information. if you don't want to change, just press enter.");
        try {
            String name = inputStudentValue.inputStudentNameWithValidation();
            student.setName(name);
        }catch (EmptyInputException ignored){}
        try {
            String major = inputStudentValue.inputStudentMajorWithValidation();
            student.setMajor(major);
        }catch (EmptyInputException ignored){}
        try {
            do{
                ArrayList<String> completedCourses = inputStudentValue.inputStudentCompletedCourseWithValidation();
                if(!courseServer.isMultiCourseIdExist(completedCourses)){
                    System.out.println(EStudent.UPDATE_FAIL_INPUT_COMPLETED_COURSE_IS_NOT_EXIST.getMessage());
                    continue;
                }
                student.setCompletedCourses(completedCourses);
                break;
            } while (true);
        } catch (EmptyInputException ignored){}
        if(server.updateStudentById(studentId, student)) System.out.println(EStudent.UPDATE_SUCCESS.getMessage());
        else System.out.println(EStudent.UPDATE_FAIL.getMessage());
    }
}
