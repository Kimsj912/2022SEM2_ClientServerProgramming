import Exceptions.EmptyInputException;
import Exceptions.ServiceTerminateException;
import Menus.IMenuInterface;
import Utils.Print.Printer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CommonClient {

    protected  <T extends Enum<T> & IMenuInterface> void selectMenu
            (Class<T> eSubMenu,
             String title,
             Class<? extends CommonClient> invokeClass,
             Object obj
            )
            throws IOException, ServiceTerminateException, EmptyInputException{
        // 메뉴 출력
        String method = Printer.selectMenu(eSubMenu, title == null ? eSubMenu.getSimpleName() : title);
        if(method == null) return;
        invokeMethod(invokeClass, method, obj);
    }


    protected void invokeMethod(Class<?> invokeClass, String name, Object obj)
            throws IOException, EmptyInputException, ServiceTerminateException{
        try {
            invokeClass.getMethod(name).invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
                 SecurityException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

}
