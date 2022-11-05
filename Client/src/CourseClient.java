import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import MenuScripts.ECourse;
import MethodEnums.Course.SGetCourseMenu;
import Objects.Course;
import Utils.Input.InputValue;
import Utils.Print.Printer;
import Utils.Validator.CourseValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CourseClient extends CommonClient {
    private static CourseIF server;
    public CourseClient(ServerIF server){
        CourseClient.server = server;
    }

    public void getAllCoursesWithPage(int page) throws RemoteException, NullDataException {
        ArrayList<Course> courses = server.getAllCoursesWithPage(1);
        Printer.printList(courses, Course.class);
    }

    public void getCourse() throws IOException, ServiceTerminateException, EmptyInputException{
        selectMenu(SGetCourseMenu.class, "Course Menu", this.getClass(), this);
    }

    public void getCourseById() throws RemoteException, IOException, ServiceTerminateException, EmptyInputException, NullDataException{
        String courseId = getCourseIdWithValidation();
        Course course = server.getCourseById(courseId);
        Printer.printItem(course);
    }

    public void getCoursesByName() throws RemoteException, IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseName = getCourseNameWithValidation();
        ArrayList<Course> courses = server.getCoursesByName(courseName);
        Printer.printList(courses, Course.class);
    }

    public void getCoursesByProfessor() throws RemoteException, IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseProfName = getCourseProfNameWithValidation();
        ArrayList<Course> courses = server.getCoursesByProfessor(courseProfName);
        Printer.printList(courses, Course.class);
    }

    public void getCoursesBySemester() throws RemoteException, IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseSemester = getCourseSemesterWithValidation();
        ArrayList<Course> courses = server.getCoursesBySemester(courseSemester);
        Printer.printList(courses, Course.class);
    }

    public void getCoursesByPreCourseId() throws RemoteException, IOException, NullDataException, ServiceTerminateException, EmptyInputException{
        String courseId = InputValue.getInputString(ECourse.ENTER_PRE_COURSES_SINGLE.getMessage(), true);
        if(!CourseValidator.checkValidCourseId(courseId))
            throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
        ArrayList<Course> courses = server.getCoursesByPreCourseId(courseId);
        Printer.printList(courses, Course.class);
    }

    public void addCourse() throws RemoteException, IOException, ServiceTerminateException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        String courseProfName = getCourseProfNameWithValidation();
        String courseName = getCourseNameWithValidation();
        String courseSemester = getCourseSemesterWithValidation();
        int maxCapacity = getMaxCapacityWithValidation();
        ArrayList<String> coursePreCourse = getCoursePreCourseWithValidation();

        // Create Course Object
        Course course = new Course(courseId, courseProfName, courseName, courseSemester, maxCapacity, coursePreCourse);
        // Request to Server
        if(server.addCourse(course)) System.out.println(ECourse.ADD_SUCCESS.getMessage());
        else System.out.println(ECourse.ADD_FAIL.getMessage());
    }

    public void deleteCourseById() throws RemoteException, IOException, ServiceTerminateException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        server.deleteCourseById(courseId);
        System.out.println(ECourse.DELETE_SUCCESS.getMessage());
    }

    public void updateCourseById() throws RemoteException, IOException, ServiceTerminateException, NullDataException, EmptyInputException{
        String courseId = getCourseIdWithValidation();
        Course course = server.getCourseById(courseId);
        if(course == null) throw new NullDataException(ECourse.NOT_FOUND.getMessage());
        Printer.printItem(course);

        System.out.println("Input new course information. if you don't want to change, just press enter.");

        try{
            String courseName = getCourseNameWithValidation();
            if(courseName == null) return;
            course.setName(courseName);
        }catch (EmptyInputException ignored){}

        try{
            String courseProfessor = getCourseProfNameWithValidation();
            if(courseProfessor == null) return;
            course.setProfessor(courseProfessor);
        }catch (EmptyInputException ignored){}

        try{
            ArrayList<String> rawPreCourses = getCoursePreCourseWithValidation();
            course.setPreCourse(rawPreCourses);
        }catch (EmptyInputException ignored){}

        try {
            String courseSemester = getCourseSemesterWithValidation();
            if (courseSemester == null) return;
            course.setSemester(courseSemester);
        }catch (EmptyInputException ignored){}

        try {
            int maxCapacity = getMaxCapacityWithValidation();
            if (maxCapacity == -1) return;
            course.setMaxCapacity(maxCapacity);
        }catch (EmptyInputException ignored){}

        if(server.updateCourseById(courseId, course)) System.out.println(ECourse.UPDATE_SUCCESS.getMessage());
        else System.out.println(ECourse.UPDATE_FAIL.getMessage());
    }


    // Get Values With Validation
    private String getCourseIdWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        String courseId = InputValue.getInputString(ECourse.ENTER_COURSE_ID.getMessage(), false);
        if(!CourseValidator.checkValidCourseId(courseId))
            throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
        return courseId;
    }
    private String getCourseNameWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        String courseName = InputValue.getInputString(ECourse.ENTER_COURSE_NAME.getMessage(), true);
        if(courseName == null) return null;
        if(!CourseValidator.checkValidCourseName(courseName))
            throw new InvalidDataException(ECourse.INVALID_COURSE_NAME.getMessage());
        return courseName;
    }
    private String getCourseProfNameWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        String courseName = InputValue.getInputString(ECourse.ENTER_COURSE_NAME.getMessage(), false);
        if(!CourseValidator.checkValidCourseName(courseName))
            throw new InvalidDataException(ECourse.INVALID_COURSE_NAME.getMessage());
        return courseName;
    }
    private String getCourseSemesterWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        String courseSemester = InputValue.getInputString(ECourse.ENTER_SEMESTER.getMessage(), true);
        if(courseSemester == null) return null;
        if(!CourseValidator.checkValidCourseSemester(courseSemester))
            throw new InvalidDataException(ECourse.INVALID_COURSE_SEMESTER.getMessage());
        return courseSemester;
    }
    private ArrayList<String> getCoursePreCourseWithValidation() throws IOException, ServiceTerminateException, EmptyInputException{
        ArrayList<String> coursePreCourse = new ArrayList<>();
        while(true){
            String courseId = InputValue.getInputString(ECourse.ENTER_PRE_COURSES_SINGLE.getMessage(), true);
            if(courseId == null ||courseId.equals("")) break;
            if(!CourseValidator.checkValidCourseId(courseId))
                throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
            coursePreCourse.add(courseId);
        }
        return coursePreCourse;
    }
    private int getMaxCapacityWithValidation() throws ServiceTerminateException, EmptyInputException, IOException{
        int maxCapacity = InputValue.getInputInteger(ECourse.ENTER_MAX_CAPACITY.getMessage(), false);
        if(!CourseValidator.checkValidMaxCapacity(maxCapacity))
            throw new InvalidDataException(ECourse.INVALID_MAX_CAPACITY.getMessage());
        return maxCapacity;
    }


}



