package Utils.Validator;


import Enums.ESemesterType;
import Exceptions.EmptyInputException;
import MenuScripts.ECourse;
import com.sun.media.sound.InvalidDataException;

import java.util.ArrayList;

// Validator 까지는 에러만 던지지 trycatch를 하지 않는다.
public class CourseValidator extends Validator {

    public static String checkValidCourseId(String courseId) throws EmptyInputException, InvalidDataException{
        // 5자리, 숫자 체크
        try{
            return checkValidId(courseId, 5);
        }catch (InvalidDataException e){
            throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
        }
    }

    public static String checkValidCourseName(String courseName) throws EmptyInputException, InvalidDataException, InvalidDataException{
        // 40자리, 한글, 영어, 숫자 체크
        return checkValidName(courseName, 40);
    }

    public static String checkValidCourseProfessor(String courseProfessor) throws EmptyInputException, InvalidDataException{
        // 20자리, 한글, 영어, 숫자 체크
        try{
            return checkValidName(courseProfessor, 20);
        } catch (InvalidDataException e){
            throw new InvalidDataException(ECourse.INVALID_COURSE_PROFESSOR.getMessage());
        }
    }

    public static String checkValidCourseSemester(String input) throws InvalidDataException, EmptyInputException{
        // 7자리, 2022-01, 2022-SM, 2022-02, 2022-WT 형식만 가능
        if(input == null || input.trim().length() == 0)
            throw new EmptyInputException();
        String semester = input.trim().toUpperCase();
        if(!semester.matches("^\\d{4}-[A-Z0-2]{2}$")
                || !ESemesterType.getSemesterList().contains(semester.substring(5, 7)))
            throw new InvalidDataException(ECourse.INVALID_COURSE_SEMESTER.getMessage());
        return semester;
    }

    public static ArrayList<String> checkValidPreCourseLine(String preCourseLine) throws InvalidDataException{
        // 5자리, 숫자, 콤마로 구분
        ArrayList<String> preCourseList = new ArrayList<String>();
        if(preCourseLine == null || preCourseLine.trim().length() == 0) return preCourseList;
        String[] preCourseIds = preCourseLine.split(",");
        try{
            for(String preCourseId : preCourseIds) {
                preCourseList.add(checkValidCourseId(preCourseId));
            }
        }catch (InvalidDataException | EmptyInputException e){
            throw new InvalidDataException(ECourse.INVALID_PRE_COURSE.getMessage());
        }
        return preCourseList;
    }


}
