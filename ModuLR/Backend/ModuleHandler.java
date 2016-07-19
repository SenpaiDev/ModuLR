package Backend;

import Annotations.Module;
import Backend.Handler;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TinaSprout on 7/19/16.
 */
public class ModuleHandler {
    Map<String, Object> modules = new HashMap<>();
    Map<Object, Handler> Handlers = new HashMap<>();
    public ModuleHandler(String Dir){
        Reflections reflections = new Reflections(Dir);
        for (Class<?> c : reflections.getTypesAnnotatedWith(Module.class)){
            modules.put(c.getAnnotation(Module.class).ModuleName(), c);
        }
    }
    public String getModuleName(Object module) {
        for (String mod1 : modules.keySet()) {
            Object mod = modules.get(mod1);
            if (mod.equals(module))
                return mod1;
        }
        return null;
    }

    public void addAnnotation(Object annotation, Handler handler){
        Handlers.put(annotation, handler);
    }

    public void Handle(String module, Object annotation, String[] args){
        Handlers.get(annotation).Handle(modules.get(module), args);
    }
    public void Handle(String module, Object annotation){
        Handlers.get(annotation).Handle(modules.get(module));
    }
    public void Handle(String module, Object annotation, Object Setting){
        Handlers.get(annotation).Handle(modules.get(module), Setting);
    }
    public void HandleWithCallback(String module, Object annotation, String[] args){
        Handlers.get(annotation).Handle(modules.get(module), args, this);
    }
    public void HandleWithCallBack(String module, Object annotation){
        Handlers.get(annotation).Handle(modules.get(module), this);
    }
    public void HandleWithCallBack(String module, Object annotation, Object Setting){
        Handlers.get(annotation).Handle(modules.get(module), Setting, this);
    }

    /*TODO:
       Modular Hander system so user can make their own annotations

       Ideas:
       Handle module name and the anno with string args
       just handle the anno for stuff like onenable ect
       Hanlde modulename anno with costum Settingsobject the object will thell what to do and its own class
       Add dock for all of this ^
     */
}
