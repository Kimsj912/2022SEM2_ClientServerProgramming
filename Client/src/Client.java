import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class Client {
    public static void main(String[] args){
        ServerIF server;
        try{
            server = (ServerIF) Naming.lookup("AddServer");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a string to save : ");
            String str = br.readLine();
            server.save(str);
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
