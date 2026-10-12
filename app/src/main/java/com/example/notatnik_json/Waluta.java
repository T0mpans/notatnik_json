package com.example.notatnik_json;
import com.google.gson.annotations.SerializedName;

public class Waluta {
    @SerializedName("nazwa")
    private String nazwaWaluty;
    @SerializedName("kraj")
    private String kraj;
    @SerializedName("wartosc")
    private float wartoscWaluty;

    public Waluta(String nazwaWaluty, String kraj, float wartoscWaluty) {
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

    public float getWartoscWaluty() {
        return wartoscWaluty;
    }

    public void setWartoscWaluty(float wartoscWaluty) {
        this.wartoscWaluty = wartoscWaluty;
    }
}
