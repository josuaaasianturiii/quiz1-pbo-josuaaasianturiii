import java.util.ArrayList;
import java.util.Scanner;

class Transaksi {
    String id;
    String nama;
    ArrayList<String> jenisPakaian;
    ArrayList<Integer> jumlahPakaian;
    int total;
    String status;

    Transaksi(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.jenisPakaian = new ArrayList<>();
        this.jumlahPakaian = new ArrayList<>();
        this.total = 0;
        this.status = "DITERIMA";
    }

    void tambahItem(String jenis, int jumlah) {
        jenisPakaian.add(jenis);
        jumlahPakaian.add(jumlah);
        total += jumlah;
    }
}

public class Driver3 {

    static ArrayList<Transaksi> daftar = new ArrayList<>();
    static int counter = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n=== SISTEM LAUNDRY DEL ===");
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Lihat Semua Transaksi");
            System.out.println("3. Ambil Laundry");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1:
                    tambahTransaksi(input);
                    break;

                case 2:
                    lihatSemua();
                    break;

                case 3:
                    ambilLaundry(input);
                    break;

                case 4:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Menu tidak valid!");
            }

        } while (pilih != 4);

        input.close();
    }

    static void tambahTransaksi(Scanner input) {

        System.out.print("Nama Mahasiswa: ");
        String nama = input.nextLine();

        String id = String.format("LD%03d", counter++);
        Transaksi t = new Transaksi(id, nama);

        System.out.println("\nMasukkan jenis pakaian (ketik '---' untuk selesai)");

        while (true) {
            System.out.print("Jenis pakaian: ");
            String jenis = input.nextLine();

            if (jenis.equals("---")) {
                break;
            }

            System.out.print("Jumlah: ");
            int jumlah = input.nextInt();
            input.nextLine();

            if (jumlah <= 0) {
                System.out.println("Jumlah harus lebih dari 0!");
                continue;
            }

            t.tambahItem(jenis, jumlah);
        }

        if (t.total == 0) {
            System.out.println("Transaksi dibatalkan (tidak ada item).");
            return;
        }

        daftar.add(t);

        System.out.println("\nTransaksi berhasil!");
        System.out.println("ID Transaksi : " + id);
        System.out.println("Total Item   : " + t.total);
        System.out.println("Status       : " + t.status);
    }

    static void lihatSemua() {

        if (daftar.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        for (Transaksi t : daftar) {
            System.out.println("\n---------------------");
            System.out.println("ID     : " + t.id);
            System.out.println("Nama   : " + t.nama);
            System.out.println("Total  : " + t.total);
            System.out.println("Status : " + t.status);
        }
    }

    static void ambilLaundry(Scanner input) {

        System.out.print("Masukkan ID Transaksi: ");
        String cariId = input.nextLine();

        for (Transaksi t : daftar) {

            if (t.id.equalsIgnoreCase(cariId)) {

                if (t.status.equals("SUDAH_DIAMBIL")) {
                    System.out.println("Laundry sudah pernah diambil!");
                    return;
                }

                System.out.println("\nData Laundry:");
                System.out.println("Nama : " + t.nama);

                for (int i = 0; i < t.jenisPakaian.size(); i++) {
                    System.out.println("- " + t.jenisPakaian.get(i) + " : " + t.jumlahPakaian.get(i));
                }

                System.out.println("Total Item: " + t.total);

                System.out.print("Konfirmasi pengambilan? (y/n): ");
                String konfirmasi = input.nextLine();

                if (konfirmasi.equalsIgnoreCase("y")) {
                    t.status = "SUDAH_DIAMBIL";
                    System.out.println("Laundry berhasil diambil.");
                } else {
                    System.out.println("Pengambilan dibatalkan.");
                }

                return;
            }
        }

        System.out.println("ID tidak ditemukan!");
    }
}