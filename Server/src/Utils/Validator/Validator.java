package Utils.Validator;

import Exceptions.EmptyInputException;
import com.sun.media.sound.InvalidDataException;

public class Validator<T>{

    protected static String checkValidId(String input, int length) throws EmptyInputException, InvalidDataException{
        // 5자리, 숫자 체크
        String id = input.trim();
        if(id.trim().equals("")) throw new EmptyInputException();
        if(!id.matches("^[0-9]*$") || id.length() != length) throw new InvalidDataException();
        return id;
    }

    protected static String checkValidName(String input, int length) throws EmptyInputException, InvalidDataException{
        if(input.trim().equals("")) throw new EmptyInputException();
        String name = input.replace(" ", "_");
        String regex = "^[a-zA-Z0-9_]{1," + length + "}$";
        if(!name.matches(regex)) throw new InvalidDataException("Name must be less than " + length + " characters and only contain letters, numbers, and underscores.");
        return name;
    }
}