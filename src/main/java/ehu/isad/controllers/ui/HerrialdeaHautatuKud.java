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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements LeihoKudeatzaile {

    @FXML private ImageView irudiContainer;

    @FXML private ComboBox herrialdeComboBox;

    private EurobiMain apiNagusia;

    @Override
    public void hasieratu(){
        this.herrialdeekinBeteComboBox();
        this.irudiContainer.setImage(this.apiNagusia.getIrudiNagusia());
    }

    private void herrialdeekinBeteComboBox(){

        BozkatuKud bozkaKud = BozkatuKud.getInstantzia();
        ObservableList<Herrialdea> herrialdeList = bozkaKud.lortuHerrialdeak();

        this.herrialdeComboBox.setItems(herrialdeList);
        this.herrialdeComboBox.setEditable(false);
        this.herrialdeComboBox.setValue(herrialdeList.get(0));
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
    public void onClickBotoian(ActionEvent actionEvent) throws SQLException {

        this.apiNagusia.unekoHerrialdeaEguneratu(this.unekoHerrialdeAukeratua());

        if(this.bozkatuAlDu()){
            this.apiNagusia.erroreaErakutsi();
        }
        else{
            this.apiNagusia.bozkaketaPanelaErakutsi();
        }
    }

    private boolean bozkatuAlDu() throws SQLException {

        boolean bozkatuDu = BozkatuKud.getInstantzia().bozkatuDu(this.unekoHerrialdeAukeratua());
        return bozkatuDu;
    }

    private String unekoHerrialdeAukeratua(){

        Herrialdea aukeratua = (Herrialdea) this.herrialdeComboBox.getValue();
        return aukeratua.getIzena();
    }

    @Override
    public void herrialdeaEguneratu(String pHerrialde){}



    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @Override
    public void top3Eguneratu() {

    }

}
