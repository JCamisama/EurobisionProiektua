package ehu.isad.controllers.ui;

import ehu.isad.LeihoKudeatzaile;
import ehu.isad.controllers.db.BozkatuKud;
import ehu.isad.modeloak.EurobiMain;
import ehu.isad.modeloak.Herrialdea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;


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

    private ObservableList<Herrialdea> herrialdeenZerrenda = BozkatuKud.getInstantzia().lortuHerrialdeak();

    //Metodoak
    @Override
    public void setMainApp(EurobiMain pNagusia) {
        this.apiNagusia = pNagusia;
    }

    @Override
    public void hasieratu() {

        this.herrialdeenTaula.setEditable(true);

        // modeloaren datuak taulan txertatu
        this.herrialdeenTaula.setItems(this.herrialdeenZerrenda);

        this.herrialdeIzena.setCellValueFactory(new PropertyValueFactory<>("Izena"));
        this.artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        this.abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        this.bandera.setCellValueFactory(new PropertyValueFactory<>("Bandera"));
        this.puntuak.setCellValueFactory(new PropertyValueFactory<Herrialdea, Integer>("BozkaketakoPuntuak"));

        //Puntuen gelaxka editagarria izateko
        this.puntuak.setCellFactory(TextFieldTableCell.<Herrialdea, Integer>
                forTableColumn(new IntegerStringConverter()));


        puntuak.setOnEditCommit((TableColumn.CellEditEvent<Herrialdea, Integer> event) -> {

            TablePosition<Herrialdea, Integer> pos = event.getTablePosition();
            int row = pos.getRow();
            Herrialdea herriHau = event.getTableView().getItems().get(row);
            Integer puntuBerriak = event.getNewValue();

            herriHau.setBozkaketakoPuntuak(puntuBerriak);
        });

        Callback<TableColumn<Herrialdea, Integer>, TableCell<Herrialdea, Integer>> defaultTextFieldCellFactory
                = TextFieldTableCell.forTableColumn(new IntegerStringConverter());

        this.puntuak.setCellFactory(col -> {
            TableCell<Herrialdea, Integer> cell = defaultTextFieldCellFactory.call(col);

            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {
                    if (cell.getTableView().getSelectionModel().getSelectedItem().
                            getIzena().equals(this.herrialdeText.getText())) {
                        cell.setEditable(false);
                    } else {
                        cell.setEditable(true);
                    }
                }
            });
            return cell;
        });
        
        // gainerako irudiak hasieratu
        this.irudiakHasieratu();
    }

    private void irudiakHasieratu() {

        this.bihotzaKontainerra.setImage(this.apiNagusia.getBihotza());
        this.logoKontainerra.setImage(this.apiNagusia.getIrudiNagusia());
    }

    @FXML
    void onClick(ActionEvent event) {

        if ( this.ondoBanatuDitu() ){
            this.herrialdeenPuntuakEgunetatu();
            BozkatuKud.getInstantzia().puntuakEguneratu(this.herrialdeenZerrenda, this.herrialdeText.getText());
            this.apiNagusia.topHiruErakutsi();
        }
        else{
            this.apiNagusia.erroreaErakutsi();
        }

        this.herrialdePartaideenBozkaketaPuntuakErreseteatu();
    }

    private void herrialdeenPuntuakEgunetatu() {
        for(Herrialdea herri : this.herrialdeenZerrenda){
            herri.setPuntuak( herri.getPuntuak()+herri.getBozkaketakoPuntuak());
        }
    }

    private void herrialdePartaideenBozkaketaPuntuakErreseteatu() {
        for(Herrialdea herri : this.herrialdeenZerrenda){
            herri.setBozkaketakoPuntuak(0);
        }
    }

    private boolean ondoBanatuDitu(){
        //AurreB: Ondo banatzeak 5 puntu totalean sartzeak dakar

        int banatutakoPuntuenTotala = 0;

        for(Herrialdea herri : this.herrialdeenZerrenda){
            banatutakoPuntuenTotala += herri.getBozkaketakoPuntuak();
        }

        return banatutakoPuntuenTotala == 5;
    }

    @Override
    public void herrialdeaEguneratu(String pHerrialde){
        this.herrialdeText.setText(pHerrialde);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


}
