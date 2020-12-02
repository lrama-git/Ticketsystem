package application.model;

import javafx.scene.control.TextField;

public class User {
    public String number;
    public String name;
    public String title;
    public String street;
    public String plz;
    public String ort;
    public String abteilungsnummer;

    @Override
    public String toString() { return number + " - " + title + name;}

    public String newCSVLine (){
        return number + "\";\"" + title + "\";\""+ name + "\";\"" + street + "\";\"" +
                plz + "\";\"" + ort + "\";\"" + abteilungsnummer + "\";\"";
    }
//3;Dipl-Ing.;Heinz Schweiger;AC/DC Straße 1;666;Rockcity;1
}

