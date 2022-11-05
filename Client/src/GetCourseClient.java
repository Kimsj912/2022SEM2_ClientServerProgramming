import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import MenuScripts.ECourse;
import Objects.Course;
import Utils.Input.InputCourseValue;
import Utils.Input.InputValue;
import Utils.Print.Printer;
import Utils.Validator.CourseValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GetCourseClient extends CommonClient {
    private final InputCourseValue inputCourseValue = new InputCourseValue();
    private final ServerIF server;

    public GetCourseClient(ServerIF server) {
        this.server = server;
    }

    public void getCourseById() throws RemoteException, IOException, ServiceTerminateException{
        try {
            String courseId = inputCourseValue.inputCourseIdWithValidation();
            System.out.println("request to Server for Course Id: " + courseId);
            Course course = server.getCourseById(courseId);
            Printer.printItem(course);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getCoursesByName() throws RemoteException, IOException, ServiceTerminateException{
        try {
            String courseName = inputCourseValue.inputCourseNameWithValidation();
            ArrayList<Course> courses = server.getCoursesByName(courseName);
            Printer.printList(courses, Course.class);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getCoursesByProfessor() throws RemoteException, IOException, ServiceTerminateException{
        try {
            String courseProfName = inputCourseValue.inputCourseProfNameWithValidation();
            ArrayList<Course> courses = server.getCoursesByProfessor(courseProfName);
            Printer.printList(courses, Course.class);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getCoursesBySemester() throws RemoteException, IOException, ServiceTerminateException{
        try {
            String courseSemester = inputCourseValue.inputCourseSemesterWithValidation();
            ArrayList<Course> courses = server.getCoursesBySemester(courseSemester);
            Printer.printList(courses, Course.class);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }

    public void getCoursesByPreCourseId() throws RemoteException, IOException, ServiceTerminateException{
        try {
            String courseId = InputValue.getInputString(ECourse.ENTER_COURSE_ID.getMessage(), true);
            ArrayList<Course> courses = server.getCoursesByPreCourseId(CourseValidator.checkValidCourseId(courseId));
            Printer.printList(courses, Course.class);
        } catch (InvalidDataException | EmptyInputException e) {
            System.out.println(e.getMessage());
        } catch (NullDataException e) {
            System.out.println(ECourse.GET_FAIL.getMessage());
        }
    }
}