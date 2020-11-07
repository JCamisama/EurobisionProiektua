package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.modeloak.EurobiMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements LeihoKudeatzaile {

    @FXML
    private AnchorPane hautaketaPanela;

    @FXML
    private ImageView irudiContainer;

    @FXML
    private Text rndmText1;

    @FXML
    private Text rndmText2;

    @FXML
    private ComboBox<?> herrialdeComboBox;

    @FXML
    private Button okBotoia;

    private EurobiMain apiNagusia;

    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
