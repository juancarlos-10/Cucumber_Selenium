package seleniumgluecode;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import db.MongoDBHelper;
import org.bson.Document;
import org.junit.Assert;
import utils.LogHelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class test extends TestBase {

    private int start;
    private int left;
    MongoDatabase mongoDataBase = null;
    Document mipcgamer = null;
    private static final Logger LOGGER= LogHelper.getLogger(test.class);



    @Given("^El usuario se encuentra en la pagia Home de imalittletester$")
    public void el_usuario_se_encuentra_en_la_pagia_Home_de_imalittletester() throws Throwable {
        Assert.assertTrue(homePage.homePageIsDisplayed());
    }

    @When("^Hace click sobre el boton The Little Tester Comics$")
    public void hace_click_sobre_el_boton_The_Little_Tester_Comics() throws Throwable {
        homePage.clickOnTitleComics();

    }

    @Then("^Se debe redirigir a la pantalla Comics$")
    public void se_debe_redirigir_a_la_pantalla_Comics() throws Throwable {
        Assert.assertTrue("Nose redirecciono corrrectamente a la pagina de Comics",comicsPage.isTitleComicsDisplayed());

    }



    @Given("^Hay (\\d+) cervezas$")
    public void hayCervezas(int start) throws Throwable {
        this.start = start;
    }

    @When("^Tomo (\\d+)  cervezas$")
    public void tomoCervezas(int drink) throws Throwable {
        this.left =this.start - drink;
    }

    @Then("^Deberian quedar (\\d+) cervezas$")
    public void deberianQuedarCervezas(int left) throws Throwable {
        Assert.assertEquals(left, this.left);
    }


    @Given("^Un blog llamado \"([^\"]*)\" con el siguiente conternido$")
    public void unBlogLlamadoConElSiguienteConternido(String comillasDobles, String contenido) throws Throwable {
        System.out.println(comillasDobles);
        System.out.println(contenido);

    }



    @Given("^Los siguieetes usuarios existentes;$")
    public void losSiguieetesUsuariosExistentes(DataTable table) throws Throwable {
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsWithoutHeading = rows.subList(1,rows.size());

        for (List<String> row : rowsWithoutHeading)
            System.out.println("nombre: " + row.get(0) + "email: " + row.get(1) + "twiter: " + row.get(2));
    }


    //db//
    @Given("^Me he conectado a la Base de Datos mipcgamer$")
    public void meHeConectadoALaBaseDeDatosMiPcgamer() {
        mongoDataBase = MongoDBHelper.getDataBase("mipcgamer");

    }

    @When("^Obtengo la PC de QA Automation$")
    public void obtengoLaPCDeQAAutomation(){
        MongoCollection<Document> collection = MongoDBHelper.getCollectionFromDb(mongoDataBase, "components");
        mipcgamer = MongoDBHelper.getDocumentBykeyValue(collection, "pc", "PC de QA Automation");

    }

    @Then("^Debo obtener los siguientes componentes \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void deboObtenerLosSiguientesComponentes(String mother, String cpu, String firstSlot, String secondSlot){
        String motherDb = (String)MongoDBHelper.getElementByKeyFromDocument(mipcgamer, "mother");
        LOGGER.log(Level.INFO, "Mother: " + motherDb);
        Assert.assertEquals(mother, motherDb);

        String cpuDb = (String)MongoDBHelper.getElementByKeyFromDocument(mipcgamer, "cpu");
        LOGGER.log(Level.INFO, "Cpu: " + cpuDb);
        Assert.assertEquals(cpu, cpuDb);

        Document rams = (Document)MongoDBHelper.getElementByKeyFromDocument(mipcgamer, "memory");
        String firstSlotDb = (String)MongoDBHelper.getElementByKeyFromDocument(rams, "slot1");
        String secondSlotDb = (String)MongoDBHelper.getElementByKeyFromDocument(rams, "slot2");
        LOGGER.log(Level.INFO, "Primer slot: " + firstSlotDb);
        LOGGER.log(Level.INFO, "Segundo slot: " + secondSlotDb);
        Assert.assertEquals(firstSlot, firstSlotDb);
        Assert.assertEquals(secondSlot, secondSlotDb);
    }

}
