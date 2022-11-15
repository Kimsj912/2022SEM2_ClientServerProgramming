package Utils.Validator;


import Enums.ESemesterType;
import Exceptions.EmptyInputException;
import MenuScripts.ECourse;
import com.sun.media.sound.InvalidDataException;

import java.util.ArrayList;

// Validator ������ ������ ������ trycatch�� ���� �ʴ´�.
public class CourseValidator extends Validator {

    public static String checkValidCourseId(String courseId) throws EmptyInputException, InvalidDataException{
        // 5�ڸ�, ���� üũ
        try{
            return checkValidId(courseId, 5);
        }catch (InvalidDataException e){
            throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
        }
    }

    public static String checkValidCourseName(String courseName) throws EmptyInputException, InvalidDataException, InvalidDataException{
        // 40�ڸ�, �ѱ�, ����, ���� üũ
        return checkValidName(courseName, 40);
    }

    public static String checkValidCourseProfessor(String courseProfessor) throws EmptyInputException, InvalidDataException{
        // 20�ڸ�, �ѱ�, ����, ���� üũ
        try{
            return checkValidName(courseProfessor, 20);
        } catch (InvalidDataException e){
            throw new InvalidDataException(ECourse.INVALID_COURSE_PROFESSOR.getMessage());
        }
    }

    public static String checkValidCourseSemester(String input) throws InvalidDataException, EmptyInputException{
        // 7�ڸ�, 2022-01, 2022-SM, 2022-02, 2022-WT ���ĸ� ����
        if(input == null || input.trim().length() == 0)
            throw new EmptyInputException();
        String semester = input.trim().toUpperCase();
        if(!semester.matches("^\\d{4}-[A-Z0-2]{2}$")
                || !ESemesterType.getSemesterList().contains(semester.substring(5, 7)))
            throw new InvalidDataException(ECourse.INVALID_COURSE_SEMESTER.getMessage());
        return semester;
    }

    public static ArrayList<String> checkValidPreCourseLine(String preCourseLine) throws InvalidDataException{
        // 5�ڸ�, ����, �޸��� ����
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
