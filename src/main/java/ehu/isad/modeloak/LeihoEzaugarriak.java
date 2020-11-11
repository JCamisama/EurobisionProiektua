package ehu.isad.modeloak;

import ehu.isad.LeihoKudeatzaile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LeihoEzaugarriak {

    //Atributuak
    private Parent              leihoUI;
    private Scene               leihoEszena;
    private LeihoKudeatzaile    leihoKud;
    private String              izenburua;

    public LeihoEzaugarriak(String pIzenburua, String pBista, EurobiMain pMain) throws IOException {

        this.izenburua = pIzenburua;
        this.pantailaKargatu(pBista, pMain);
    }

    private void pantailaKargatu(String pBista, EurobiMain pMain) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pBista));
        this.leihoUI  = (Parent) loader.load();
        this.leihoKud = loader.getController();
        this.leihoKud.setMainApp(pMain);
        this.leihoKud.hasieratu();
        this.leihoEszena = new Scene(this.leihoUI);
    }

    public void eszenatokiaErakutsi(Stage pStage) {

        pStage.setTitle(this.izenburua);
        pStage.setScene(this.leihoEszena);
        pStage.show();
    }

    public void herrialdeaPasatuKudeatzaileari(String pHerrialdea){

        this.leihoKud.herrialdeaEguneratu(pHerrialdea);
    }

    public void topHiruEguneratu() {
        this.leihoKud.top3Eguneratu();
    }
}
