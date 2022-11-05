package Utils.Validator;

import Enums.EMajor;
import Exceptions.EmptyInputException;
import MenuScripts.EStudent;
import com.sun.media.sound.InvalidDataException;

import java.util.Arrays;

// Validator ������ ������ ������ trycatch�� ���� �ʴ´�.
public class StudentValidator extends Validator {
    public static String checkValidStudentId(String studentId) throws EmptyInputException, InvalidDataException{
        try{
            return checkValidId(studentId, 8);
        }catch(InvalidDataException e){
            throw new InvalidDataException(EStudent.INVALID_STUDENT_ID.getMessage());
        }
    }

    public static String checkValidStudentName(String studentName) throws EmptyInputException, InvalidDataException{
        return checkValidName(studentName, 20);
    }

    public static String checkValidStudentMajor(String major) throws EmptyInputException, InvalidDataException{
        if(major == null || major.isEmpty() || major.trim().isEmpty()){
            throw new EmptyInputException();
        }
        if(Arrays.stream(EMajor.values()).noneMatch(m -> m.name().equals(major))){
            throw new InvalidDataException("Major must be one of the following: " + Arrays.toString(EMajor.values()));
        }
        return major;
    }




}
