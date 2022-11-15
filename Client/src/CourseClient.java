import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import MenuScripts.ECourse;
import MethodEnums.Course.SGetCourseMenu;
import Objects.Course;
import Utils.Input.InputCourseValue;
import Utils.Print.Printer;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CourseClient extends CommonClient {
    // Server Interface
    private final CSCourseIF server;
    // Input Class
    private final InputCourseValue inputCourseValue;
    // Sub Class
    private final GetCourseClient getCourseClient;

    // Constructor
    public CourseClient(CSCourseIF server){
        this.server = server;
        this.getCourseClient = new GetCourseClient(server);
        this.inputCourseValue = new InputCourseValue();
    }

    public void getAllCourses() throws RemoteException, NullDataException {
        try{
            ArrayList<Course> courseList = server.getAllCourses();
            Printer.printList(courseList, Course.class);
        } catch ( NullDataException e){
            e.printStackTrace();
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getCourse() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SGetCourseMenu.class, "Course Menu", GetCourseClient.class, this.getCourseClient);
    }

    public void addCourse() throws RemoteException, IOException, ServiceTerminateException{
        try{
            String courseId = null;
            while(true){
                courseId = inputCourseValue.inputCourseIdWithValidation();
                if(server.isCourseIdExist(courseId)) System.out.println(ECourse.ADD_FAIL_ALREADY_EXIST.getMessage());
                else break;
            }
            String courseProfName = inputCourseValue.inputCourseProfNameWithValidation();
            String courseName = inputCourseValue.inputCourseNameWithValidation();
            String courseSemester = inputCourseValue.inputCourseSemesterWithValidation();
            ArrayList<String> coursePreCourse = null;
            while(true){
                coursePreCourse = inputCourseValue.inputCoursePreCourseWithValidation();
                if(!server.isMultiCourseIdExist(coursePreCourse)){
                    System.out.println(ECourse.ADD_FAIL_PRE_COURSE_IS_NOT_EXIST.getMessage());
                    continue;
                }
                break;
            }
            // Create Course Object
            Course course = new Course(courseId, courseProfName, courseName, courseSemester, coursePreCourse);
            // Request to Server
            if(server.addCourse(course)) System.out.println(ECourse.ADD_SUCCESS.getMessage());
            else System.out.println(ECourse.ADD_FAIL.getMessage());
        }catch (EmptyInputException | InvalidDataException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCourseById() throws RemoteException, IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            if(server.deleteCourseById(courseId)) System.out.println(ECourse.DELETE_SUCCESS.getMessage());
            else System.out.println(ECourse.DELETE_FAIL.getMessage());
        } catch (EmptyInputException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCourseById() throws RemoteException, IOException, ServiceTerminateException{
        Course course = null;
        String courseId = null;
        try{
            courseId = inputCourseValue.inputCourseIdWithValidation();
            course = server.getCourseById(courseId);
            if(course == null) throw new NullDataException(ECourse.NOT_FOUND.getMessage());
            System.out.println("Current Course Info");
            Printer.printItem(course);
        } catch (EmptyInputException | NullDataException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Input new course information. if you don't want to change, just press enter.");
        try {
            String courseProfessor = inputCourseValue.inputCourseProfNameWithValidation();
            course.setProfessor(courseProfessor);
        } catch (EmptyInputException ignored){}
        try{
            String courseName = inputCourseValue.inputCourseNameWithValidation();
            course.setName(courseName);
        } catch (EmptyInputException ignored){}
        try {
            String courseSemester = inputCourseValue.inputCourseSemesterWithValidation();
            course.setSemester(courseSemester);
        } catch (EmptyInputException ignored){}
        try {
            do{
                ArrayList<String> coursePreCourse = inputCourseValue.inputCoursePreCourseWithValidation();
                if(!server.isMultiCourseIdExist(coursePreCourse)){
                    System.out.println(ECourse.UPDATE_FAIL_INPUT_PRE_COURSE_IS_NOT_EXIST.getMessage());
                    continue;
                }
                course.setPreCourse(coursePreCourse);
                break;
            } while (true);
        } catch (EmptyInputException ignored){}
        if(server.updateCourseById(courseId, course)) System.out.println(ECourse.UPDATE_SUCCESS.getMessage());
        else System.out.println(ECourse.UPDATE_FAIL.getMessage());
    }
}



