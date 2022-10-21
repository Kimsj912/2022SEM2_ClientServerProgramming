import Enums.*;
import Exceptions.NullDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: 2022-10-25
// - Update Logic 추가
// - 코드 분리 (유지보수성) -> 필수로 구현해야하는 내용과 변경해도 되는 내용 여쭤보기 (메뉴들의 위치, 서브 메뉴 등등)
// - exception 보완

public class Client {
    private static final String clientId ="Client1234";
    protected static ServerIF server;
    private final BufferedReader bufferedReader;
    private Checker<Integer> integerChecker;
    private Checker<String> stringChecker;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException{
        Client client = new Client();
        client.run();
    }

    public Client () throws MalformedURLException, NotBoundException, RemoteException{
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        server = (ServerIF) Naming.lookup("Server");
        // Login Token 과정
        server.addConnection(clientId);

        integerChecker = new Checker<>();
        stringChecker = new Checker<>();
    }

    public void run(){
        String choice = "";
        while(true) {
            try{
                // 메뉴 출력
                System.out.println("============== MENU ===========");
                for (EBasicMenu menu : EBasicMenu.values())
                    System.out.println(menu.ordinal() + ". " + menu.getDescription());
                System.out.println("~~~ Type '--q' whenever you want to quit. ~~~");
                // 메뉴 입력
                choice = userInput("Select Menu >> ");
                // 메뉴 이외의 번호 선택인지 체크
                int choiceNum = Integer.parseInt(choice);
                if(choiceNum<0 || EBasicMenu.values().length<=choiceNum) throw new NumberFormatException();
                // 메뉴 선택에 따른 처리
                System.out.printf("======= %s =======\n", EBasicMenu.values()[Integer.parseInt(choice)].getDescription().toUpperCase());
                invokeMethod(EBasicMenu.values()[Integer.parseInt(choice)].getMethod());
                System.out.println("\n\n");
            } catch (Exception e){
                for(EErrorMessage errorMessage : EErrorMessage.values()){
                    if(errorMessage.getErrorCode() !=null && errorMessage.getErrorCode().equals(e.getClass().getSimpleName())){
                        System.out.println(errorMessage.getMessage());
                        break;
                    }
                }
            }
        }
    }
    // Method for Common
    public String userInput(String message) throws IOException{
        System.out.print(message);
        String input = bufferedReader.readLine().trim();
        // 공백 처리
        if(input.equals("")) {
            System.out.println(EErrorMessage.NO_INPUT.getMessage());
            return userInput(message);
        }
        // --q 입력 시 종료 처리
        if(input.equals("--q"))exit();
        return input;
    }
    public boolean checkValidCourseId(String courseId) throws IOException{
        // 5자리, 숫자 체크
        if(!integerChecker.checkValidNumber(courseId) || !stringChecker.checkEqualLength(courseId, 5)) {
            System.out.println(ECourse.INVALID_ID.getMessage());
            return checkValidCourseId(userInput(ECourse.courseID.getMessage()));
        }
        return true;
    }

    public boolean checkExistCourseId(String courseId) throws IOException{
        // 중복 체크
        return (server.getCourseById(courseId) != null);
    }

    public void exit() throws RemoteException{
        server.deleteConnection(clientId);
        System.out.println(" Good Bye \n（；´д｀）");
        System.exit(0);
    }

    // Method for Student
    public void getAllStudentList() throws RemoteException{
        try{
            ArrayList<Student> studentList = server.getAllStudentData();
            if(studentList.size() == 0) throw new NullDataException(EErrorMessage.NULL_DATA.getMessage());
            System.out.println("======= STUDENT LIST =======");
            for(Student student : studentList)
                System.out.println(student);
        } catch (NullDataException e){
            System.out.println(e.getMessage());
        }
    }
    public void getStudent() throws IOException{
        // TODO: id, first name, last name, major, completed course 에 따라 검색할 수 있게 한다. - getStudentByXXX
        String studentId = userInput(EStudent.studentID.getMessage());
        Student student = server.getStudent(studentId);
        if(student == null) System.out.println(EStudent.NOT_FOUND.getMessage());
        else System.out.println(student);
    }
    public void addStudent() throws IOException{
        String studentId = userInput(EStudent.studentID.getMessage());
        String studentLastName = userInput(EStudent.studentLastName.getMessage());
        String studentFirstName = userInput(EStudent.studentFirstName.getMessage());
        String studentMajor = userInput(EStudent.studentMajor.getMessage());
        ArrayList<String> studentCompletedCourses = new ArrayList<>(Arrays.asList(userInput(EStudent.studentCompletedCourses.getMessage()).split(",")));
        Student student = new Student(studentId, studentLastName+" "+studentFirstName, studentMajor, studentCompletedCourses);
        server.addStudent(student);
    }
    public void deleteStudent() throws IOException {
        String input = userInput(EStudent.studentID.getMessage());
        boolean result = server.deleteStudent(input);
        if(result) System.out.println(EStudent.DELETED.getMessage()+input);
        else System.out.println(EStudent.NOT_FOUND.getMessage()+input);
    }

