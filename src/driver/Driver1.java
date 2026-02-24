import java.util.*;

public class Driver1 {

    static class Menu {
        String nama;
        int harga;

        Menu(String nama, int harga) {
            this.nama = nama;
            this.harga = harga;
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ====== DAFTAR MENU ======
        Map<String, Menu> daftarMenu = new HashMap<>();
        daftarMenu.put("NGS", new Menu("Nasi Goreng Spesial", 15000));
        daftarMenu.put("AP", new Menu("Ayam Penyet", 20000));
        daftarMenu.put("SA", new Menu("Sate Ayam (10 Tusuk)", 25000));
        daftarMenu.put("BU", new Menu("Bakso Urat", 18000));
        daftarMenu.put("MAP", new Menu("Mie Ayam Pangsit", 15000));
        daftarMenu.put("GG", new Menu("Gado-Gado", 15000));
        daftarMenu.put("SAM", new Menu("Soto Ayam", 17000));
        daftarMenu.put("RD", new Menu("Rendang Daging", 25000));
        daftarMenu.put("IB", new Menu("Ikan Bakar", 35000));
        daftarMenu.put("NUK", new Menu("Nasi Uduk Komplit", 20000));

        List<String> listMenu = new ArrayList<>();
        List<Integer> listPorsi = new ArrayList<>();
        List<Integer> listHarga = new ArrayList<>();
        List<Integer> listTotal = new ArrayList<>();

        int grandTotal = 0;

        System.out.println("====================================");
        System.out.println(" SISTEM PEMESANAN BUTET & UCOK ");
        System.out.println("====================================");
        System.out.println("Ketik kode menu (NGS, AP, SA, BU, MAP, GG, SAM, RD, IB, NUK)");
        System.out.println("Ketik '---' untuk selesai");

        while (true) {

            System.out.print("\nMasukkan kode menu: ");
            String kode = input.nextLine().trim().toUpperCase();

            if (kode.equals("---")) {
                break;
            }

            if (!daftarMenu.containsKey(kode)) {
                System.out.println("Kode tidak tersedia! Silakan ulangi.");
                continue;
            }

            int porsiButet = 0;

            while (true) {
                try {
                    System.out.print("Jumlah porsi Butet: ");
                    porsiButet = Integer.parseInt(input.nextLine());

                    if (porsiButet <= 0) {
                        System.out.println("Porsi harus lebih dari 0!");
                        continue;
                    }
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Input harus angka!");
                }
            }

            Menu menu = daftarMenu.get(kode);

            int porsiUcok = porsiButet * 2;
            int totalPorsi = porsiButet + porsiUcok;
            int totalHarga = totalPorsi * menu.harga;

            listMenu.add(menu.nama);
            listPorsi.add(totalPorsi);
            listHarga.add(menu.harga);
            listTotal.add(totalHarga);

            grandTotal += totalHarga;

            System.out.println("Ucok otomatis memesan " + porsiUcok + " porsi.");
        }

        // ====== OUTPUT ======
        if (listMenu.isEmpty()) {
            System.out.println("\nTidak ada pesanan.");
            return;
        }

        System.out.println("\n===============================================");
        System.out.println("               RINCIAN PESANAN");
        System.out.println("===============================================");
        System.out.printf("%-25s %-10s %-12s %-15s\n",
                "Menu", "Porsi", "Harga", "Total");

        for (int i = 0; i < listMenu.size(); i++) {
            System.out.printf("%-25s %-10d Rp %-9d Rp %-10d\n",
                    listMenu.get(i),
                    listPorsi.get(i),
                    listHarga.get(i),
                    listTotal.get(i));
        }

        System.out.println("---------------------------------------------------");
        System.out.println("Subtotal : Rp " + grandTotal);

        // ====== DISKON ======
        double diskon = 0;

        if (grandTotal >= 500000) diskon = 0.25;
        else if (grandTotal >= 400000) diskon = 0.20;
        else if (grandTotal >= 300000) diskon = 0.15;
        else if (grandTotal >= 200000) diskon = 0.10;
        else if (grandTotal >= 100000) diskon = 0.05;

        double potongan = grandTotal * diskon;
        double totalBayar = grandTotal - potongan;

        System.out.println("Diskon   : " + (int)(diskon * 100) + "%");
        System.out.println("Potongan : Rp " + (int) potongan);
        System.out.println("===============================================");
        System.out.println("Total Bayar : Rp " + (int) totalBayar);
        System.out.println("===============================================");

        input.close();
    }
}