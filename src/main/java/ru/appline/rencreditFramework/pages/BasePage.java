package ru.appline.rencreditFramework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.rencreditFramework.managers.PageMngr;

import static ru.appline.rencreditFramework.managers.DriverMngr.getDriver;

public class BasePage {
    protected PageMngr pages = PageMngr.getPageMngr();
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void scrollToElementOffsetJs(WebElement element, int offset) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("javascript:window.scrollBy(0, "+ offset +");");
    }
}