    // Method for Course
    public void getAllCourseList() throws RemoteException{
        try{
            ArrayList<Course> courseList = server.getAllCoursesData();
            if(courseList.size() == 0) throw new NullDataException(EErrorMessage.NULL_DATA.getMessage());
            System.out.println("======= COURSE LIST =======");
            for(Course course : courseList)
                System.out.println(course);
        } catch (NullDataException e){
            System.out.println(e.getMessage());
        }
    }
    public void getCourse() throws IOException{
        // TODO: id, name, professor, preCourse에 따라 검색할 수 있게 한다. - getCourseByXXX
        System.out.println("======= SEARCH COURSE =======");
        for(EGetCourseMenu eGetCourseMenu : EGetCourseMenu.values()){
            System.out.println(eGetCourseMenu.ordinal() + ". " + eGetCourseMenu.getDescription());
        }
        System.out.println("~~~ Type '--q' whenever you want to quit.");
        System.out.println("    Type '--b' whenever you want to back to Main Menu. ~~~");

        String choice = userInput("Select Menu : ");
        // 서브 메뉴 입력
        choice = userInput("Select Menu >> ");
        // 뒤로가기 처리
        if(choice.equals("--b")) return;
        // 선택한 서브 메뉴에 따라 처리
        try{
            System.out.printf("======= %s =======\n", EGetCourseMenu.values()[Integer.parseInt(choice)].getDescription().toUpperCase());
            invokeMethod(EGetCourseMenu.values()[Integer.parseInt(choice)].getMethod());
            System.out.println("\n\n");
        } catch (Exception e){
            for(EErrorMessage errorMessage : EErrorMessage.values()){
                if(errorMessage.getErrorCode() !=null && errorMessage.getErrorCode().equals(e.getClass().getSimpleName())){
                    System.out.println(errorMessage.getMessage());
                    break;
                }
            }
        }
    }

    public Course getCourseById() throws IOException{
        String courseId = userInput(ECourse.courseID.getMessage());
        Course course = server.getCourseById(courseId);
        if(course != null) System.out.println(course);
        else System.out.println(ECourse.NOT_FOUND.getMessage());
        return course;
    }

    public ArrayList<Course> getCourseByName() throws IOException{
        String courseName = userInput(ECourse.courseName.getMessage());
        ArrayList<Course> courseList = server.getCoursesByName(courseName);
        if(courseList.size() == 0) System.out.println(ECourse.NOT_FOUND.getMessage());
        else {
            System.out.println("======= COURSE LIST =======");
            for(Course course : courseList)
                System.out.println(course);
        }
        return courseList;
    }

    public ArrayList<Course> getCourseByProfessor() throws IOException{
        String courseProfessor = userInput(ECourse.courseProfName.getMessage());
        ArrayList<Course> courseList = server.getCoursesByProfessor(courseProfessor);
        if(courseList.size() == 0) System.out.println(ECourse.NOT_FOUND.getMessage());
        else {
            System.out.println("======= COURSE LIST =======");
            for(Course course : courseList)
                System.out.println(course);
        }
        return courseList;
    }

    public ArrayList<Course> getCourseByPreCourse() throws IOException{
        String coursePreCourse = userInput(ECourse.preCourses.getMessage());
        ArrayList<Course> courseList = server.getCoursesByPreCourse(coursePreCourse);
        if(courseList.size() == 0) System.out.println(ECourse.NOT_FOUND.getMessage());
        else {
            System.out.println("======= COURSE LIST =======");
            for(Course course : courseList)
                System.out.println(course);
        }
        return courseList;
    }

