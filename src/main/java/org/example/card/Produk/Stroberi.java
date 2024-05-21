package org.example.card.Produk;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;

public class Stroberi extends Produk {
    public Stroberi(String name, String imgPath, int harga, int tambahanBerat) {
        super(name, imgPath, harga, tambahanBerat);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            if (((Hewan) bisaPanen).getTipe().equals("Omnivora") || ((Hewan) bisaPanen).getTipe().equals("Herbivora")) {
                ((Hewan) bisaPanen).addBerat(getTambahanBerat());
            }
        }
    }
}