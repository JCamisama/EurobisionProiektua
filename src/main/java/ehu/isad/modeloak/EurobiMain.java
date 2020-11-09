package ehu.isad.modeloak;

import ehu.isad.utils.IrudiKud;
import ehu.isad.utils.Utils;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Properties;

public class EurobiMain extends Application {

    //Atributuak
    private Stage stage;

    private String irudiNagusia;
    private String bihotzIrudiNagusia;
    private String unekoHerrialdea;

    //******Hurrengoak dokumentu batetik atera beharko lirateke, hurrengo hobekuntzean inplementatuko da.
    private String hasieraLeihoa                = "/bistak/hasieraUI.fxml";
    private String herrialdeaHautatuLeihoa      = "/bistak/herrialdeaHautatuUI.fxml";
    private String erroreaLeihoa                = "/bistak/erroreaUI.fxml";
    private String bozkaketaLeihoa              = "/bistak/bozkaketaPanelaUI.fxml";
    private String topHiruLeihoa                = "/bistak/topHiruUI.fxml";

    private String hasieraIzenburu              = "EuroVision";
    private String hautatuIzenburu              = "Hautatzeko Panela";
    private String erroreaIzenburu              = "Bozkaketa Jadanik Burutu Da";
    private String bozkaketaIzenburu            = "Bozkaketa egiteko pantailila";
    private String topHiruIzenburu              = "Top 3";
    //*****************************************************************************************

    private LeihoEzaugarriak hasiera;
    private LeihoEzaugarriak herrialdeaHautatu;
    private LeihoEzaugarriak errorea;
    private LeihoEzaugarriak bozkaketaPanela;
    private LeihoEzaugarriak topHiru;


    //Metodoak

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.irudiNagusiaKargatu();
        this.stage = primaryStage;
        pantailakKargatu();

        this.hasiera.eszenatokiaErakutsi(this.stage);
    }


    private void pantailakKargatu() throws IOException {

        this.herrialdeaHautatu  = new LeihoEzaugarriak(this.hautatuIzenburu, this.herrialdeaHautatuLeihoa, this);
        this.hasiera            = new LeihoEzaugarriak(this.hasieraIzenburu, this.hasieraLeihoa, this);
        this.errorea            = new LeihoEzaugarriak(this.erroreaIzenburu, this.erroreaLeihoa, this);
        this.bozkaketaPanela    = new LeihoEzaugarriak(this.bozkaketaIzenburu, this.bozkaketaLeihoa,this);
        this.topHiru            = new LeihoEzaugarriak(this.topHiruIzenburu, this.topHiruLeihoa, this);
    }


    public void hasieraErakutsi() {
        this.hasiera.eszenatokiaErakutsi(this.stage);
    }

    public void herrialdeaHautatuErakutsi() {
       this.herrialdeaHautatu.eszenatokiaErakutsi(this.stage);
    }

    public void erroreaErakutsi() {
        this.errorea.herrialdeaPasatuKudeatzaileari(this.unekoHerrialdea);
        this.errorea.eszenatokiaErakutsi(this.stage);
    }

    public void bozkaketaPanelaErakutsi() {
        this.bozkaketaPanela.eszenatokiaErakutsi(this.stage);
        this.bozkaketaPanela.herrialdeaPasatuKudeatzaileari(this.unekoHerrialdea);
    }

    public void topHiruErakutsi(){
        this.topHiru.eszenatokiaErakutsi(this.stage);
    }

    private void irudiNagusiaKargatu() {

        Properties ezarpenak =  Utils.lortuEzarpenak();
        this.irudiNagusia       = ezarpenak.getProperty("logoNagusia");
        this.bihotzIrudiNagusia = ezarpenak.getProperty("bihotza");
    }


    public Image getIrudiNagusia(){

        Image irudia = IrudiKud.getInstantzia().irudiaKargatu(this.irudiNagusia);
        return irudia;
    }

    public Image getBihotza(){

        Image irudia = IrudiKud.getInstantzia().irudiaKargatu(this.bihotzIrudiNagusia);
        return irudia;
    }

    public void unekoHerrialdeaEguneratu(String pHerrialdea){
        this.unekoHerrialdea = pHerrialdea;
    }




}
