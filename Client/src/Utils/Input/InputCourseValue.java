package Utils.Input;

import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import MenuScripts.ECourse;
import Utils.Validator.CourseValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.util.ArrayList;

public class InputCourseValue extends InputValue{
    public String inputCourseIdWithValidation() throws ServiceTerminateException, IOException, EmptyInputException{
        try {
            String courseId = getInputString(ECourse.ENTER_COURSE_ID.getMessage(), true);
            return CourseValidator.checkValidCourseId(courseId);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputCourseIdWithValidation();
        }
    }

    public String inputCourseNameWithValidation() throws ServiceTerminateException, IOException, EmptyInputException{
        try {
            String courseName = getInputString(ECourse.ENTER_COURSE_NAME.getMessage(), true);
            return CourseValidator.checkValidCourseName(courseName);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputCourseNameWithValidation();
        }
    }

    public String inputCourseProfNameWithValidation() throws ServiceTerminateException, IOException, EmptyInputException{
        try {
            String professor = getInputString(ECourse.ENTER_PROFESSOR.getMessage(), true);
            return CourseValidator.checkValidCourseProfessor(professor);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputCourseProfNameWithValidation();
        }
    }

    public String inputCourseSemesterWithValidation() throws ServiceTerminateException, IOException, EmptyInputException{
        try {
            String courseSemester = getInputString(ECourse.ENTER_SEMESTER.getMessage(), true);
            return CourseValidator.checkValidCourseSemester(courseSemester);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputCourseSemesterWithValidation();
        }
    }

    public ArrayList<String> inputCoursePreCourseWithValidation() throws IOException, ServiceTerminateException, EmptyInputException{
        try {
            String courseIdLine = getInputString(ECourse.ENTER_PRE_COURSES_MULTI.getMessage(), true);
            return CourseValidator.checkValidMultiCourseIdLine(courseIdLine);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputCoursePreCourseWithValidation();
        } catch (EmptyInputException e) {
            return new ArrayList<>();
        }
    }

}