package Utils.Validator;

import Exceptions.EmptyInputException;

public class Validator<T>{

    protected static boolean checkValidId(String id, int length) throws EmptyInputException{
        // 5ÀÚ¸®, ¼ýÀÚ Ã¼Å©
        if(id.trim().equals("")) throw new EmptyInputException();
        if(!id.matches("^[0-9]*$"))return false;
        if(id.length() != length) return false;
        return true;
    }

    protected static boolean checkValidName(String name, int length) throws EmptyInputException{
        if(name.equals("") || name.equals(" ")) throw new EmptyInputException();
        if(name.length() < 1) return false;
        if(name.length() > length) return false;
        if(!name.matches("^[°¡-ÆRa-zA-Z0-9]*$")) return false;
        return true;
    }

}