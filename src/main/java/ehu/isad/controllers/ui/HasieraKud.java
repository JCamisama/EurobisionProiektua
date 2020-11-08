package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.utils.IrudiKud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements LeihoKudeatzaile {

    //Atributuak
    private EurobiMain apiNagusia;

    @FXML private AnchorPane hasieraPanela;

    @FXML private ImageView irudiKontainerra;

    @FXML private Button bozkatuBotoia;


    //Metodoak

    @FXML
    public void onClick(ActionEvent event) {

        this.apiNagusia.herrialdeaHautatuErakutsi();
    }

    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    public void hasieratu(){

        this.irudiKontainerra.setImage(this.apiNagusia.getIrudiNagusia());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){}



}
