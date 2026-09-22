import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = readNonEmptyLine(sc);
        int n = Integer.parseInt(line.trim());
        if (n <= 0) {
            return;
        }

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] token = readNonEmptyLine(sc).trim().split("\\s+");
            while (token.length < n) {
                String[] more = readNonEmptyLine(sc).trim().split("\\s+");
                String[] merged = new String[token.length + more.length];
                System.arraycopy(token, 0, merged, 0, token.length);
                System.arraycopy(more, 0, merged, token.length, more.length);
                token = merged;
            }
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(token[j]);
            }
        }

        if (n == 1) {
            int nilai = matrix[0][0];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilai);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilai);
            return;
        }

        if (n == 2) {
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    total += matrix[i][j];
                }
            }
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + total);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + total);
            return;
        }

        // Nilai L: seluruh kolom pertama + baris terakhir (tanpa kolom pertama & pojok kanan bawah)
        int nilaiL = 0;
        for (int i = 0; i < n; i++) {
            nilaiL += matrix[i][0];
        }
        for (int j = 1; j < n - 1; j++) {
            nilaiL += matrix[n - 1][j];
        }

        // Nilai Kebalikan L: seluruh kolom terakhir + baris pertama (tanpa pojok kiri atas & kolom terakhir)
        int nilaiKebalikanL = 0;
        for (int i = 0; i < n; i++) {
            nilaiKebalikanL += matrix[i][n - 1];
        }
        for (int j = 1; j < n - 1; j++) {
            nilaiKebalikanL += matrix[0][j];
        }

        // Nilai Tengah
        int nilaiTengah;
        if (n % 2 == 1) {
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            int mid = n / 2;
            nilaiTengah = matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                        + matrix[mid][mid - 1] + matrix[mid][mid];
        }

        int perbedaan = Math.abs(nilaiL - nilaiKebalikanL);

        int dominan;
        if (perbedaan == 0) {
            dominan = nilaiTengah;
        } else {
            dominan = Math.max(nilaiL, nilaiKebalikanL);
        }

        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }

    private static String readNonEmptyLine(Scanner sc) {
        String line;
        do {
            line = sc.nextLine();
        } while (line.trim().isEmpty());
        return line;
    }
}
