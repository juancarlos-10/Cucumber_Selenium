package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import db.MongoDBHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.LogHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cucumber.api.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)


@CucumberOptions(
        features = "src/test/java/features",
        glue = ("seleniumgluecode"),
        plugin = {"json:test/report/cucumber_report.json"},
        snippets = CAMELCASE,
        tags={"@browser"}
)



public class testrunner {
    private static final Logger LOGGER= LogHelper.getLogger(testrunner.class);

    @BeforeClass
    public static void setUp(){
        MongoDBHelper.connectToServer();
    }

    @AfterClass
    public static void teardown(){
        try {
            LOGGER.log(Level.INFO, "Generado reporte");
            String[] cmd ={"cmd.exe", "/c", "npm run report"};
            Runtime.getRuntime().exec(cmd);
            LOGGER.log(Level.INFO, "Reporte Generado satisfactoriamente");
        }catch (Exception ex){
            LOGGER.log(Level.INFO, "Nose puede generar el reporte");
            ex.printStackTrace();
        }
    }

}
