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

// exception 처리 추가
// 유지보수성 추가
public class Client {
    private static final String clientId ="Client1234";
    protected static ServerIF server;
    private BufferedReader bufferedReader;

    public enum EMenu {
        GET_ALL_COURSE("getAllCourseList", "Get all courses"),
        GET_COURSE("getCourse", "Get course"),
        ADD_COURSE("addCourse", "Add course"),
        DELETE_COURSE("deleteCourse", "Delete course"),
        GET_ALL_STUDENT("getAllStudentList", "Get all students"),
        GET_STUDENT("getStudent", "Get student"),
        ADD_STUDENT("addStudent", "Add student"),
        DELETE_STUDENT("deleteStudent", "Delete student"),
        EXIT("exit", "Program exit");


        // Variables
        public String method;
        private String description;

        // Getters & Setters
        public String getMethod(){return method;}
        public String getDescription(){return description;}
        public void setMethod(String method){this.method = method;}
        public void setDescription(String description){this.description = description;}


        EMenu(String method, String description){
            this.method = method;
            this.description = description;
        }

    }


    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException{
        Client client = new Client();
        client.run();
    }

    public Client () throws MalformedURLException, NotBoundException, RemoteException{
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        server = (ServerIF) Naming.lookup("Server");
        // Login Token 과정
        server.addConnection(clientId);
    }

    public void run(){
        try{
            String choice = "";
            while(true) {
                printMenu();
                choice = bufferedReader.readLine().trim();
                System.out.printf("======= %s =======",EMenu.values()[Integer.parseInt(choice)].getDescription().toUpperCase());
                invokeMethod(EMenu.values()[Integer.parseInt(choice)].getMethod());
                System.out.println("\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Error is occurred.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public String userInput(String message) throws IOException{
        System.out.print(message);
        return bufferedReader.readLine().trim();
    }
    public void printMenu(){
        System.out.println("============== MENU ===========");
        for (EMenu menu : EMenu.values()) {
            System.out.println(menu.ordinal() + ". " + menu.getDescription());
        }
    }
    public void getAllCourseList() throws RemoteException{server.getAllCoursesData().forEach(System.out::println);}
    public void getAllStudentList() throws RemoteException{server.getAllStudentData().forEach(System.out::println);}
    public void getStudent() throws IOException{
        String studentId = userInput("Enter student ID");
        Student student = server.getStudent(studentId);
        if(student == null) System.out.println("Student not found");
        else System.out.println(student);
    }

    public void getCourse() throws IOException{
        String courseId = userInput("Enter course ID");
        Course course = server.getCourse(courseId);
        if(course != null) System.out.println(course);
        else System.out.println("Course not found");
    }


    public void addStudent() throws IOException{
        String studentId = userInput("Enter student ID");
        String studentLastName = userInput("Enter student Last name");
        String studentFirstName = userInput("Enter student First name");
        String studentMajor = userInput("Enter student Major");
        ArrayList<String> studentCompletedCourses = new ArrayList<>(Arrays.asList(userInput("Enter completedCourses (separated by comma)").split(",")));
        Student student = new Student(studentId, studentLastName+" "+studentFirstName, studentMajor, studentCompletedCourses);
        server.addStudent(student);
    }

    public void addCourse() throws IOException{
        String courseId = userInput("Enter course ID");
        String courseProfName = userInput("Enter professor Last Name");
        String courseName = userInput("Enter course name");
        ArrayList<String> sPreCourseArrList = new ArrayList<>(Arrays.asList(userInput("Enter preCourses (separated by comma)").split(",")));
        Course course = new Course(courseId, courseProfName, courseName, sPreCourseArrList);
        server.addCourse(course);
    }
    public void deleteStudent() throws IOException {server.deleteStudent(userInput("Enter Student ID"));}
    public void deleteCourse() throws IOException {server.deleteCourse(userInput("Enter Course ID"));}

    public void exit() throws RemoteException{
        server.deleteConnection(clientId);
        System.out.println(" Good Bye \n（；´д｀）");
        System.exit(0);
    }

    private void invokeMethod(String name) {
        try {
            this.getClass().getMethod(name).invoke(this);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
                 SecurityException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }
}