    // -------------------------- ADD COURSE ---------------------------------
    public void addCourse() throws IOException{
        // Initialize
        String courseId, courseProfName, courseName;
        ArrayList<String> preCourseList = new ArrayList<>();

        // courseId : 5자리 숫자
        while(true) {
            courseId = userInput(ECourse.courseID.getMessage());
            // 5자리 숫자가 아닐 경우
            if(!checkValidCourseId(courseId)) {
                System.out.println(ECourse.INVALID_ID.getMessage());
                continue;
            }
            // 중복 체크
            if(checkExistCourseId(courseId)) {
                System.out.println(ECourse.DUPLICATED_ID.getMessage());
                continue;
            }
            break;
        }

        // professor : 2자리 이상의 문자열
        while(true) {
            courseProfName = userInput(ECourse.courseProfName.getMessage());
            if((!stringChecker.checkValidString(courseProfName)
                    || !stringChecker.checkMinLength(courseProfName, 2)
                    || !stringChecker.checkAlphabet(courseProfName))) {
                System.out.println(ECourse.INVALID_PROF.getMessage());
                continue;
            } break;
        }
        // courseName: 2자리 이상의 문자열
        while (true) {
            courseName = userInput(ECourse.courseName.getMessage());
            if((!stringChecker.checkValidString(courseName)
                    || !stringChecker.checkMinLength(courseName, 2)
                    || !stringChecker.checkAlphabetAndNumber(courseName))) {
                System.out.println(ECourse.INVALID_NAME.getMessage());
                continue;
            } break;
        }
        // coursePreCourse: 5자리 숫자, 쉼표로 구분
        boolean isPreCourseValid = true;
        while(isPreCourseValid){
            String[] rawPreCourses = userInput(ECourse.preCourses.getMessage()).split(",");
            preCourseList = new ArrayList<>(Arrays.asList(rawPreCourses));
            for(String preCourse : rawPreCourses){
                if(!checkValidCourseId(preCourse)) {
                    System.out.println(ECourse.INVALID_PRE_COURSE.getMessage());
                    isPreCourseValid = false;
                } else if(!checkExistCourseId(preCourse)) {
                    System.out.println(ECourse.NOT_EXIST_PRE_COURSE.getMessage());
                    isPreCourseValid = false;
                } else{
                    isPreCourseValid = true;
                }
            }
        }
        Course course = new Course(courseId, courseProfName, courseName, preCourseList);
        server.addCourse(course);
    }


    // ------------------------- DELETE COURSE -------------------------
    public void deleteCourse() throws IOException {
        System.out.println("======= DELETE COURSE =======");
        for(EDeleteCourseMenu eDeleteCourseMenu : EDeleteCourseMenu.values()){
            System.out.println(eDeleteCourseMenu.ordinal() + ". " + eDeleteCourseMenu.getDescription());
        }
        System.out.println("~~~ Type '--q' whenever you want to quit.");
        System.out.println("    Type '--b' whenever you want to back to Main Menu. ~~~");

        // 서브 메뉴 입력
        String choice = userInput("Select Menu >> ");
        // 뒤로가기 처리
        if(choice.equals("--b")) return;
        // 선택한 서브 메뉴에 따라 처리
        try{
            System.out.printf("======= %s =======\n", EDeleteCourseMenu.values()[Integer.parseInt(choice)].getDescription().toUpperCase());
            invokeMethod(EDeleteCourseMenu.values()[Integer.parseInt(choice)].getMethod());
            System.out.println("\n\n");
        } catch (Exception e){
            for(EErrorMessage errorMessage : EErrorMessage.values()){
                if(errorMessage.getErrorCode() !=null && errorMessage.getErrorCode().equals(e.getClass().getSimpleName())){
                    System.out.println(errorMessage.getMessage());
                    break;
                }
            }
        }

    }

    public void deleteCourseById() throws IOException{
        Course course = getCourseById();
        if(course != null) {
            server.deleteCourse(course);
            System.out.println(ECourse.DELETED.getMessage());
        }
    }

    public void deleteCourseByName() throws IOException{
        ArrayList<Course> courseList = getCourseByName();
        deleteMultipleCourseFound(courseList);
    }

    public void deleteCourseByProfName() throws IOException{
        ArrayList<Course> courseList = getCourseByProfessor();
        deleteMultipleCourseFound(courseList);
    }

    public void deleteCourseByPreCourse() throws IOException{
        ArrayList<Course> courseList = getCourseByPreCourse();
        deleteMultipleCourseFound(courseList);
    }

    public void deleteMultipleCourseFound(ArrayList<Course> courseList) throws IOException{
        if(courseList.size() == 0) System.out.println(ECourse.NOT_FOUND.getMessage());
        else if(courseList.size() == 1) {
            server.deleteCourse(courseList.get(0));
            System.out.println(ECourse.DELETED.getMessage());
        }
        else {
            System.out.println("Multiple courses found. Please select one to delete.");
            String courseId = userInput(ECourse.courseID.getMessage());
            if(checkValidCourseId(courseId)) {
                Course course = server.getCourseById(courseId);
                if(course != null) {
                    server.deleteCourse(course);
                    System.out.println(ECourse.DELETED.getMessage());
                }
            } else System.out.println(ECourse.INVALID_ID.getMessage());
            System.out.println("if you wanna delete another course, please enter 'y'.");
            String choice = userInput(">> ");
            if(choice.equals("y") || choice.equals("Y")) deleteCourse();
        }
    }

