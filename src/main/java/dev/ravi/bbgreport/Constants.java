package dev.ravi.bbgreport;

public interface Constants {

    public static final String LOCAL_CHROMEDRIVER = "C:/Users/kravi/Documents/GitHub/test-automation/resources/chromedriver_win32/chromedriver83.0.4103.39.exe";
    public static final String LOGIN_LINK_XPATH = "/html/body/div/blockquote/a";
    public static final String EMAIL_ID_XPATH = "//*[@id=\"loginId\"]";
    public static final String PASSWORD_XPATH = "//*[@id=\"password\"]";
    public static final String LOGIN_BTN_XPATH = "//*[@id=\"login-submit\"]";
    public static final String NEXT_PAGE_BTN_XPATH = "/html/body/div/button";
    public static final String CELL_ELEMENT = "/html/body/div/table/tbody[$row$]/tr/td[$col$]";
    public static final int EMP_PAGE_ROW_SIZE = 20;
    public static final String PAGE_INFO_DIV_XPATH = "/html/body/div/h4";
    public static final String PAGE_ALL_ROWS = "//tbody/tr";
    public static final int COL_SIZE = 6;
    public static final String PROPERTIES = "src/main/resources/application.properties";
}
