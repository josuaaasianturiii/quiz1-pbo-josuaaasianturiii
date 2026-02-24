import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input jumlah total data
        System.out.print("Masukkan jumlah total data: ");
        int N = input.nextInt();

        int[] nilai = new int[N];

        // Input deret nilai
        System.out.println("Masukkan deret nilai:");
        for (int i = 0; i < N; i++) {
            nilai[i] = input.nextInt();
        }

        // Input jumlah data per kelompok
        System.out.print("Masukkan jumlah data per kelompok: ");
        int K = input.nextInt();

        // Input kode kelompok
        System.out.print("Masukkan kode kelompok yang ingin dijumlahkan: ");
        int kodeKelompok = input.nextInt();

        int start = (kodeKelompok - 1) * K;
        int end = start + K;

        if (end > N || start < 0) {
            System.out.println("Kode kelompok tidak valid.");
        } else {
            int total = 0;
            for (int i = start; i < end; i++) {
                total += nilai[i];
            }

            System.out.println("Total nilai kelompok " + kodeKelompok + " = " + total);
        }

        input.close();
    }
}