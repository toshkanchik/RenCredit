package ru.appline.rencreditFramework.baseClasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.appline.rencreditFramework.managers.DriverMngr;
import ru.appline.rencreditFramework.managers.InitMngr;
import ru.appline.rencreditFramework.managers.PageMngr;
import ru.appline.rencreditFramework.managers.PropMngr;
import ru.appline.rencreditFramework.utils.MyListener;

@ExtendWith(MyListener.class)
public class BaseTestClass {
        protected PageMngr pages = PageMngr.getPageMngr();

        @BeforeEach
        void beforeEach() {
            InitMngr.initAll();
            DriverMngr.getDriver().get(PropMngr.GetPropMngr().getProperty("app.url"));
        }

        @AfterEach
        void after() {
            InitMngr.closeAll();
        }
}
