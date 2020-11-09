package ehu.isad.controllers.db;

import ehu.isad.modeloak.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BozkatuKud {

    //singleton patroia
    private static BozkatuKud instantzia = new BozkatuKud();
    public static BozkatuKud getInstantzia(){
        return instantzia;
    };
    private BozkatuKud(){}

    //Metodoak
    public boolean bozkatuDu(String pHerrialde) throws SQLException {

        boolean bozkatuAlDu = false;

        String query = "SELECT  b.bozkatuDu " +
                        "FROM Bozkaketa b " +
                        "WHERE  b.bozkatuDu = '" + pHerrialde + "' AND " +
                                "b.urtea = strftime('%Y', datetime('now'));";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        bozkatuAlDu = rs.next();

        return rs.next();
    }


    public ObservableList<Herrialdea> lortuHerrialdeak() {

        ObservableList<Herrialdea> herrialdeList = FXCollections.observableArrayList();
        Herrialdea herriHau;
        ResultSet rSet = this.herrialdeakEskatuDatuBaseari();

        try {
            while (rSet.next()) {
                herriHau = this.herrialdeaLortu(rSet);
                herrialdeList.add(herriHau);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return herrialdeList;
    }


    private ResultSet herrialdeakEskatuDatuBaseari() {

        String query = "SELECT  o.herrialdea, o.abestia, o.artista, o.puntuak, h.bandera  " +
                "FROM Ordezkaritza o, Herrialde h " +
                "WHERE  h.izena=o.herrialdea AND " +
                "o.urtea = strftime('%Y', datetime('now'));";

        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        return rs;
    }


    private Herrialdea herrialdeaLortu(ResultSet pHerrialdeZerre) throws SQLException {

        Herrialdea herriHau;

        String  izena    = pHerrialdeZerre.getString("herrialdea");
        String  artista  = pHerrialdeZerre.getString("artista");
        String  abestia  = pHerrialdeZerre.getString("abestia");
        String  bandera  = pHerrialdeZerre.getString("bandera");
        int     puntuak  = pHerrialdeZerre.getInt("puntuak");

        herriHau = new Herrialdea(izena, artista, abestia, bandera, puntuak);


        return herriHau;
    }


    public ObservableList<Herrialdea> lortuTopHiru() {

        ObservableList<Herrialdea> herrialdeList = FXCollections.observableArrayList();
        Herrialdea herriHau;
        ResultSet rSet = this.topHiruEskatuDatuBaseari();

        try {
            while (rSet.next()) {
                herriHau = this.herrialdeaLortu(rSet);
                herrialdeList.add(herriHau);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return herrialdeList;
    }


    private ResultSet topHiruEskatuDatuBaseari() {

        String query = "SELECT  o.herrialdea, o.abestia, o.artista, o.puntuak, h.bandera   " +
                "FROM Ordezkaritza o, Herrialde h " +
                "WHERE  o.herrialdea=h.izena " +
                "ORDER BY o.puntuak "+
                "LIMIT 3;";

        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        return rs;
    }


}
