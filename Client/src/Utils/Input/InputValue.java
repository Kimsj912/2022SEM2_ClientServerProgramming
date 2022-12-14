package Utils.Input;

import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputValue {
    static BufferedReader br;
    public static String getInput(String message) throws IOException, EmptyInputException, ServiceTerminateException{
        br = new BufferedReader(new InputStreamReader(System.in));
        if (message != null) System.out.print(message);
        String input = br.readLine();
        if (input.trim().equals("")) throw new EmptyInputException();
        if (input.equals("--q")) throw new ServiceTerminateException();
        return input;
    }

    public static String getInputString(String message, boolean canBack) throws IOException, ServiceTerminateException, EmptyInputException {
        String input = getInput(message);
        if (canBack && input.equals("--b")) return null;
        return input;
    }

    public static int getInputInteger(String message, boolean canBack) throws IOException, ServiceTerminateException, EmptyInputException, NumberFormatException {
        String input = getInput(message);
        if (canBack && input.equals("--b")) return -1;
        if(!input.matches("^[0-9]*$")) throw new NumberFormatException("Invalid Number Format. Please try again.");
        return Integer.parseInt(input);
    }
}
