package Utils.Input;

import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputValue {
    static BufferedReader br;

    public InputValue(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String getInputString(String message, boolean canBack) throws IOException, ServiceTerminateException, EmptyInputException {
        br = new BufferedReader(new InputStreamReader(System.in));
        if (message != null) System.out.println(message);
        String input = br.readLine();
        if (input.trim().equals("")) throw new EmptyInputException();
        if (input.equals("--q")) throw new ServiceTerminateException();
        if (canBack && input.equals("--b")) return null;
        return input;
    }

    public static int getInputInteger(String message, boolean canBack) throws IOException, ServiceTerminateException, EmptyInputException {
        if (message != null) System.out.println(message);
        String input = br.readLine();
        if (input.trim().equals("")) throw new EmptyInputException();
        if (input.equals("--q")) throw new ServiceTerminateException();
        if (canBack && input.equals("--b")) return -1;
        if(!input.matches("[0-9]")) throw new NumberFormatException();
        return Integer.parseInt(input);
    }
}
