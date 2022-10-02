import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class Client {
    public static void main(String[] args){
        ServerIF server;
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            server = (ServerIF) Naming.lookup("Server");
            System.out.println("============== MENU ===========");
            System.out.println("1. List Students");
            System.out.println("2. List Courses");

            String sChoice = objReader.readLine().trim();
            if(sChoice.equals("1")){
                System.out.println("Server's answer : " + server.getAllStudentData());
            } else if(sChoice.equals("2")){
                System.out.println("Server's answer : " + server.getAllCoursesData());
                System.out.println("HomeWork!!");} else {
                System.out.println("HomeWork!!");
            }
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
