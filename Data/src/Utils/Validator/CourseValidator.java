package Utils.Validator;


import Enums.ESemesterType;
import Exceptions.EmptyInputException;

public class CourseValidator extends Validator {

    public static boolean checkValidCourseId(String courseId) throws EmptyInputException{
        // 5�ڸ�, ���� üũ
        return checkValidId(courseId, 5);
    }

    public static boolean checkValidCourseName(String courseName) throws EmptyInputException{
        // 40�ڸ�, �ѱ�, ����, ���� üũ
        return checkValidName(courseName, 40);
    }

    public static boolean checkValidCourseProfessor(String courseProfessor) throws EmptyInputException{
        // 20�ڸ�, �ѱ�, ����, ���� üũ
        return checkValidName(courseProfessor, 20);
    }

    public static boolean checkValidCourseSemester(String semester){
        // 7�ڸ�, 2022-01, 2022-SM, 2022-02, 2022-WT ���ĸ� ����
        if(!semester.matches("^[0-9][A-Z]*$"))return false;
        if(semester.length() != 7) return false;
        if(semester.charAt(4) != '-') return false;
        String semesterType = semester.substring(5, 7);
        if(ESemesterType.getSemesterList().contains(semesterType)) return false;
        return true;
    }

    public static boolean checkValidMaxCapacity(int maxCapacity){
        // 0 ~ 1000 (TODO: ó���� �� �л����� �޾ƿͼ� maxCapcity�� ���� �����ϸ� �� ��Ȳ�� �°� ���� �� �� �ִ� �ڵ尡 �� �� ����)
        return maxCapacity < 0 || maxCapacity > 1000;
    }

}
