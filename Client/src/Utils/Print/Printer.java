package Utils.Print;

import Exceptions.EmptyInputException;
import Exceptions.NullDataException;
import Exceptions.ServiceTerminateException;
import Menus.IMenuInterface;
import Objects.IObject;
import Utils.Input.InputValue;

import java.io.IOException;
import java.util.ArrayList;

public class Printer {

    public static <T extends Enum<T> & IMenuInterface> String selectMenu(Class<T> eMenu, String title) throws IOException, ServiceTerminateException, EmptyInputException{
        System.out.printf("\n======= %s =======\n", title.toUpperCase());
        for(T eUpdateCourseMenu : eMenu.getEnumConstants()){
            System.out.println(eUpdateCourseMenu.ordinal() + ". " + eUpdateCourseMenu.getDescription());
        }
        System.out.println("Type '--q' whenever you want to quit.");
        System.out.println("Type '--b' whenever you want to back to Main Menu.");

        System.out.println();
        String choice = InputValue.getInputString("Select Menu: ", true);
        if(choice == null) return null;
        try{
            int choiceNum = Integer.parseInt(choice);
            if(choiceNum<0 || eMenu.getEnumConstants().length<=choiceNum) throw new NumberFormatException();
        }catch (NumberFormatException e){
            System.out.println("Invalid Number of Menu. Please try again.");
            return selectMenu(eMenu, title);
        }
        return eMenu.getEnumConstants()[Integer.parseInt(choice)].getMethod();
    }

    public static void printItem(IObject item) throws NullDataException {
        if(item == null) throw new NullDataException();  // checkExistCourseId(courseId)는 여기서 처리된다.
        System.out.println(item);
    }

    public static void printList(ArrayList<?> list, Class<? extends IObject> classObj) throws NullDataException{
        System.out.printf("======= %s =======\n", (classObj.getSimpleName()+" List").toUpperCase());
        if(list.size() == 0) throw new NullDataException(classObj.getSimpleName()+" is empty.");
        for(Object object : list) System.out.println(":: "+object);
    }
}
