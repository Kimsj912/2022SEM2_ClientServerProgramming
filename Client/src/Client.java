import Exceptions.ServerProcessErrorException;
import Objects.IObject;
import Interfaces.ISubMenuInterface;
import Enums.*;
import Exceptions.NullDataException;
import Interfaces.ServerIF;
import Objects.Course;
import Objects.Student;
import com.sun.media.sound.InvalidDataException;

import javax.naming.LimitExceededException;
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

import static Constants.Constants.*;

// TODO: 2022-10-25
// - Object.Student Delete 기능 Id이외로도 찾아서 삭제할 수 있게 보완
// - Object.Student Update Logic 추가
// - 코드 분리 (유지보수성)
// - exception 보완
    // 1. 서버가 죽었을 때, 클라이언트가 서버에 접속하려고 할 때, 어떻게 처리할 것인가?

// TODO: 교수님께 여쭤볼 것.
// 1. Completed Course의 개수 제한이 있는가? (일단은 최대 10개로 제한)
// 2. 코드 분리  -> 필수로 구현해야하는 내용과 변경해도 되는 내용 여쭤보기 (메뉴들의 위치, 서브 메뉴 등등을 바꿔도 되는가)

public class Client {
    private static final String clientId ="Client1234";
    protected static ServerIF server;
    private final BufferedReader bufferedReader;
    private final Checker<Integer> integerChecker;
    private final Checker<String> stringChecker;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException{
        Client client = new Client();
        client.run();
    }

    public Client () throws MalformedURLException, NotBoundException, RemoteException{
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // Login Token 과정
        try {
            server = (ServerIF) Naming.lookup("Server");
            server.addConnection(clientId);
        } catch (Exception e){
            System.out.println("[Error] Server is not ready.");
            System.exit(0);
        }
        integerChecker = new Checker<>();
        stringChecker = new Checker<>();
    }

