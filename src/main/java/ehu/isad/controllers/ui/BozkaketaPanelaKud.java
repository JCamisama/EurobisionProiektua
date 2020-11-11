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
import java.util.Iterator;
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

        this.modeloarenDatuakTaulanTxertatu();
        this.puntuenTaulaEditagarriaEgin();
        this.puntuenTaularenAldaketakGauzatu();
        this.unekoHerrialdeaEditagarriaEzIzateaZiurtatu();

        this.irudiakHasieratu();
    }

    private void modeloarenDatuakTaulanTxertatu(){
        this.herrialdeenTaula.setItems(this.herrialdeenZerrenda);

        this.herrialdeIzena.setCellValueFactory(new PropertyValueFactory<>("Izena"));
        this.artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        this.abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        this.bandera.setCellValueFactory(new PropertyValueFactory<>("Bandera"));
        this.puntuak.setCellValueFactory(new PropertyValueFactory<Herrialdea, Integer>("BozkaketakoPuntuak"));
    }

    private void puntuenTaulaEditagarriaEgin(){
        this.puntuak.setCellFactory(TextFieldTableCell.<Herrialdea, Integer>
                forTableColumn(new IntegerStringConverter()));
    }

    private void puntuenTaularenAldaketakGauzatu(){
        puntuak.setOnEditCommit((TableColumn.CellEditEvent<Herrialdea, Integer> event) -> {

            TablePosition<Herrialdea, Integer> pos = event.getTablePosition();
            int row = pos.getRow();
            Herrialdea herriHau = event.getTableView().getItems().get(row);
            Integer puntuBerriak = event.getNewValue();

            herriHau.setBozkaketakoPuntuak(puntuBerriak);
        });
    }

    private void unekoHerrialdeaEditagarriaEzIzateaZiurtatu(){

        Callback<TableColumn<Herrialdea, Integer>, TableCell<Herrialdea, Integer>> defaultTextFieldCellFactory
                = TextFieldTableCell.forTableColumn(new IntegerStringConverter());

        this.puntuak.setCellFactory(kol -> {
            TableCell<Herrialdea, Integer> cell = defaultTextFieldCellFactory.call(kol);

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
        herrialdeenTaula.refresh();
    }

    private boolean ondoBanatuDitu(){
        //AurreB: Ondo banatzeak 5 puntu totalean sartzeak dakar eta puntu negatiborik ez onartzea

        int banatutakoPuntuenTotala = 0;
        Iterator<Herrialdea> itr = this.herrialdeenZerrenda.iterator();
        Herrialdea herriHau;
        boolean puntuNegatiborenBatDago = false;

        while(itr.hasNext() && !puntuNegatiborenBatDago){
            herriHau = itr.next();
            banatutakoPuntuenTotala += herriHau.getBozkaketakoPuntuak();
            puntuNegatiborenBatDago = herriHau.getBozkaketakoPuntuak()<0;
        }

        return (banatutakoPuntuenTotala == 5) && !puntuNegatiborenBatDago;
    }

    @Override
    public void herrialdeaEguneratu(String pHerrialde){
        this.herrialdeText.setText(pHerrialde);
    }

    @Override
    public void top3Eguneratu() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


}
