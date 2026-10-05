import java.util.ArrayList;
import java.util.Collections;
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

        // Jumlah di atas rata-rata dihitung di PUTARAN KEDUA. Tidak bisa dihitung
        // sambil membaca nilai (putaran pertama), karena saat nilai pertama dibaca,
        // rata-ratanya belum diketahui. Rata-rata baru ada setelah semua nilai
        // terkumpul, jadi daftar harus dilewati sekali lagi.
        int diAtasRataRata = 0;
        for (int n : daftar) {
            if (n > rataRata) {
                diAtasRataRata++;
            }
        }
        System.out.println("Di atas rata2   : " + diAtasRataRata + " orang");

        // Distribusi grade: index 0=A, 1=B, 2=C, 3=D, 4=E
        int[] jumlahGrade = new int[5];
        for (int n : daftar) {
            if (n >= 90) {
                jumlahGrade[0]++;
            } else if (n >= 80) {
                jumlahGrade[1]++;
            } else if (n >= 70) {
                jumlahGrade[2]++;
            } else if (n >= 60) {
                jumlahGrade[3]++;
            } else {
                jumlahGrade[4]++;
            }
        }
        String[] huruf = {"A", "B", "C", "D", "E"};
        System.out.print("Distribusi      : ");
        for (int i = 0; i < jumlahGrade.length; i++) {
            System.out.print(huruf[i] + "=" + jumlahGrade[i] + " ");
        }
        System.out.println();

        // Daftar terurut dibuat dari SALINAN, supaya daftar asli tidak ikut terurut
        ArrayList<Integer> terurut = new ArrayList<>(daftar);
        Collections.sort(terurut);
        System.out.println("Terurut         : " + terurut);
        System.out.println("Urutan asli     : " + daftar);
    }
}