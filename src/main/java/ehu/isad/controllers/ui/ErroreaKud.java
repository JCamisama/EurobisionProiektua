package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.modeloak.EurobiMain;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ErroreaKud implements LeihoKudeatzaile {

    private EurobiMain apiNagusia;

    @FXML private ImageView irudiContainerEzkerra;

    @FXML private ImageView irudiContainerEskuma;

    @FXML private Text herrialdeText;

    //Metodoak
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @FXML
    public void onClick(){

        this.apiNagusia.hasieraErakutsi();
    }

    public void hasieratu(){

        this.irudiContainerEskuma.setImage(this.apiNagusia.getIrudiNagusia());
        this.irudiContainerEzkerra.setImage(this.apiNagusia.getBihotza());
    }

    public void herrialdeaEguneratu(String pHerrialde){
        this.herrialdeText.setText(pHerrialde);
    }

    @Override
    public void top3Eguneratu() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
