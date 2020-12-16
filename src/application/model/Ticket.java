package application.model;

public class Ticket {
    //das is Person? kein Ticket
    /**public String number;
    public String name;
    public String title;
    public String street;
    public String plz;
    public String ort;
    public String abteilungsnummer;
     */
    public String nummer;
    public String name;
    public String description;
    public Status status;
    public Priority priority;

  //  1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4
    //        2;Fertigstellen ToDo-System;Es soll jedes ToDo auf der rechten Seite angezeigt werden.;2;3

    public String toString() {
        return nummer + " - " + name ;
    }

    public String newCSVLine() {
        //1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4
        return nummer + "\";\"" + name + "\";\"" + description +  "\";\"" + status +  "\";\"" + priority +  "\";\"";
    }
}
