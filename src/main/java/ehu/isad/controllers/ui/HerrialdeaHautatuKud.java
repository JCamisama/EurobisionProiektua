package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.modeloak.EurobiMain;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements LeihoKudeatzaile {

    private EurobiMain apiNagusia;

    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
