import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import ServerClientIF.CourseIF;
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
    private final CourseIF server;
    private final InputCourseValue inputCourseValue = new InputCourseValue();
    private final GetCourseClient getCourseClient;

    public CourseClient(ServerIF server){
        this.server = server;
        this.getCourseClient = new GetCourseClient(server);
    }

    public void getAllCourses() throws RemoteException, NullDataException {
        try{
            ArrayList<Course> courseList = server.getAllCourses();
            Printer.printList(courseList, Course.class);
        } catch (RemoteException e){
            System.out.println(e.getMessage());
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getCourse() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SGetCourseMenu.class, "Course Menu", GetCourseClient.class, this.getCourseClient);
    }

    public void addCourse() throws RemoteException, IOException, ServiceTerminateException{
        try{
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            if(server.isCourseIdExist(courseId)){
                System.out.println(ECourse.ADD_FAIL_ALREADY_EXIST.getMessage());
                return;
            }
            String courseProfName = inputCourseValue.inputCourseProfNameWithValidation();
            String courseName = inputCourseValue.inputCourseNameWithValidation();
            String courseSemester = inputCourseValue.inputCourseSemesterWithValidation();
            ArrayList<String> coursePreCourse = inputCourseValue.inputCoursePreCourseWithValidation();
            if(server.isMultiCourseIdExist(coursePreCourse)){
                System.out.println(ECourse.ADD_FAIL_PRE_COURSE_IS_NOT_EXIST.getMessage());
                return;
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
            System.out.println("Input new course information. if you don't want to change, just press enter.");
        } catch (EmptyInputException e){System.out.println(e.getMessage());
        } catch (NullDataException e){System.out.println(e.getMessage()); return;}

        try{
            if(course == null) return;
            String courseName = inputCourseValue.inputCourseNameWithValidation();
            if(courseName == null) return; // --b Ã³¸®
            course.setName(courseName);

            String courseProfessor = inputCourseValue.inputCourseProfNameWithValidation();
            if(courseProfessor == null) return;
            course.setProfessor(courseProfessor);

            ArrayList<String> rawPreCourses = inputCourseValue.inputCoursePreCourseWithValidation();
            course.setPreCourse(rawPreCourses);

            String courseSemester = inputCourseValue.inputCourseSemesterWithValidation();
            if (courseSemester == null) return;
            course.setSemester(courseSemester);

            int maxCapacity = inputCourseValue.inputMaxCapacityWithValidation();
            if (maxCapacity == -1) return;
            course.setMaxCapacity(maxCapacity);
        } catch (InvalidDataException e){
            System.out.println(e.getMessage());
        } catch (EmptyInputException ignored){}

        if(server.updateCourseById(courseId, course)) System.out.println(ECourse.UPDATE_SUCCESS.getMessage());
        else System.out.println(ECourse.UPDATE_FAIL.getMessage());
    }
}