    public void run(){
        String choice = "";
        while(true) {
            try{
                // 메뉴 출력
                System.out.println("============== MENU ===========");
                for (EMainMenu menu : EMainMenu.values())
                    System.out.println(menu.ordinal() + ". " + menu.getDescription());
                System.out.println("~~~ Type '--q' whenever you want to quit. ~~~");
                // 메뉴 입력
                if((choice = userInput("Select Menu >> ")).equals("--q")) break;
                // 메뉴 이외의 번호 선택인지 체크
                int choiceNum = Integer.parseInt(choice);
                if(choiceNum<0 || EMainMenu.values().length<=choiceNum) throw new NumberFormatException();
                // 메뉴 선택에 따른 처리
                System.out.printf("======= %s =======\n", EMainMenu.values()[Integer.parseInt(choice)].getDescription().toUpperCase());
                System.out.println(EMainMenu.values()[Integer.parseInt(choice)].getMethod());
                invokeMethod(EMainMenu.values()[Integer.parseInt(choice)].getMethod());
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

    // Print Sub Menu and invoke sub Menu's method
    private <T extends Enum<T> & ISubMenuInterface> void printSubMenu(Class<T> eSubMenu) throws IOException{
        for(T eUpdateCourseMenu : eSubMenu.getEnumConstants()){
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
            System.out.printf("======= %s =======\n", eSubMenu.getEnumConstants()[Integer.parseInt(choice)].getDescription().toUpperCase());
            invokeMethod(eSubMenu.getEnumConstants()[Integer.parseInt(choice)].getMethod());
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

    private void printItem(IObject item){
        try{
            if(item == null) throw new NullDataException(EErrorMessage.NULL_DATA.getMessage());  // checkExistCourseId(courseId)는 여기서 처리된다.
            System.out.println(item);
        } catch (NullDataException e){
            System.out.println(e.getMessage());
        }
    }

    private void printList(ArrayList<?> list, Class<? extends IObject> classObj){

        System.out.printf("======= %s =======", (classObj.getSimpleName()+" List").toUpperCase());
        try {
            if(list.size() == 0)
                throw new NullDataException(ECourse.NOT_FOUND.getMessage());
            for(Object object : list)
                System.out.println(object);
        } catch (NullDataException e) {
            System.out.println(e.getMessage());
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

    public void exit() throws RemoteException{
        server.deleteConnection(clientId);
        System.out.println(" Good Bye \n（；´д｀）");
        System.exit(0);
    }

    // ==================== Methods for Object.Student ====================
    private boolean checkValidStudentId(String studentId){
        return stringChecker.checkEqualLength(studentId, 8) && stringChecker.checkValidNumber(studentId);
    }

    private boolean checkExistStudentId(String studentId) throws IOException, NullDataException{
        return server.getStudentById(studentId) != null;
    }

    // ---------------------- Object.Student Validation ----------------------

    private String getStudentIdWithValidation() throws IOException, NullDataException{
        String studentId;
        while(true) {
            try{
                studentId = userInput(EStudent.studentID.getMessage());
                // 8자리 숫자가 아닐 경우
                if(!checkValidStudentId(studentId))
                    throw new InvalidDataException(EStudent.INVALID_STUDENT_ID.getMessage());
                // 중복 체크
                if(checkExistStudentId(studentId))
                    throw new InvalidDataException(EStudent.DUPLICATED_STUDENT_ID.getMessage());
                break;
            } catch (InvalidDataException e){
                System.out.println(e.getMessage());
            }
        }
        return studentId;
    }

    private String inputLastNameWithValidation() throws IOException {
        String lastName;
        while(true) {
            try{
                lastName = userInput(EStudent.studentLastName.getMessage());
                // 2~40자리 문자가 아닐 경우
                if(!stringChecker.checkIntegerRange(lastName.length(), 2, 40))
                    throw new InvalidDataException(EStudent.INVALID_LAST_NAME.getMessage());
                break;
            } catch (InvalidDataException e){
                System.out.println(e.getMessage());
            }
        }
        return lastName;
    }


    // ------------------- GET STUDENT -------------------
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
        System.out.println("======= SEARCH STUDENT =======");
        for(EGetStudentMenu eGetStudentMenu : EGetStudentMenu.values()){
            System.out.println(eGetStudentMenu.ordinal() + ". " + eGetStudentMenu.getDescription());
        }
        System.out.println("~~~ Type '--q' whenever you want to quit.");
        System.out.println("    Type '--b' whenever you want to back to Main Menu. ~~~");

        // 서브 메뉴 입력
        String choice = userInput("Select Menu >> ");
        // 뒤로가기 처리
        if(choice.equals("--b")) return;
        // 선택한 서브 메뉴에 따라 처리
        try{
            System.out.printf("======= %s =======\n", EGetStudentMenu.values()[Integer.parseInt(choice)].getDescription());
            invokeMethod(EGetStudentMenu.values()[Integer.parseInt(choice)].getMethod());
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

    public Student getStudentById() throws IOException, NullDataException{
        // Input
        String studentId = getStudentIdWithValidation();
        // Check Validation
        Student student = server.getStudentById(studentId);
        // Output
        printItem(student);
        return student;
    }

    public ArrayList<Student> getStudentsByFirstName() throws IOException, NullDataException{
        // Input
        String firstName = userInput(EStudent.studentFirstName.getMessage());
        // Check Validation
        if(!stringChecker.checkValidString(firstName))
            throw new NullDataException(EErrorMessage.NULL_DATA.getMessage());
        if(!stringChecker.checkIntegerRange(firstName.length(), STUDENT_FIRST_NAME_MIN_LENGTH, STUDENT_FIRST_NAME_MAX_LENGTH))
            throw new InvalidDataException(EStudent.INVALID_FIRST_NAME.getMessage());
        // Get Data
        ArrayList<Student> studentList = server.getStudentsByFirstName(firstName);
        // Output
        printList(studentList, Student.class);
        return studentList;
    }

    public ArrayList<Student> getStudentsByLastName() throws IOException, NullDataException{
        // Input
        String lastName = inputLastNameWithValidation();
        // Check Validation
        if(!stringChecker.checkValidString(lastName))
            throw new NullDataException(EErrorMessage.NULL_DATA.getMessage());
        if(!stringChecker.checkIntegerRange(lastName.length(), STUDENT_LAST_NAME_MIN_LENGTH,STUDENT_LAST_NAME_MAX_LENGTH))
            throw new InvalidDataException(EStudent.INVALID_FIRST_NAME.getMessage());
        // Get Data
        ArrayList<Student> studentList = server.getStudentsByLastName(lastName);
        printList(studentList, Student.class);
        return studentList;
    }

    public ArrayList<Student> getStudentsByMajor() throws IOException, NullDataException{
        // Input
        String major = userInput(EStudent.studentMajor.getMessage());
        // Check validation
        if(!EMajor.getMajorList().contains(major)) throw new InvalidDataException(EStudent.INVALID_MAJOR.getMessage());
        // Get data
        ArrayList<Student> studentList = server.getStudentsByMajor(major);
        printList(studentList, Student.class);
        return studentList;
    }

    public ArrayList<Student> getStudentsByCompletedCourse() throws IOException, NullDataException{
        // Input
        String courseId = userInput(ECourse.courseID.getMessage());
        // Check validation
        if(!checkValidCourseId(courseId)) throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
        // Get data
        ArrayList<Student> studentList = server.getStudentsByCompletedCourse(courseId);
        printList(studentList, Student.class);
        return studentList;
    }

    // ------------------- ADD STUDENT -------------------

    public void addStudent() throws IOException, NullDataException{
        // Initialize
        String studentId, lastName, firstName,major;
        ArrayList<String> completedCourseList = new ArrayList<>();

        // Input
        // studentID: 8자리 숫자
        studentId = getStudentIdWithValidation();

        // lastName: 2~40자리 문자
        lastName = inputLastNameWithValidation();


        // firstName: 2~40자리 문자
        while(true) {
            try{
                firstName = userInput(EStudent.studentFirstName.getMessage());
                // 2~40자리 문자가 아닐 경우
                if(!stringChecker.checkIntegerRange(firstName.length(), 2, 40))
                    throw new InvalidDataException(EStudent.INVALID_FIRST_NAME.getMessage());
                break;
            } catch (InvalidDataException e){
                System.out.println(e.getMessage());
            }
        }

        // major: EMajor에 있는 값만 입력 가능
        while(true) {
            try{
                major = userInput(EStudent.studentMajor.getMessage());
                // EMajor에 없는 값일 경우
                if(!EMajor.getMajorList().contains(major))
                    throw new InvalidDataException(EStudent.INVALID_MAJOR.getMessage());
                break;
            } catch (InvalidDataException e){
                System.out.println(e.getMessage());
            }
        }

        // completedCourseList: 0~10개의 ECourse에 있는 값만 입력 가능 (중복 불가)
        String answer = userInput(EStudent.studentCompletedCourses.getMessage());
        while(true) {
            try{
                if(answer.equals("y")) {
                    String courseId = userInput(ECourse.courseID.getMessage());
                    // ECourse에 없는 값일 경우
                    if(!checkValidCourseId(courseId))
                        throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
                    // 중복 체크
                    if(completedCourseList.contains(courseId))
                        throw new InvalidDataException(ECourse.DUPLICATED_COURSE_ID.getMessage());
                    if(completedCourseList.size() > MAX_COMPLETED_COURSES)
                        throw new LimitExceededException(EStudent.INVALID_COMPLETED_COURSES.getMessage());
                    completedCourseList.add(courseId);
                    answer = userInput(EStudent.studentCompletedCourses.getMessage());
                } else if(answer.equals("n")) break;
                else throw new InvalidDataException(EStudent.INVALID_YN_ANSWER.getMessage());
            } catch (InvalidDataException e){
                System.out.println(e.getMessage());
                if(e.getMessage().equals(EStudent.INVALID_YN_ANSWER.getMessage()))
                    answer = userInput(EStudent.studentCompletedCourses.getMessage());
            } catch (LimitExceededException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        Student student = new Student(studentId, lastName,firstName, major, completedCourseList);
        if(server.addStudent(student)) System.out.println(EStudent.ADD_SUCCESS.getMessage());
        else System.out.println(EStudent.ADD_FAIL.getMessage());
    }



    // ------------------- DELETE STUDENT -------------------

    public void deleteStudent() throws IOException {
//        String studentId = userInput(EStudent.studentID.getMessage());
//        boolean result = server.deleteStudent(studentId);
//        if(result) System.out.println(EStudent.DELETE_SUCCESS.getMessage()+studentId);
//        else System.out.println(EStudent.NOT_FOUND.getMessage()+studentId);
    }

    // ------------------- UPDATE STUDENT -------------------
    public void updateStudent() throws IOException{

    }



    // ============================ Methods for Object.Course ============================
    public boolean checkValidCourseId(String courseId) throws IOException{
        // 5자리, 숫자 체크
        if(!integerChecker.checkValidNumber(courseId) || !stringChecker.checkEqualLength(courseId, 5)) {
            System.out.println(ECourse.INVALID_COURSE_ID.getMessage());
            return checkValidCourseId(userInput(ECourse.courseID.getMessage()));
        }
        return true;
    }

    public boolean checkExistCourseId(String courseId) throws IOException, NullDataException{
        // 중복 체크
        return (server.getCourseById(courseId)!=null);
    }


    // ------------------- Object.Course Validation ------------ -------------------
    private String getCourseIdWithValidation() throws IOException, NullDataException{
        String courseId;
        while(true) {
            try{
                courseId = userInput(ECourse.courseID.getMessage());
                // 5자리 숫자가 아닐 경우
                if(!checkValidCourseId(courseId))
                    throw new InvalidDataException(ECourse.INVALID_COURSE_ID.getMessage());
                // 중복 체크
                if(!checkExistCourseId(courseId))
                    throw new InvalidDataException(ECourse.DUPLICATED_COURSE_ID.getMessage());
                break;
            } catch (InvalidDataException e){
                System.out.println(e.getMessage());
            }
        }
        return courseId;
    }
    private String getCourseNameWithValidation() throws IOException{
        try{
            String courseName = userInput(ECourse.courseName.getMessage());
            if((!stringChecker.checkValidString(courseName)
                    || !stringChecker.checkMinLength(courseName, 2)
                    || !stringChecker.checkAlphabetAndNumber(courseName))) {
                throw new InvalidDataException(ECourse.INVALID_COURSE_NAME.getMessage());
            }
            return courseName;
        }catch (InvalidDataException e){
            System.out.println(e.getMessage());
            return getCourseNameWithValidation();
        }
    }

    private String getCourseProfNameWithValidation() throws IOException{
        String courseProfName = userInput(ECourse.courseProfName.getMessage());
        try{
            if((!stringChecker.checkValidString(courseProfName)
                    || !stringChecker.checkMinLength(courseProfName, 2)
                    || !stringChecker.checkAlphabet(courseProfName)))
                throw new InvalidDataException(ECourse.INVALID_COURSE_PROF_NAME.getMessage());
        } catch (InvalidDataException e){
            System.out.println(e.getMessage());
            return getCourseProfNameWithValidation();
        }
        return courseProfName;
    }

    private ArrayList<String> getCoursePreCourseWithValidation() throws IOException, NullDataException{
        try{
            String[] rawPreCourses = userInput(ECourse.preCourses.getMessage()).split(",");
            ArrayList<String> preCourseList = new ArrayList<>(Arrays.asList(rawPreCourses));
            for(String preCourse : preCourseList){
                if(!checkValidCourseId(preCourse)) throw new InvalidDataException(ECourse.INVALID_PRE_COURSE.getMessage());
                if(!checkExistCourseId(preCourse)) throw new InvalidDataException(ECourse.NOT_EXIST_PRE_COURSE.getMessage());
            }
            return preCourseList;
        } catch (InvalidDataException e){
            System.out.println(e.getMessage());
            return getCoursePreCourseWithValidation();
        }
    }


    // -------------------------- GET COURSE ---------------------------------

    public void getAllCourseList() throws RemoteException, NullDataException{
        ArrayList<Course> courseList = server.getAllCoursesData();
        printList(courseList, Course.class);
    }
    public void getCourse() throws IOException{
        printSubMenu(IGetCourseMenu.class);
    }

    public Course getCourseById() throws IOException, NullDataException{
        String courseId = getCourseIdWithValidation();
        Course course = server.getCourseById(courseId);
        printItem(course);
        return course;
    }

    public ArrayList<Course> getCourseByName() throws IOException, NullDataException{
        String courseName = getCourseNameWithValidation();
        ArrayList<Course> courseList = server.getCoursesByName(courseName);
        printList(courseList, Course.class);
        return courseList;
    }

    public ArrayList<Course> getCourseByProfessor() throws IOException, NullDataException{
        String courseProfName = getCourseProfNameWithValidation();
        ArrayList<Course> courseList = server.getCoursesByProfessor(courseProfName);
        printList(courseList, Course.class);
        return courseList;
    }

    public ArrayList<Course> getCourseByPreCourse() throws IOException, NullDataException{
        String coursePreCourse = getCourseIdWithValidation();
        ArrayList<Course> courseList = server.getCoursesByPreCourse(coursePreCourse);
        printList(courseList, Course.class);
        return courseList;
    }



    // -------------------------- ADD COURSE ---------------------------------


    public void addCourse() throws IOException, NullDataException{
        // Initialize
        ArrayList<String> preCourseList;
        // courseId : 5자리 숫자
        String courseId = getCourseIdWithValidation();
        // professor : 2자리 이상의 문자열
        String courseProfName = getCourseProfNameWithValidation();
        // courseName: 2자리 이상의 문자열
        String courseName = getCourseNameWithValidation();
        // coursePreCourse: 5자리 숫자, 쉼표로 구분
        ArrayList<String> coursePreCourse = getCoursePreCourseWithValidation();
        // Create Object.Course Object
        Course course = new Course(courseId, courseProfName, courseName, coursePreCourse);
        // Request to Server
        if(server.addCourse(course)) System.out.println(ECourse.ADD_SUCCESS.getMessage());
        else System.out.println(ECourse.ADD_FAIL.getMessage());
    }



    // ------------------------- DELETE COURSE -------------------------
    public void deleteCourse() throws IOException {
        printSubMenu(IDeleteCourseMenu.class);
    }

    public void deleteCourseById() throws IOException, NullDataException{
        Course course = getCourseById();
        if(course != null) {
            server.deleteCourseById(course.getCourseId());
            System.out.println(ECourse.DELETE_SUCCESS.getMessage());
        }
    }

    public void deleteCourseByName() throws IOException, NullDataException{
        ArrayList<Course> courseList = getCourseByName();
        deleteMultipleCourseFound(courseList);
    }

    public void deleteCourseByProfName() throws IOException, NullDataException{
        ArrayList<Course> courseList = getCourseByProfessor();
        deleteMultipleCourseFound(courseList);
    }

    public void deleteCourseByPreCourse() throws IOException, NullDataException{
        ArrayList<Course> courseList = getCourseByPreCourse();
        deleteMultipleCourseFound(courseList);
    }

    public void deleteMultipleCourseFound(ArrayList<Course> courseList) throws IOException, NullDataException{
        try{
            printList(courseList, Course.class);
            System.out.println("Please select one to delete.");
            String courseId = getCourseIdWithValidation();
            if(!checkExistCourseId(courseId)) throw new NullDataException(ECourse.NOT_FOUND.getMessage());
            if(!server.deleteCourseById(courseId)) throw new ServerProcessErrorException(ECourse.DELETE_FAIL.getMessage());
            System.out.println(ECourse.DELETE_SUCCESS.getMessage());
        } catch (ServerProcessErrorException e){
            System.out.println(e.getMessage());
        }
    }

    // ------------------------------ updateCourse ------------------------------


    public void updateCourse() throws IOException {
        printSubMenu(IUpdateCourseMenu.class);
    }

    public void updateCourseById() throws IOException, NullDataException{
        Course course = getCourseById();
        updateSingleCourseFound(course);
    }

    public void updateCourseByName() throws IOException, NullDataException{
        ArrayList<Course> courseList = getCourseByName();
        updateMultipleCourseFound(courseList);
    }

    public void updateCourseByProfName() throws IOException, NullDataException{
        ArrayList<Course> courseList = getCourseByProfessor();
        updateMultipleCourseFound(courseList);
    }

    public void updateCourseByPreCourse() throws IOException, NullDataException{
        ArrayList<Course> courseList = getCourseByPreCourse();
        updateMultipleCourseFound(courseList);
    }

    public Course updateCourseSelectTarget(Course course) throws IOException, NullDataException{
        System.out.println("What do you want to update?");
        System.out.println("1. Object.Course Name");
        System.out.println("2. Object.Course Professor");
        System.out.println("3. Object.Course PreCourse");
        System.out.println("4. exit(quit without update)");
        String updateChoice = userInput(">> ");
        switch (updateChoice){
            case "1":
                String courseName = getCourseNameWithValidation();
                course.setCourseName(courseName);
                break;
            case "2":
                String courseProfessor = getCourseProfNameWithValidation();
                course.setProfName(courseProfessor);
                break;
            case "3":
                ArrayList<String> coursePreCourse = getCoursePreCourseWithValidation();
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

    public void updateSingleCourseFound(Course course) throws IOException, NullDataException{
        printItem(course);
        String choice = userInput("Is that correct? (y/n) >> ");
        if(choice.toUpperCase().equals("Y")){
            Course newCourse = updateCourseSelectTarget(course);
            server.updateCourse(course, newCourse);
            System.out.println(ECourse.UPDATE_SUCCESS.getMessage());
            return;
        }
        System.out.println(ECourse.UPDATE_FAIL.getMessage());
    }

    public void updateMultipleCourseFound(ArrayList<Course> courseList) throws IOException, NullDataException{
        printList(courseList, Course.class);
        System.out.println("Please select one to update.");
        String courseId = getCourseIdWithValidation();
        for(Course course : courseList){
            if(course.getCourseId().equals(courseId)){
                Course newCourse = updateCourseSelectTarget(course);
                server.updateCourse(course, newCourse);
                System.out.println(ECourse.UPDATE_SUCCESS.getMessage());
                return;
            }
        }
        System.out.println(ECourse.UPDATE_FAIL.getMessage());
    }

    // ==================== Reservation ====================

    public void makeReservation() throws IOException, NullDataException{
        String studentId = getStudentIdWithValidation();
        String courseId = getCourseIdWithValidation();
        server.makeReservation(studentId, courseId);
    }



    // ======================= Invoke Method =====================
    private void invokeMethod(String name) {
        try {
            this.getClass().getMethod(name).invoke(this);
//        } catch (IOException e) {
//            System.out.println("Unexpected value has been inputted.");
//        } catch (NullDataException e) {
//            System.out.println(e.getMessage());
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
                 SecurityException | InvocationTargetException e1){
            e1.printStackTrace();
        }
    }
}
