package ehu.isad.modeloak;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.controllers.ui.ErroreaKud;
import ehu.isad.controllers.ui.HasieraKud;
import ehu.isad.controllers.ui.HerrialdeaHautatuKud;
import ehu.isad.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class EurobiMain extends Application {

    //Atributuak
    private Stage stage;

    private String irudiNagusia;
    //******Hurrengoak dokumentu batetik atera beharko lirateke, hurrengo hobekuntza inplementatuko da.
    private String hasieraLeihoa                = "/bistak/hasiera.fxml";
    private String herrialdeaHautatuLeihoa      = "/bistak/herrialdeaHautatuUI.fxml";
    private String erroreaLeihoa                = "/bistak/erroreaUI.fxml";

    private String hasieraIzenburu              = "EuroVision";
    private String hautatuIzenburu              = "Hautatzeko Panela";
    private String erroreaIzenburu              = "Bozkaketa Jadanik Burutu Da";
    //*****************************************************************************************

    private LeihoEzaugarriak hasiera;
    private LeihoEzaugarriak herrialdeaHautatu;
    private LeihoEzaugarriak errorea;


    //Metodoak

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.irudiNagusiaKargatu();
        this.stage = primaryStage;
        pantailakKargatu();

        hasiera.eszenatokiaErakutsi(this.stage);
    }


    private void pantailakKargatu() throws IOException {

        this.herrialdeaHautatu  = new LeihoEzaugarriak(this.hautatuIzenburu, this.herrialdeaHautatuLeihoa, this);
        this.hasiera            = new LeihoEzaugarriak(this.hasieraIzenburu, this.hasieraLeihoa, this);
        this.errorea            = new LeihoEzaugarriak(this.erroreaIzenburu, this.erroreaLeihoa, this);
    }


    public void hasieraErakutsi() {
        this.hasiera.eszenatokiaErakutsi(this.stage);
    }

    public void herrialdeaHautatuErakutsi() {
       this.herrialdeaHautatu.eszenatokiaErakutsi(this.stage);
    }

    public void erroreaErakutsi() {
        this.errorea.eszenatokiaErakutsi(this.stage);
    }

    private void irudiNagusiaKargatu() {

        Properties ezarpenak =  Utils.lortuEzarpenak();
        this.irudiNagusia = ezarpenak.getProperty("logoNagusia");
    }


    public String getIrudiNagusia(){
        return this.irudiNagusia;
    }






}
