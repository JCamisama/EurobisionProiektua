package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.controllers.db.BozkatuKud;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.modeloak.Herrialdea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class BozkaketaPanelaKud implements LeihoKudeatzaile {

    //Atributuak
    private EurobiMain apiNagusia;

    @FXML private TableView<Herrialdea> herrialdeenTaula;
    @FXML private TableColumn<Herrialdea, String> herrialdeIzena;
    @FXML private TableColumn<Herrialdea, String> artista;
    @FXML private TableColumn<Herrialdea, String> abestia;
    @FXML private TableColumn<Herrialdea, ImageView> bandera;
    @FXML private TableColumn<Herrialdea, Integer> puntuak;

    @FXML private ImageView logoKontainerra;
    @FXML private ImageView bihotzaKontainerra;
    @FXML private Text herrialdeText;

    @FXML private Button bozkatuBotoia;


    private ObservableList<Herrialdea> herrialdeenZerrenda = BozkatuKud.getInstantzia().lortuHerrialdeak();


    //Metodoak
    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void hasieratu() {

        this.herrialdeIzena.setCellValueFactory(new PropertyValueFactory<>("Izena"));
        this.artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        this.abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        this.bandera.setCellValueFactory(new PropertyValueFactory<>("Bandera"));
        this.puntuak.setCellValueFactory(new PropertyValueFactory<>("BozkaketakoPuntuak"));



        // modeloaren datuak taulan txertatu
        this.herrialdeenTaula.setItems(this.herrialdeenZerrenda);
        
        // gainerako irudiak hasieratu
        this.irudiakHasieratu();
    }

    private void irudiakHasieratu() {

        this.bihotzaKontainerra.setImage(this.apiNagusia.getBihotza());
        this.logoKontainerra.setImage(this.apiNagusia.getIrudiNagusia());
    }

    @FXML
    void onClick(ActionEvent event) {
        this.apiNagusia.topHiruErakutsi();
    }

    @Override
    public void herrialdeaEguneratu(String pHerrialde){
        this.herrialdeText.setText(pHerrialde);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


}
