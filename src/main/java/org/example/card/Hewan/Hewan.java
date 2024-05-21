package org.example.card.Hewan;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Produk.Produk;

public abstract class Hewan extends Card implements BisaPanen {
    protected int berat;
    protected int standarBeratPanen;
    protected String tipe;
    protected boolean isTerlindungi = false;
    protected boolean isTrapActivated = false;
    
    public Hewan(String name, String imgPath, int berat, int standarBeratPanen, String tipe) {
        super(name, imgPath);
        this.berat = berat;
        this.standarBeratPanen = standarBeratPanen;
        this.tipe = tipe;
    }

    public int getBerat() {
        return berat;
    }

    public int getStandarBeratPanen() {
        return standarBeratPanen;
    }

    public String getTipe() {
        return tipe;
    }

    public void addBerat(int berat) {
        this.berat += berat;
        if (this.berat < 0) this.berat = 0;
    }

    public boolean isSiapPanen() {
        return this.berat >= this.standarBeratPanen;
    }

    @Override
    public boolean isTerlindungi() {
        return isTerlindungi;
    }

    @Override
    public void setTerlindungi(boolean state) {
        this.isTerlindungi = state;
    }

    @Override
    public boolean isTrapActivated() {
        return isTrapActivated;
    }

    @Override
    public void setTrapActivated(boolean state) {
        this.isTrapActivated = state;
    }

    @Override
    abstract public Produk panen();
}