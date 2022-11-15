package Utils.Validator;

import Enums.EMajor;
import Exceptions.EmptyInputException;
import MenuScripts.EStudent;
import com.sun.media.sound.InvalidDataException;

import java.util.Arrays;

// Validator 까지는 에러만 던지지 trycatch를 하지 않는다.
public class StudentValidator extends Validator {
    public static String checkValidStudentId(String studentId) throws EmptyInputException, InvalidDataException{
        try{
            return checkValidId(studentId, 8);
        }catch(InvalidDataException e){
            throw new InvalidDataException(EStudent.INVALID_STUDENT_ID.getMessage());
        }
    }

    public static String checkValidStudentName(String studentName) throws EmptyInputException, InvalidDataException{
        try{
            return checkValidName(studentName, 20);
        } catch (InvalidDataException e) {
            throw new InvalidDataException(EStudent.INVALID_STUDENT_NAME.getMessage());
        }
    }

    public static String checkValidStudentMajor(String major) throws EmptyInputException, InvalidDataException{
        if(major == null || major.isEmpty() || major.trim().isEmpty()){
            throw new EmptyInputException();
        }
        if(Arrays.stream(EMajor.getMajorAbbrList()).noneMatch(m -> m.equals(major))){
            throw new InvalidDataException(EStudent.INVALID_STUDENT_MAJOR.getMessage());
        }
        return major;
    }




}
