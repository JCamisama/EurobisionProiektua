package ehu.isad.controllers.ui;



import ehu.isad.LeihoKudeatzaile;
import ehu.isad.modeloak.App;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.utils.IrudiKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements LeihoKudeatzaile {

    //Atributuak
    private EurobiMain apiNagusia;
    @FXML
    private ImageView irudiKontainerra;

    @FXML
    private Button    bozkatuBotoia;

    //Metodoak
    @FXML
    public void onClick(){

        this.apiNagusia.herrialdeaHautatuErakutsi();
    }


    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        String irudiNagusi = this.apiNagusia.getIrudiNagusia();
        Image irudia = IrudiKud.getInstantzia().irudiaKargatu(irudiNagusi);
        this.irudiKontainerra.setImage(irudia);
    }



}
