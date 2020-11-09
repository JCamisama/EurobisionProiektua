package ehu.isad;

import ehu.isad.modeloak.EurobiMain;
import javafx.fxml.Initializable;

public interface LeihoKudeatzaile extends Initializable {


    public void setMainApp(EurobiMain pNagusia);
    public void hasieratu();
    public void herrialdeaEguneratu(String pHerrialde);
}
