package application.model;

public class Department {
    public String number;
    public String name;

    @Override
    public String toString() {
        return number + " - " + name;
    }

    public String newCSVLine() {
        return number + "\";\"" + name + "\";\"";
    }
    //
}

