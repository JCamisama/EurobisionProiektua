package ehu.isad.controllers.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EzarpenakDBKud {

  // singleton patroia

  private static EzarpenakDBKud instantzia = new EzarpenakDBKud();

  public static EzarpenakDBKud getInstantzia(){
      return instantzia;
  };

  private EzarpenakDBKud (){}
/*
  public List<Ezarpena> lortuEzarpenak(){

    List<Ezarpena> emaitza = new ArrayList<>();
    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

    String query = "select * from properties";
    ResultSet rs = dbkud.execSQL(query);

    try {
      while (rs.next()) {

        Integer erabID = rs.getInt("userid");
        String key = rs.getString("key");
        String value = rs.getString("value");

        Ezarpena ezarpena = new Ezarpena(erabID, key, value);
        emaitza.add(ezarpena);
      }
    }catch (SQLException e){
      System.err.println(e);
    }


    return emaitza;
  }

  public void eguneratu() {
    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
    dbkud.execSQL("INSERT INTO properties ('userid', 'key', 'value') values ('5','6','7')");

  }*/
}
