package Handlers;

import Annotations.Setting;
import Backend.Handler;
import Backend.ModuleHandler;
import Annotations.onSettingUpdate;
import java.lang.reflect.Field;

/**
 * Created by TinaSprout on 7/19/16.
 */
public class ChangeValueHandler extends Handler {
    @Override
    public void Handle(Object clz, String[] args, ModuleHandler moduleHandler) {
        for (Field field : clz.getClass().getFields()) {
            if (field.isAnnotationPresent(Setting.class)){
                if (field.getAnnotation(Setting.class).settingname().equalsIgnoreCase(args[0])) {
                    try {
                        switch (field.getType().getName().toLowerCase()) {
                            case "java.lang.string":
                                field.set(clz, args[1]);
                                break;
                            case "boolean":
                                field.setBoolean(clz, Boolean.parseBoolean(args[1]));
                                break;
                            case "float":
                                field.setFloat(clz, Float.parseFloat(args[1]));
                                break;
                            case "int":
                                field.setInt(clz, Integer.parseInt(args[1]));
                                break;
                            case "double":
                                field.setDouble(clz, Double.parseDouble(args[1]));
                                break;
                            case "long":
                                field.setLong(clz, Long.parseLong(args[1]));
                                break;
                            case "short":
                                field.setShort(clz, Short.parseShort(args[1]));
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (args[3] != null){
                    moduleHandler.Handle(moduleHandler.getModuleName(clz), onSettingUpdate.class, args);
                }
            }
        }
    }

}
