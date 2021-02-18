package ru.appline.rencreditFramework.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.rencreditFramework.managers.DriverMngr.*;
import static ru.appline.rencreditFramework.managers.PageMngr.clearAllPages;

public class InitMngr {
    private static final PropMngr props = PropMngr.GetPropMngr();

    public static void initAll() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty("implicitly.wait")), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty("page.load.timeout")), TimeUnit.SECONDS);
    }

    public static void closeAll() {
        clearAllPages();
        closeDriver();
    }
}
