package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.utils.IrudiKud;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ErroreaKud implements LeihoKudeatzaile {

    private EurobiMain apiNagusia;

    @FXML
    private ImageView irudiContainerEzkerra;

    @FXML
    private ImageView irudiContainerEskuma;

    @FXML
    private Button okBotoia;
    private String bihotza = "eurovisionheart.png";

    //Metodoak
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @FXML
    public void onClick(){

        this.apiNagusia.hasieraErakutsi();
    }

    public void hasieratu(){

        String irudiNagusi = this.apiNagusia.getIrudiNagusia();
        Image irudia = IrudiKud.getInstantzia().irudiaKargatu(irudiNagusi);
        this.irudiContainerEskuma.setImage(irudia);

        Image irudiBihotz = IrudiKud.getInstantzia().irudiaKargatu(irudiNagusi);
        this.irudiContainerEzkerra.setImage(irudiBihotz);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
