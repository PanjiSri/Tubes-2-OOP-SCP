package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Hewan.*;
import org.example.card.Item.*;
import org.example.card.StaticCard;
import org.example.card.Tumbuhan.BijiJagung;
import org.example.card.Tumbuhan.BijiLabu;
import org.example.card.Tumbuhan.BijiStroberi;
import org.example.card.Tumbuhan.Tumbuhan;

public class Player {
    private String name;
    private int coin;
    private int card_in_one_turn;
    ArrayList<Card> deck;
    ArrayList<Card> deck_aktif;
    ArrayList<ArrayList<Card>> lahan;

    public Player(String _name, int _coin) {
        name = _name;
        coin = _coin;
        deck = new ArrayList<Card>();
        deck_aktif = new ArrayList<Card>();
        lahan = new ArrayList<ArrayList<Card>>();

        for (int i = 0; i < 40; i++) {
            try {
                Class<?> clazz = Class.forName(StaticCard.getRandomCardName());
                Card temp = (Card) clazz.getDeclaredConstructor().newInstance();
                deck.add(temp);
            } catch (Exception e) {
                System.out.println("kelas tidak ditemukan");
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 6; i++) {
            deck_aktif.add(null);
        }
        
        for (int i = 0; i < 4; i++) {
            ArrayList<Card> temp = new ArrayList<Card>();
            for (int j = 0; j < 5; j++) {
                temp.add(null);
            }
            lahan.add(temp);
        }
    }

    public void add_ciot() {
        card_in_one_turn++;
    }

    public void add_into_deck(Card i) {
        deck.add(i);
    }

    public void delete_from_ladang (int i, int j) {
        lahan.get(i).remove(j);
        lahan.get(i).add(j, null);
    }

    public void reset_ciot() {
        card_in_one_turn = 0;
    }

    public void drop_deck_aktif(Card i) {
        String id_card = Integer.toString(i.getId());
        for(int x = 0; x < deck_aktif.size(); x++) {
            String id_card_aktif = " ";
            if (deck_aktif.get(x) != null) {
                id_card_aktif = Integer.toString(deck_aktif.get(x).getId());
            }
            if(deck_aktif.get(x) != null && id_card_aktif.equals(id_card)) {
                deck_aktif.remove(x);
                deck_aktif.add(x, null);
                print_deck_aktif();
                break;
            }
        }
    }

    public void add_umur_tumbuhan() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) == null) {
                    continue;
                }
                else {
                    if (lahan.get(i).get(j) instanceof Tumbuhan) {
                        Tumbuhan tumbuhan = (Tumbuhan) lahan.get(i).get(j);
                        tumbuhan.addUmur(2);
                        if (tumbuhan.isSiapPanen()) {
                            tumbuhan.setImgPathToProduct();
                        }
                    }
                }
            }
        }
    }

    public void delete_item_aktiv() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) == null) {
                    continue;
                }
                else {
                    if (lahan.get(i).get(j) instanceof Tumbuhan) {
                        Tumbuhan tumbuhan = (Tumbuhan) lahan.get(i).get(j);
                        tumbuhan.setItem("Accelerate", -1);
                        tumbuhan.setItem("Delay", -1);
                        tumbuhan.setItem("Protect", -1);
                        tumbuhan.setItem("Trap", -1);
                    } else if (lahan.get(i).get(j) instanceof Hewan) {
                        Hewan hewan = (Hewan) lahan.get(i).get(j);
                        hewan.setItem("Accelerate", -1);
                        hewan.setItem("Delay", -1);
                        hewan.setItem("Protect", -1);
                        hewan.setItem("Trap", -1);
                    }
                }
            }
        }
    }

    public void drop_ladang(String id) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) == null) {
                    continue;
                }
                else {
                    String id_kartu = Integer.toString(lahan.get(i).get(j).getId());
                    if(id_kartu.equals(id)) {
                        lahan.get(i).set(j, null);
                    }
                }
            }
        }
    }

    public int getCard_in_one_turn() {
        return card_in_one_turn;
    }

    public void remove_deck(String id_kartu) {
        for(int i = 0; i < deck.size(); i++) {
            if(deck.get(i) != null) {
                deck.remove(i);
                break;
            }
        }
    }

    public Card get_kartu(int idx) {
        return deck.get(idx);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void add_into_deck_aktiv(Card card) {
        System.out.println("Ini hewan: " + card.getName());
        for(int i = 0; i < deck_aktif.size(); i++) {
            if(deck_aktif.get(i) == null) {
                deck_aktif.set(i, card);
                break;
            }
        }
    }

    public Card get_deck (int idx) {
        return deck.get(idx);
    }

    public Card get_card_aktif (int idx) {
        return deck_aktif.get(idx);
    }

    public int get_card_aktif_idx (String id) {
        for(int i = 0; i < deck_aktif.size(); i++) {
            if(deck_aktif.get(i) != null) {
                int id_card = deck_aktif.get(i).getId();
                if (Integer.toString(id_card).equals(id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Card get_card_ladang (int i, int j) {
        return lahan.get(i).get(j);
    }

    public String getName() {
        return name;
    }

    public int getCoin() {
        return coin;
    }

    public void add_in_lahan(int x, int y, Card value) {
        lahan.get(x).set(y, value);
    }

    public void drop_ladang(int x, int y) {
        lahan.get(x).set(y, null);
    }

    public void print_lahan() {
        for (int i = 0; i < lahan.size(); i++) {
            for (int j = 0; j < lahan.get(i).size(); j++) {
                if (lahan.get(i).get(j) == null) {
                    System.out.print("null ");
                } else {
                    System.out.print(lahan.get(i).get(j).getId() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void print_deck_aktif() {
        for (int i = 0; i < deck_aktif.size(); i++) {
            if (deck_aktif.get(i) == null) {
                System.out.println("null ");
            } else {
                System.out.println(deck_aktif.get(i).getId() + " ");
            }
        }
        System.out.println();
    }

    public void print_deck() {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == null) {
                System.out.println("null ");
            } else {
                System.out.println(deck.get(i).getId() + " " + deck.get(i).getName());
            }
        }
    }

    public int get_deck_aktif_size() {
        int size = 0;
        for (int i = 0; i < 6; i++) {
            if (deck_aktif.get(i) != null) {
                size+=1;
            }
        }
        return size;
    }

    public ArrayList<Integer> get_idx_lahan(String id) {
        ArrayList<Integer> idx_lahan = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) == null) {
                    continue;
                }
                else {
                    String id_kartu = Integer.toString(lahan.get(i).get(j).getId());
                    if(id_kartu.equals(id)) {
                        idx_lahan.add(i);
                        idx_lahan.add(j);
                    }
                    System.out.println("Baris: " + idx_lahan.get(0));
                    return idx_lahan;
                }
            }
        }
        return null;
    }

    public void player_load(ArrayList<String> data) {
        for(int i = 0; i < 40; i++) {
            deck.remove(i);
            deck.add(i,null);
        }
        System.out.println(deck_size());
        int a = 0;
        coin = Integer.valueOf(data.get(a));
        a += 1;
        int deck_size = Integer.valueOf(data.get(a));
        System.out.println(deck_size);
        a += 1;
        for(int i = 0; i < deck_size; i++) {
            try {
                Class<?> clazz = Class.forName(StaticCard.getRandomCardName());
                Card temp = (Card) clazz.getDeclaredConstructor().newInstance();
                deck.remove(i);
                deck.add(i, temp);
            } catch (Exception e) {
                System.out.println("kelas tidak ditemukan");
                e.printStackTrace();
            }
        }
        System.out.println(deck_size());
        int deck_aktif_size = Integer.valueOf(data.get(a));
        a += 1;
        for(int i = 0; i < deck_aktif_size; i++) {
            ArrayList<Integer> idx = get_indeks(data.get(a));
            System.out.println(data.get(a));
            System.out.println(idx);
            a += 1;
            Card card = get_card(data.get(a));
            a += 1;
            System.out.println("index" + idx.get(1));
            System.out.println(deck_aktif);
            System.out.println(deck_aktif);
            deck_aktif.set(idx.get(1), card);
            System.out.println(deck_aktif);
        }
        int ladang_size = Integer.valueOf(data.get(a));
        System.out.println(ladang_size);
        a += 1;
        for (int i = 0; i < ladang_size; i++) {
            ArrayList<Integer> idx = get_indeks(data.get(a));
            a += 1;
            String mahkluk = data.get(a);
            a += 1;
            int berat = Integer.valueOf(data.get(a));
            Card card = get_card_with_berat(mahkluk, berat);
            lahan.get(idx.get(0)).remove(idx.get(1));
            lahan.get(idx.get(0)).add(idx.get(1), card);
            a += 1;
            int item = Integer.valueOf(data.get(a));
            a += 1;
            for (int j = 0; j < item; j++) {
                String power = data.get(a);
                a += 1;
                add_power(card, power);
            }
        }
    }

    public ArrayList<Integer> get_indeks(String lokasi) {
        ArrayList<Integer> indeks = new ArrayList<>();
        if(lokasi.equals("A01")) {
            indeks.add(0);
            indeks.add(0);
        }
        else if(lokasi.equals("A02")) {
            indeks.add(1);
            indeks.add(0);
        }
        else if(lokasi.equals("A03")) {
            indeks.add(2);
            indeks.add(0);
        }
        else if(lokasi.equals("A04")) {
            indeks.add(3);
            indeks.add(0);
        }
        else if(lokasi.equals("B01")) {
            indeks.add(0);
            indeks.add(1);
        }
        else if(lokasi.equals("B02")) {
            indeks.add(1);
            indeks.add(1);
        }
        else if(lokasi.equals("B03")) {
            indeks.add(2);
            indeks.add(1);
        }
        else if(lokasi.equals("B04")) {
            indeks.add(3);
            indeks.add(1);
        }
        else if(lokasi.equals("C01")) {
            indeks.add(0);
            indeks.add(2);
        }
        else if(lokasi.equals("C02")) {
            indeks.add(1);
            indeks.add(2);
        }
        else if(lokasi.equals("C03")) {
            indeks.add(2);
            indeks.add(2);
        }
        else if(lokasi.equals("C04")) {
            indeks.add(3);
            indeks.add(2);
        }
        else if(lokasi.equals("D01")) {
            indeks.add(0);
            indeks.add(3);
        }
        else if(lokasi.equals("D02")) {
            indeks.add(1);
            indeks.add(3);
        }
        else if(lokasi.equals("D03")) {
            indeks.add(2);
            indeks.add(3);
        }
        else if(lokasi.equals("D04")) {
            indeks.add(3);
            indeks.add(3);
        }
        else if(lokasi.equals("E01")) {
            indeks.add(0);
            indeks.add(4);
        }
        else if(lokasi.equals("F01")) {
            indeks.add(0);
            indeks.add(5);
        }
        return indeks;
    }

    public String get_idx (int x, int y) {
        if (x == 0 && y == 0) {
            return "A01";
        }
        else if (x == 1 && y == 0) {
            return "A02";
        }
        else if (x == 2 && y == 0) {
            return "A03";
        }
        else if (x == 3 && y == 0) {
            return "A04";
        }
        else if (x == 0 && y == 1) {
            return "B01";
        }
        else if (x == 1 && y == 1) {
            return "B02";
        }
        else if (x == 2 && y == 1) {
            return "B03";
        }
        else if (x == 3 && y == 1) {
            return "B04";
        }
        else if (x == 0 && y == 2) {
            return "C01";
        }
        else if (x == 1 && y == 2) {
            return "C02";
        }
        else if (x == 2 && y == 2) {
            return "C03";
        }
        else if (x == 3 && y == 2) {
            return "C04";
        }
        else if (x == 0 && y == 3) {
            return "D01";
        }
        else if (x == 1 && y == 3) {
            return "D02";
        }
        else if (x == 2 && y == 3) {
            return "D03";
        }
        else if (x == 3 && y == 3) {
            return "D04";
        }
        else if (x == 0 && y == 4) {
            return "E01";
        }
        else if (x == 0 && y == 5) {
            return "F01";
        }
        return null;
    }

    public String get_mahkluk (Card card) {
        if(card instanceof Ayam) {
            return "AYAM";
        }
        else if(card instanceof Beruang) {
            return "BERUANG";
        }
        else if(card instanceof Domba) {
            return "DOMBA";
        }
        else if(card instanceof HiuDarat) {
            return "HIU_DARAT";
        }
        else if(card instanceof Kuda) {
            return "KUDA";
        }
        else if(card instanceof Sapi) {
            return "SAPI";
        }
        else if(card instanceof Accelerate) {
            return "ACCELERATE";
        }
        else if(card instanceof Delay) {
            return "DELAY";
        }
        else if(card instanceof Destroy) {
            return "DESTROY";
        }
        else if (card instanceof InstantHarvest) {
            return "INSTANT_HARVEST";
        }
        else if(card instanceof Protect) {
            return "PROTECT";
        }
        else if (card instanceof Trap) {
            return "TRAP";
        }
        else if(card instanceof BijiStroberi) {
            return "BIJI_STROBERI";
        }
        else if(card instanceof BijiJagung) {
            return "BIJI_JAGUNG";
        }
        else if(card instanceof BijiLabu) {
            return "BIJI_LABU";
        }
        return "LABU";
    }

    public Card get_card(String mahkluk) {
        if(mahkluk.equals("AYAM")) {
            Ayam a = new Ayam();
            return a;
        }
        else if(mahkluk.equals("BERUANG")) {
            Beruang b = new Beruang();
            return b;
        }
        else if(mahkluk.equals("DOMBA")) {
            Domba d = new Domba();
            return d;
        }
        else if(mahkluk.equals("HIU_DARAT")) {
            HiuDarat hiu = new HiuDarat();
            return hiu;
        }
        else if(mahkluk.equals("KUDA")) {
            Kuda kuda = new Kuda();
            return kuda;
        }
        else if(mahkluk.equals("SAPI")) {
            Sapi sapi = new Sapi();
            return sapi;
        }
        else if(mahkluk.equals("BIJI_JAGUNG")) {
            BijiJagung bij = new BijiJagung();
            return bij;
        }
        else if(mahkluk.equals("BIJI_LABU")) {
            BijiLabu bij = new BijiLabu();
            return bij;
        }
        else if(mahkluk.equals("BIJI_STROBERI")) {
            BijiStroberi bij = new BijiStroberi();
            return bij;
        }
        return null;
    }

    public void add_power(Card card, String power) {
        if (power.equals("ACCELERATE")) {
            Accelerate a = new Accelerate();
            if (card instanceof Hewan) {
                Hewan hewan = (Hewan) card;
                System.out.println("Berat: "+hewan.getBerat());
                a.aksi(hewan);

            }
            else {
                Tumbuhan tumbuhan = (Tumbuhan) card;
                a.aksi(tumbuhan);
            }
        }
        else if(power.equals("DELAY")) {
            Delay a = new Delay();
            if (card instanceof Hewan) {
                Hewan hewan = (Hewan) card;
                a.aksi(hewan);
            }
            else {
                Tumbuhan tumbuhan = (Tumbuhan) card;
                a.aksi(tumbuhan);
            }
        }
        else if(power.equals("DESTROY")) {
            Destroy a = new Destroy();
            if (card instanceof Hewan) {
                Hewan hewan = (Hewan) card;
                a.aksi(hewan);
            }
            else {
                Tumbuhan tumbuhan = (Tumbuhan) card;
                a.aksi(tumbuhan);
            }
        } else if (power.equals("INSTANT_HARVEST")) {
            InstantHarvest a = new InstantHarvest();
            if (card instanceof Hewan) {
                Hewan hewan = (Hewan) card;
                a.aksi(hewan);
            }
            else {
                Tumbuhan tumbuhan = (Tumbuhan) card;
                a.aksi(tumbuhan);
            }
        }
        else if(power.equals("PROTECT")) {
            Protect a = new Protect();
            if (card instanceof Hewan) {
                Hewan hewan = (Hewan) card;
                a.aksi(hewan);
            }
            else {
                Tumbuhan tumbuhan = (Tumbuhan) card;
                a.aksi(tumbuhan);
            }
        }

        else if(power.equals("TRAP")) {
            Trap a = new Trap();
            if (card instanceof Hewan) {
                Hewan hewan = (Hewan) card;
                a.aksi(hewan);
            }
            else {
                Tumbuhan tumbuhan = (Tumbuhan) card;
                a.aksi(tumbuhan);
            }
        }
    }

    public Card get_card_with_berat(String mahkluk, int berat) {
        if(mahkluk.equals("AYAM")) {
            Ayam a = new Ayam(berat);
            return a;
        }
        else if(mahkluk.equals("BERUANG")) {
            Beruang b = new Beruang(berat);
            return b;
        }
        else if(mahkluk.equals("DOMBA")) {
            Domba d = new Domba(berat);
            return d;
        }
        else if(mahkluk.equals("HIU_DARAT")) {
            HiuDarat hiu = new HiuDarat(berat);
            return hiu;
        }
        else if(mahkluk.equals("KUDA")) {
            Kuda kuda = new Kuda(berat);
            return kuda;
        }
        else if(mahkluk.equals("SAPI")) {
            Sapi sapi = new Sapi(berat);
            return sapi;
        }
        else if(mahkluk.equals("BIJI_JAGUNG")) {
            BijiJagung bij = new BijiJagung(berat);
            return bij;
        }
        else if(mahkluk.equals("BIJI_LABU")) {
            BijiLabu bij = new BijiLabu(berat);
            return bij;
        }
        else if(mahkluk.equals("BIJI_STROBERI")) {
            BijiStroberi bij = new BijiStroberi(berat);
            return bij;
        }
        return null;
    }

    public int get_ladang () {
        int size = 0;
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) != null) {
                    size += 1;
                }
            }
        }
        return size;
    }

    public int deck_size() {
        int size = 0;
        for(int i = 0; i < deck.size(); i++) {
            if (deck.get(i) != null) {
                size+=1;
            }
        }
        return size;
    }

    public ArrayList<String> get_save() {
        ArrayList<String> data = new ArrayList<>();
        data.add(Integer.toString(coin));
        data.add(Integer.toString(deck_size()));
        data.add(Integer.toString(get_deck_aktif_size()));
        for(int i = 0; i < 6; i++) {
            if(deck_aktif.get(i) != null) {
                String indeks = get_idx(0, i);
                String mahkluk = get_mahkluk(deck_aktif.get(i));
                data.add(indeks+ " " + mahkluk);
            }
        }
        data.add(Integer.toString(get_ladang()));
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if(lahan.get(i).get(j) != null) {
                    String umur = "";
                    String indeks = get_idx(i, j);
                    String mahkluk = get_mahkluk(lahan.get(i).get(j));
                    if(lahan.get(i).get(j) instanceof Hewan) {
                        Hewan hewan = (Hewan) lahan.get(i).get(j);
                        umur =Integer.toString(hewan.getBerat());
                    }
                    if(lahan.get(i).get(j) instanceof Tumbuhan) {
                        Tumbuhan tumbuhan = (Tumbuhan) lahan.get(i).get(j);
                        umur = Integer.toString(tumbuhan.getUmur());
                    }
                    data.add(indeks +" "+ mahkluk+" "+umur+" "+Integer.toString(0));
                }
            }
        }
        System.out.println(data);
        return data;
    }
    
    // format namaProduk = SIRIP_HIU
    public void beli(Toko toko, String namaProduk) {
        if (toko.ambilStokProduk(namaProduk) <= 0) {
            throw new IllegalStateException("Stok habis");
        }
        if (coin < toko.ambilHargaProduk(namaProduk)) {
            throw new IllegalStateException("Uang tidak cukup");
        }
        toko.kurangiStokProduk(namaProduk);
        coin -= toko.ambilHargaProduk(namaProduk);
    }

    // format namaProduk = SIRIP_HIU
    public void jual(Toko toko, String namaProduk) {
        toko.tambahStokProduk(namaProduk);
        coin += toko.ambilHargaProduk(namaProduk);
    }

    public void deck_catat() {
        System.out.println(deck);
    }
}
