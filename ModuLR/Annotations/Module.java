package Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by TinaSprout on 7/19/16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
    String ModuleName();
    ModuleType MODULE_TYPE() default ModuleType.All;
}
enum ModuleType{
    Toggleble,
    Runable,
    All
}
