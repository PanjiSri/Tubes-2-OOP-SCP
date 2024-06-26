package org.example.card.Produk;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;

public class Jagung extends Produk {
    public Jagung() {
        super("Jagung", "/img/Produk/corn.png", 150, 3);
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
