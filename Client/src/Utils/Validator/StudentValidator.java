package Utils.Validator;

import Enums.EMajor;
import Exceptions.EmptyInputException;

import java.util.Arrays;

public class StudentValidator extends Validator {
    public static boolean checkValidStudentId(String studentId) throws EmptyInputException{
        return checkValidId(studentId, 8);
    }

    public static boolean checkValidStudentName(String studentName) throws EmptyInputException{
        return checkValidName(studentName, 20);
    }

    public static boolean checkValidStudentMajor(String major) throws EmptyInputException{
        if(major == null || major.isEmpty() || major.trim().isEmpty()){
            throw new EmptyInputException();
        }
        return Arrays.stream(EMajor.values()).anyMatch(m -> m.getAbbreviation().equals(major));
    }




}
