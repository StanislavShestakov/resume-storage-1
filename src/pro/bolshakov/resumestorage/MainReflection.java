package pro.bolshakov.resumestorage;

import pro.bolshakov.resumestorage.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume r = new Resume("Name");
        Method methodToString = r.getClass().getMethod("toString");
        System.out.println(methodToString.invoke(r));
    }
}
