package com.example.notatnik_json;
import com.google.gson.annotations.SerializedName;

public class Waluta {
    @SerializedName("nazwa")
    private String nazwaWaluty;
    @SerializedName("kraj")
    private String kraj;
    @SerializedName("wartosc")
    private double wartoscWaluty;

    public Waluta(String nazwaWaluty, String kraj, double wartoscWaluty) {
        this.nazwaWaluty = nazwaWaluty;
        this.kraj = kraj;
        this.wartoscWaluty = wartoscWaluty;
    }

    public String getNazwaWaluty() {
        return nazwaWaluty;
    }

    public void setNazwaWaluty(String nazwaWaluty) {
        this.nazwaWaluty = nazwaWaluty;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public double getWartoscWaluty() {
        return wartoscWaluty;
    }

    public void setWartoscWaluty(double wartoscWaluty) {
        this.wartoscWaluty = wartoscWaluty;
    }
}
