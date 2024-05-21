package org.example.card.Item;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;
import org.example.card.Tumbuhan.Tumbuhan;

public class Delay extends Item {
    public Delay(String name, String imgPath) {
        super(name, imgPath);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            ((Hewan) bisaPanen).addBerat(-5);
        } else if (bisaPanen instanceof Tumbuhan) {
            ((Tumbuhan) bisaPanen).addUmur(-2);
        }
    }
}