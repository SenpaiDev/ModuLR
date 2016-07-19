package Handlers;

import Backend.Handler;
import Annotations.onSettingUpdate;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by TinaSprout on 7/19/16.
 */
public class SettingUpdateHandler extends Handler {
    @Override
    public void Handle(Object clz, String[] args) {
        for (Method method : clz.getClass().getMethods()){
            if(method.isAnnotationPresent(onSettingUpdate.class))
                if (method.getAnnotation(onSettingUpdate.class).settingname().equalsIgnoreCase(args[0])){
                    try {
                        method.invoke(clz);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
