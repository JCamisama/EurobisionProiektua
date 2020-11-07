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
    private Parent hasieraUI;
    private Parent herrialdeaHautatuUI;
    private Parent erroreaUI;

    private Stage stage;

    private Scene eszenaHasiera;
    private Scene eszenaHerrialdeaHautatu;
    private Scene eszenaErrorea;

    private HasieraKud hasieraKud;
    private HerrialdeaHautatuKud herrialdeaHautatuKud;
    private ErroreaKud erroreaKud;


    private String irudiNagusia;
    private String hasieraLeihoa                = "/bistak/hasiera.fxml";
    private String herrialdeaHautatuLeihoa      = "/bistak/herrialdeaHautatuUI.fxml";
    private String erroreaLeihoa                = "/bistak/erroreaUI.fxml";


    //Metodoak

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.irudiNagusiaKargatu();
        this.stage = primaryStage;
        pantailakKargatu();

        this.stage.setTitle("EuroVision");
        this.eszenaHasiera              = new Scene(this.hasieraUI);
        this.eszenaHerrialdeaHautatu    = new Scene(this.herrialdeaHautatuUI);
        this.eszenaErrorea              = new Scene(this.erroreaUI);

        this.eszenaErakutsi(this.eszenaHasiera);
    }

    private void irudiNagusiaKargatu() {

        Properties ezarpenak =  Utils.lortuEzarpenak();
        this.irudiNagusia = ezarpenak.getProperty("logoNagusia");
    }



    private void pantailakKargatu() throws IOException {

        this.pantailaBakarraKargatu(this.hasieraUI, this.hasieraKud, this.hasieraLeihoa);
        this.pantailaBakarraKargatu(this.herrialdeaHautatuUI, this.herrialdeaHautatuKud, this.herrialdeaHautatuLeihoa);
        this.pantailaBakarraKargatu(this.erroreaUI, this.erroreaKud, this.erroreaLeihoa);

    }

    private void pantailaBakarraKargatu(Parent pUI, LeihoKudeatzaile pKud, String pLeihoa) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pLeihoa));
        pUI  = (Parent) loader.load();
        pKud = loader.getController();
        pKud.initialize(null, null);
        pKud.setMainApp(this);
    }

    public void hasieraErakutsi() {
        this.stage.setTitle("EuroVision");
        this.eszenaErakutsi(this.eszenaHasiera);
    }

    public void herrialdeaHautatuErakutsi() {
        this.stage.setTitle("Hautatzeko Panela");
        this.eszenaErakutsi(this.eszenaHerrialdeaHautatu);
    }

    public void erroreaErakutsi() {
        this.stage.setTitle("Bozkaketa Jadanik Burutu Da");
        this.eszenaErakutsi(this.eszenaErrorea);
    }

    private void eszenaErakutsi(Scene pEszena){
        this.stage.setScene(pEszena);
        this.stage.show();
    }

    public String getIrudiNagusia(){
        return this.irudiNagusia;
    }






}
