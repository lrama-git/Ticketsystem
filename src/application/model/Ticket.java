package application.model;

public class Ticket {
    public String number;
    public String name;
    public String title;
    public String street;
    public String plz;
    public String ort;
    public String abteilungsnummer;

  //  1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4
    //        2;Fertigstellen ToDo-System;Es soll jedes ToDo auf der rechten Seite angezeigt werden.;2;3

    public String toString() {
        return number + " - " + title + name;
    }

    public String newCSVLine() {
        return number + "\";\"" + title + "\";\"" + name + "\";\"" + street + "\";\"" +
                plz + "\";\"" + ort + "\";\"" + abteilungsnummer + "\";\"";
    }
}
