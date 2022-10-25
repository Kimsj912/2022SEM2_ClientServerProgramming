import java.util.ArrayList;

public class Checker<T>{
    /**
     * ArrayList, String[], int[], char[], String are supported
     **/
    public boolean checkEqualLength(T array, int length){
        if(array instanceof ArrayList){
            return ((ArrayList<?>) array).size() == length;
        }else if(array instanceof String[]){
            return ((String[]) array).length == length;
        }else if(array instanceof int[]){
            return ((int[]) array).length == length;
        }else if(array instanceof char[]){
            return ((char[]) array).length == length;
        }else if(array instanceof String){
            return ((String) array).length() == length;
        }
        return false;
    }
    /**
     * ArrayList, String[], int[], char[], String are supported
     **/
    public boolean checkMinLength(T array, int length){
        if(array instanceof ArrayList){
            return ((ArrayList<?>) array).size() > length;
        }else if(array instanceof String[]){
            return ((String[]) array).length > length;
        }else if(array instanceof int[]){
            return ((int[]) array).length > length;
        }else if(array instanceof char[]){
            return ((char[]) array).length > length;
        }else if(array instanceof String){
            return ((String) array).length() > length;
        }
        return false;
    }
    /**
     * ArrayList, String[], int[], char[], String are supported
     **/
    public boolean checkMaxLength(T array, int length){
        if(array instanceof ArrayList){
            return ((ArrayList<?>) array).size() < length;
        }else if(array instanceof String[]){
            return ((String[]) array).length < length;
        }else if(array instanceof int[]){
            return ((int[]) array).length < length;
        }else if(array instanceof char[]){
            return ((char[]) array).length < length;
        }else if(array instanceof String){
            return ((String) array).length() < length;
        }
        return false;
    }
    // Check Valid Number
    public boolean checkIntegerRange(int value, int min, int max){
        return value >= min && value <= max;
    }
    public boolean checkValidNumber(String str){
        if(str == null || str.length() == 0){
            return false;
        }
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    // Check Valid String
    public boolean checkValidString(String str){
        return str.equals("") || str.equals(" ");
    }
    public boolean checkAlphabet(String str){
        return str.matches("^[a-zA-Z]*$");
    }
    public boolean checkAlphabetAndNumber(String str){
        return str.matches("^[a-zA-Z0-9]*$");
    }

}