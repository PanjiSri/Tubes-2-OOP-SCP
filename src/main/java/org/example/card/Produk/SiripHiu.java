package org.example.card.Produk;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;

public class SiripHiu extends Produk {
    public SiripHiu() {
        super("Sirip Hiu", "/img/Produk/shark-fin.png", 500, 12);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            if (((Hewan) bisaPanen).getTipe().equals("Omnivora") || ((Hewan) bisaPanen).getTipe().equals("Karnivora")) {
                ((Hewan) bisaPanen).addBerat(getTambahanBerat());
            }
        }
    }
}
