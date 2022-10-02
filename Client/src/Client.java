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

public class Client {
    private static final String clientId ="Client1234";
    protected static ServerIF server;
    private BufferedReader objReader;

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
        objReader = new BufferedReader(new InputStreamReader(System.in));
        server = (ServerIF) Naming.lookup("Server");
        // Login Token °úÁ¤
        server.addConnection(clientId);
    }

    public void run(){
        try{
            String sChoice = "";
            while(true) {
                printMenu();
                sChoice = objReader.readLine().trim();
                invokeMethod(EMenu.values()[Integer.parseInt(sChoice)].getMethod());
                System.out.println("\n\n");
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Error is occurred.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }



    public void printMenu(){
        System.out.println("============== MENU ===========");
        for (EMenu menu : EMenu.values()) {
            System.out.println(menu.ordinal() + ". " + menu.getDescription());
        }
    }
    public void getAllCourseList() throws RemoteException{
        System.out.println("============== COURSE LIST ===========");
        server.getAllCoursesData().forEach(System.out::println);
    }

    public void getAllStudentList() throws RemoteException{
        System.out.println("============== STUDENT LIST ===========");
        server.getAllStudentData().forEach(System.out::println);
    }

    public void getStudent() throws IOException{
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============== FIND STUDENT ===========");
        System.out.println("Enter Student ID");
        String sStudentID = objReader.readLine().trim();
        Student student = server.getStudent(sStudentID);
        if(student == null){
            System.out.println("Student not found");
        } else {
            System.out.println(student);
        }
    }

    public void getCourse() throws IOException{
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============== FIND COURSE ===========");
        System.out.println("Enter Course ID");
        String sCourseID = objReader.readLine().trim();
        Course course = server.getCourse(sCourseID);
        if(course != null){
            System.out.println(course);
        } else{
            System.out.println("Course not found");
        }
    }

    public void addStudent() throws IOException{
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============== ADD STUDENT ===========");
        System.out.println("Enter Student ID");
        String sStudentID = objReader.readLine().trim();
        System.out.println("Enter Student Last Name");
        String sStudentLastName = objReader.readLine().trim();
        System.out.println("Enter Student First Name");
        String sStudentFirstName = objReader.readLine().trim();
        System.out.println("Enter Student Major");
        String sStudentMajor = objReader.readLine().trim();
        System.out.println("Enter completedCourses (separated by comma)");
        String sStudentCompletedCourses = objReader.readLine().trim();
        String[] sStudentCompletedCoursesArray = sStudentCompletedCourses.split(",");
        ArrayList<String> studentCompletedCourses = new ArrayList<>(Arrays.asList(sStudentCompletedCoursesArray));

        Student student = new Student(sStudentID, sStudentLastName+" "+sStudentFirstName, sStudentMajor, studentCompletedCourses);
        server.addStudent(student);
    }

    public void addCourse() throws IOException {
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============== ADD COURSE ===========");
        System.out.println("Enter Course ID");
        String sCourseID = objReader.readLine().trim();
        System.out.println("Enter Professor Last Name");
        String sProfName = objReader.readLine().trim();
        System.out.println("Enter Course Name");
        String sCourseName = objReader.readLine().trim();
        System.out.println("Enter preCourses (separated by comma)");
        String[] sPreCourseArr = objReader.readLine().trim().split(",");
        ArrayList<String> sPreCourseArrList = new ArrayList<>(Arrays.asList(sPreCourseArr));

        Course course = new Course(sCourseID, sCourseName, sProfName, sPreCourseArrList);
        server.addCourse(course);
    }

    public void deleteStudent() throws IOException {
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============== DELETE STUDENT ===========");
        System.out.println("Enter Student ID");
        String sStudentID = objReader.readLine().trim();
        server.deleteStudent(sStudentID);
    }

    public void deleteCourse() throws IOException {
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============== DELETE COURSE ===========");
        System.out.println("Enter Course ID");
        String sCourseID = objReader.readLine().trim();
        server.deleteCourse(sCourseID);
    }

    public void exit() throws RemoteException{
        server.deleteConnection(clientId);
        System.out.println("============== EXIT ===========");
        System.out.println(" Good Bye ");
        System.out.println("£¨£»¢¥¬Õ£à£©");

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
