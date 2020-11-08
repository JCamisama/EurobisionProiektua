package ehu.isad.modeloak;

public class Herrialdea {

    private String izena;
    private String artista;
    private String abestia;
    private int    puntuak;
    private String bandera;


    public Herrialdea(String pIzena, String pArtista, String pAbestia, String pBandera, int pPuntuak) {

        this.izena      =   pIzena;
        this.artista    =   pArtista;
        this.abestia    =   pAbestia;
        this.bandera    =   pBandera;
        this.puntuak    =   pPuntuak;
    }

    public String getIzena() {
        return this.izena;
    }
}
