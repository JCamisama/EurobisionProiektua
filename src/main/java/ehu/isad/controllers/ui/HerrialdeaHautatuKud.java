package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.controllers.db.BozkatuKud;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.modeloak.Herrialdea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements LeihoKudeatzaile {

    @FXML private AnchorPane hautaketaPanela;

    @FXML private ImageView irudiContainer;

    @FXML private ComboBox herrialdeComboBox;

    @FXML private Button okBotoia;

    private EurobiMain apiNagusia;

    @Override
    public void hasieratu(){
        this.herrialdeekinBeteComboBox();
    }

    private void herrialdeekinBeteComboBox(){

        BozkatuKud bozkaKud = BozkatuKud.getInstantzia();
        ObservableList<Herrialdea> herrialdeList = bozkaKud.lortuHerrialdeak();

        this.herrialdeComboBox.setItems(herrialdeList);
        this.herrialdeComboBox.setEditable(false);
        this.comboBoxekoEtiketaEguneratu();
    }


    private void comboBoxekoEtiketaEguneratu(){

        this.herrialdeComboBox.setConverter(new StringConverter<Herrialdea>() {

            @Override
            public String toString(Herrialdea pHerrialde) {
                if (pHerrialde == null) {
                    return "";
                }
                else{
                    return pHerrialde.getIzena();
                }
            }
            @Override
            public Herrialdea fromString(String izena) {
                return null;
            }
        });
    }

    @FXML
    public void onClickComboboxen(ActionEvent actionEvent) {

        this.comboBoxekoEtiketaEguneratu();
    }

    @FXML
    public void onClickBotoian(ActionEvent actionEvent) {

        this.apiNagusia.hasieraErakutsi();
    }

    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
