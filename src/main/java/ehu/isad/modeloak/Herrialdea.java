package ehu.isad.modeloak;

import ehu.isad.utils.IrudiKud;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Herrialdea {

    private String izena;
    private String artista;
    private String abestia;
    private int    puntuak;
    private String bandera;
    private int    bozkaketakoPuntuak;


    public Herrialdea(String pIzena, String pArtista, String pAbestia, String pBandera, int pPuntuak) {

        this.izena      =   pIzena;
        this.artista    =   pArtista;
        this.abestia    =   pAbestia;
        this.bandera    =   pBandera;
        this.puntuak    =   pPuntuak;

        this.bozkaketakoPuntuak = 0;
    }

    public String getIzena() {
        return this.izena;
    }
    
    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAbestia() {
        return abestia;
    }

    public void setAbestia(String abestia) {
        this.abestia = abestia;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }

    public ImageView getBandera() {

        Image irudia = IrudiKud.getInstantzia().irudiaKargatu(this.bandera+".png");
        ImageView irudiaMarkoan = new ImageView(irudia);
        return irudiaMarkoan;
    }

    public void setBandera(String bandera) {

        this.bandera = bandera;
    }

    public int getBozkaketakoPuntuak() {
        return bozkaketakoPuntuak;
    }

    public void setBozkaketakoPuntuak(int bozkaketakoPuntuak) {
        this.bozkaketakoPuntuak = bozkaketakoPuntuak;
    }
}
