package ru.appline.rencreditFramework.managers;


import ru.appline.rencreditFramework.pages.DepositPage;
import ru.appline.rencreditFramework.pages.StartPage;

public class PageMngr {
    private static PageMngr pageMngr;
    private static StartPage startPage;
    private static DepositPage depositPage;

    private PageMngr(){}

    public static PageMngr getPageMngr(){
        if(pageMngr == null){
            pageMngr = new PageMngr();
        }
        return pageMngr;
    }

    public static void clearAllPages(){
        startPage = null;
        depositPage = null;
//        pageMngr = null;
    }

    public DepositPage getDepositPage() {
        if(depositPage == null) depositPage = new DepositPage();
        return depositPage;
    }

    public StartPage getStartPage(){
        if(startPage == null) startPage = new StartPage();
        return startPage;
    }
}
