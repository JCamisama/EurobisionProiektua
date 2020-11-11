package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.controllers.db.BozkatuKud;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.modeloak.Herrialdea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class topHiruKud implements LeihoKudeatzaile {

    //Atributuak
    private EurobiMain apiNagusia;

    @FXML private TableView<Herrialdea> topHiruTaula;
    @FXML private TableColumn<Herrialdea, String> herrialdeZutabe;
    @FXML private TableColumn<Herrialdea, ImageView> banderaZutabe;
    @FXML private TableColumn<Herrialdea, Integer> puntuakZutabe;

    @FXML private ImageView irudiNagusiKontainerra;

    private ObservableList<Herrialdea> topHiruZerrenda;


    //Metodoak
    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void hasieratu() {

        this.herrialdeZutabe.setCellValueFactory(new PropertyValueFactory<>("Izena"));
        this.banderaZutabe.setCellValueFactory(new PropertyValueFactory<>("Bandera"));
        this.puntuakZutabe.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));

        // modeloaren datuak taulan txertatu
        this.top3Eguneratu();
        this.topHiruTaula.setItems(this.topHiruZerrenda);

        irudiNagusiKontainerra.setImage(this.apiNagusia.getIrudiNagusia());

    }

    @FXML
    void onClick(ActionEvent event) {
        this.apiNagusia.hasieraErakutsi();
    }

    @Override
    public void herrialdeaEguneratu(String pHerrialde) {

    }

    @Override
    public void top3Eguneratu() {
        this.topHiruTaula.getItems().removeAll();
        this.topHiruZerrenda = BozkatuKud.getInstantzia().lortuTopHiru();
        this.topHiruTaula.setItems(this.topHiruZerrenda);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){ }

}
