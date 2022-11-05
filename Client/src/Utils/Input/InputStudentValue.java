package Utils.Input;

import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import MenuScripts.EStudent;
import Utils.Validator.CourseValidator;
import Utils.Validator.StudentValidator;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.util.ArrayList;

public class InputStudentValue extends InputValue {
    public String inputStudentIdWithValidation() throws IOException, ServiceTerminateException, EmptyInputException{
        try {
            String studentId = getInputString(EStudent.ENTER_STUDENT_ID.getMessage(), true);
            return StudentValidator.checkValidStudentId(studentId);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputStudentIdWithValidation();
        }
    }

    public String inputStudentNameWithValidation() throws IOException, ServiceTerminateException, EmptyInputException{
        try {
            String name = getInputString(EStudent.ENTER_STUDENT_NAME.getMessage(), true);
            return StudentValidator.checkValidStudentName(name);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputStudentNameWithValidation();
        }
    }

    public String inputStudentMajorWithValidation() throws IOException, ServiceTerminateException, EmptyInputException{
        try {
            String major = getInputString(EStudent.ENTER_STUDENT_MAJOR.getMessage(), true);
            return StudentValidator.checkValidStudentMajor(major);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputStudentMajorWithValidation();
        }
    }

    public ArrayList<String> inputStudentCompletedCourseWithValidation() throws IOException, ServiceTerminateException, EmptyInputException{
        try {
            String courseIdLine = getInputString(EStudent.ENTER_COMPLETED_COURSE_MULTI.getMessage(), true);
            return CourseValidator.checkValidMultiCourseIdLine(courseIdLine);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return inputStudentCompletedCourseWithValidation();
        } catch (EmptyInputException e) {
            return new ArrayList<>();
        }
    }
}