    // ------------------------------ updateCourse ------------------------------
    public void updateCourse() throws IOException {
        System.out.println("======= UPDATE COURSE =======");
        for(EUpdateCourseMenu eUpdateCourseMenu : EUpdateCourseMenu.values()){
            System.out.println(eUpdateCourseMenu.ordinal() + ". " + eUpdateCourseMenu.getDescription());
        }
        System.out.println("~~~ Type '--q' whenever you want to quit.");
        System.out.println("    Type '--b' whenever you want to back to Main Menu. ~~~");

        // 서브 메뉴 입력
        String choice = userInput("Select Menu >> ");
        // 뒤로가기 처리
        if(choice.equals("--b")) return;
        // 선택한 서브 메뉴에 따라 처리
        try{
            System.out.printf("======= %s =======\n", EUpdateCourseMenu.values()[Integer.parseInt(choice)].getDescription().toUpperCase());
            invokeMethod(EUpdateCourseMenu.values()[Integer.parseInt(choice)].getMethod());
            System.out.println("\n\n");
        } catch (Exception e){
            for(EErrorMessage errorMessage : EErrorMessage.values()){
                if(errorMessage.getErrorCode() !=null && errorMessage.getErrorCode().equals(e.getClass().getSimpleName())){
                    System.out.println(errorMessage.getMessage());
                    break;
                }
            }
        }
    }

    public void updateCourseById() throws IOException{
        Course course = getCourseById();
        updateSingleCourseFound(course);
    }

    public void updateCourseByName() throws IOException{
        ArrayList<Course> courseList = getCourseByName();
        updateMultipleCourseFound(courseList);
    }

    public void updateCourseByProfName() throws IOException{
        ArrayList<Course> courseList = getCourseByProfessor();
        updateMultipleCourseFound(courseList);
    }

    public void updateCourseByPreCourse() throws IOException{
        ArrayList<Course> courseList = getCourseByPreCourse();
        updateMultipleCourseFound(courseList);
    }

    public Course updateCourseSelectTarget(Course course) throws IOException {
        System.out.println("What do you want to update?");
        System.out.println("1. Course Name");
        System.out.println("2. Course Professor");
        System.out.println("3. Course PreCourse");
        System.out.println("4. exit");
        String updateChoice = userInput(">> ");
        switch (updateChoice){
            case "1":
                String courseName = userInput(ECourse.courseName.getMessage());
                course.setCourseName(courseName);
                break;
            case "2":
                String courseProfessor = userInput(ECourse.courseProfName.getMessage());
                course.setProfName(courseProfessor);
                break;
            case "3":
                String coursePreCourseStr = userInput(ECourse.preCourses.getMessage());
                String[] coursePreCourseArr = coursePreCourseStr.trim().split(",");
                ArrayList<String> coursePreCourse = new ArrayList<>();
                for(String preCourse : coursePreCourseArr){
                    if(checkValidCourseId(preCourse)) coursePreCourse.add(preCourse);
                    else System.out.println(ECourse.INVALID_ID.getMessage());
                }
                course.setPreCourse(coursePreCourse);
                break;
            case "4":
                return course;
            default:
                System.out.println("Invalid Input");
                break;
        }
        System.out.println("if you wanna update another item, please enter 'y'.");
        String updateMore = userInput(">> ");
        if(updateMore.equals("Y") || updateMore.equals("y")){
            return updateCourseSelectTarget(course);
        }
        return course;
    }

    public void updateSingleCourseFound(Course course) throws IOException{
        System.out.println("Is that correct? (y/n)");
        String choice = userInput(">> ");
        choice = choice.toUpperCase();
        if(choice.equals("Y")){
            Course newCourse = updateCourseSelectTarget(course);
            server.updateCourse(course, newCourse);
        } else {
            System.out.println("Update Cancelled");
        }
    }

    public void updateMultipleCourseFound(ArrayList<Course> courseList) throws IOException{
        if(courseList == null || courseList.size() == 0) return;
        else if(courseList.size() == 1){
            Course course = courseList.get(0);
            updateSingleCourseFound(course);
        } else {
            System.out.println("Multiple courses found. Please select one to update.");
            for (int i = 0; i < courseList.size(); i++) {
                System.out.println(i + ". " + courseList.get(i).getCourseName());
            }
            String choice = userInput(">> ");
            try {
                if (!integerChecker.checkValidNumber(choice)) throw new NumberFormatException();
                if (!integerChecker.checkIntegerRange(Integer.parseInt(choice), 0, courseList.size()))
                    throw new IndexOutOfBoundsException();
                int index = Integer.parseInt(choice);
                Course course = courseList.get(index);
                updateSingleCourseFound(course);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(EErrorMessage.WRONG_INPUT_NUMBER.getMessage());
            }
        }
    }





    // Method for Invoke Methods
    private void invokeMethod(String name) {
        try {
            this.getClass().getMethod(name).invoke(this);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
                 SecurityException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }
}
