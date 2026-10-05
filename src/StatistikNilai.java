import java.util.ArrayList;
import java.util.Scanner;

public class StatistikNilai {

    static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> daftar = new ArrayList<>();

        System.out.println("===== STATISTIK NILAI KELAS =====");
        System.out.println("Ketik -1 kalau sudah selesai.");

        int nilai;
        do {
            System.out.print("Nilai ke-" + (daftar.size() + 1) + " : ");
            nilai = input.nextInt();

            if (nilai == SELESAI) {
                continue;
            }
            if (nilai < 0 || nilai > 100) {
                System.out.println("  Ditolak, harus 0-100");
                continue;
            }
            daftar.add(nilai);
        } while (nilai != SELESAI);

        if (daftar.isEmpty()) {
            System.out.println("Tidak ada nilai yang dimasukkan.");
            return;
        }

        // Jumlah dan rata-rata
        int jumlah = 0;
        for (int n : daftar) {
            jumlah += n;
        }
        double rataRata = (double) jumlah / daftar.size();

        // Tertinggi dan terendah dengan loop sendiri (tanpa Collections.max/min).
        // Keduanya dimulai dari elemen pertama (daftar.get(0)), bukan 0 atau 100.
        // Kalau terendah dimulai dari 0, nilainya tidak akan pernah berubah karena
        // tidak ada nilai yang lebih kecil dari 0, hasilnya salah. Elemen pertama
        // pasti nilai yang benar-benar ada di daftar. Karena itu loop mulai dari
        // index 1 (index 0 sudah dipakai sebagai nilai awal).
        int tertinggi = daftar.get(0);
        int terendah = daftar.get(0);
        for (int i = 1; i < daftar.size(); i++) {
            int n = daftar.get(i);
            if (n > tertinggi) {
                tertinggi = n;
            }
            if (n < terendah) {
                terendah = n;
            }
        }

        System.out.println();
        System.out.println("Nilai tersimpan : " + daftar);
        System.out.println("Jumlah          : " + daftar.size());
        System.out.println("Rata-rata       : " + String.format("%.2f", rataRata));
        System.out.println("Tertinggi       : " + tertinggi);
        System.out.println("Terendah        : " + terendah);
    }
}