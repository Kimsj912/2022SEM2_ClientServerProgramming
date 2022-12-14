package Utils.Validator;


import Enums.ESemesterType;
import Exceptions.EmptyInputException;

public class CourseValidator extends Validator {

    public static boolean checkValidCourseId(String courseId) throws EmptyInputException{
        // 5자리, 숫자 체크
        return checkValidId(courseId, 5);
    }

    public static boolean checkValidCourseName(String courseName) throws EmptyInputException{
        // 40자리, 한글, 영어, 숫자 체크
        return checkValidName(courseName, 40);
    }

    public static boolean checkValidCourseProfessor(String courseProfessor) throws EmptyInputException{
        // 20자리, 한글, 영어, 숫자 체크
        return checkValidName(courseProfessor, 20);
    }

    public static boolean checkValidCourseSemester(String semester){
        // 7자리, 2022-01, 2022-SM, 2022-02, 2022-WT 형식만 가능
        if(!semester.matches("^[0-9][A-Z]*$"))return false;
        if(semester.length() != 7) return false;
        if(semester.charAt(4) != '-') return false;
        String semesterType = semester.substring(5, 7);
        if(ESemesterType.getSemesterList().contains(semesterType)) return false;
        return true;
    }

    public static boolean checkValidMaxCapacity(int maxCapacity){
        // 0 ~ 1000 (TODO: 처음에 총 학생수를 받아와서 maxCapcity의 값을 지정하면 더 상황에 맞게 오래 갈 수 있는 코드가 될 것 같다)
        return maxCapacity < 0 || maxCapacity > 1000;
    }

}
