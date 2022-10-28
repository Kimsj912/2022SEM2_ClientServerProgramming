package Utils.Print;

import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import MethodEnums.IMenuInterface;
import Objects.IObject;
import Utils.Input.InputValue;

import java.io.IOException;
import java.util.ArrayList;

public class Printer {

    public static <T extends Enum<T> & IMenuInterface> String selectMenu(Class<T> eMenu, String title) throws IOException, ServiceTerminateException, EmptyInputException{
        System.out.printf("======= %s =======\n", title.toUpperCase());
        for(T eUpdateCourseMenu : eMenu.getEnumConstants()){
            System.out.println(eUpdateCourseMenu.ordinal() + ". " + eUpdateCourseMenu.getDescription());
        }
        System.out.println("Type '--q' whenever you want to quit.");
        System.out.println("Type '--b' whenever you want to back to Main Menu.");

        System.out.println();
        String choice = InputValue.getInputString("Select Menu: ", true);
        if(choice == null) return null;
        int choiceNum = Integer.parseInt(choice);
        if(choiceNum<0 || eMenu.getEnumConstants().length<=choiceNum) throw new NumberFormatException();
        return eMenu.getEnumConstants()[Integer.parseInt(choice)].getMethod();
    }

    public static void printItem(IObject item){
        try{
            if(item == null) throw new NullDataException(ECourse.NOT_FOUND.getMessage());  // checkExistCourseId(courseId)는 여기서 처리된다.
            System.out.println(item);
        } catch (NullDataException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printList(ArrayList<?> list, Class<? extends IObject> classObj){
        System.out.printf("======= %s =======", (classObj.getSimpleName()+" List").toUpperCase());
        try {
            if(list.size() == 0) throw new NullDataException(ECourse.NOT_FOUND.getMessage());
            for(Object object : list) System.out.println(object);
        } catch (NullDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
