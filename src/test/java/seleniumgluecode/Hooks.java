package seleniumgluecode;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import db.MongoDBHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import runner.browser_manager.DriverManager;
import runner.browser_manager.DriverManagerFactory;
import runner.browser_manager.DriverType;

import java.util.concurrent.TimeUnit;


public class Hooks {


    private static WebDriver driver;
    private static int numberOfCase = 0;
    private DriverManager driverManager;


    @Before("@browser")

    public void setUp(){
        numberOfCase ++;
        System.out.println("Se esta ejecutando el escenario numero: " + numberOfCase);
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();

        //METODO IMPLICITO: implicitlywailt le dice al programa que encuentre el elemento hasta el segundo 10
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://imalittletester.com/");
        driver.manage().window().maximize();
    }
    @Before("@backend")
    public void connectToMongoServer(){
        MongoDBHelper.connectToServer();
    }


    @After("@browser")
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot)driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
        }
        driverManager.quitDriver();
        System.out.println("El scenario numero: " + numberOfCase + " Se ejecuto correctamente.");
        driverManager.quitDriver();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
