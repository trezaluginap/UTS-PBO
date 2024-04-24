import java.util.*;

class ItemLelang {
    protected String nama;
    protected String deskripsi;
    protected double hargaAwal;
    protected double hargaTertinggi;
    protected String penawarTertinggi;

    public ItemLelang(String nama, String deskripsi, double hargaAwal) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.hargaAwal = hargaAwal;
        this.hargaTertinggi = hargaAwal;
        this.penawarTertinggi = "Tidak Ada";
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public double getHargaAwal() {
        return hargaAwal;
    }

    public double getHargaTertinggi() {
        return hargaTertinggi;
    }

    public String getPenawarTertinggi() {
        return penawarTertinggi;
    }

    public void tempatkanPenawaran(double jumlah, String penawar) {
        if (jumlah > hargaTertinggi) {
            hargaTertinggi = jumlah;
            penawarTertinggi = penawar;
            System.out.println("Penawaran diterima! Penawaran tertinggi baru adalah $" + hargaTertinggi + " oleh " + penawarTertinggi);
        } else {
            System.out.println("Penawaran harus lebih tinggi dari penawaran tertinggi saat ini sebesar $" + hargaTertinggi);
        }
    }
}

class Lukisan extends ItemLelang {
    public Lukisan(String nama, String deskripsi, double hargaAwal) {
        super(nama, deskripsi, hargaAwal);
    }
}

class VasAntik extends ItemLelang {
    public VasAntik(String nama, String deskripsi, double hargaAwal) {
        super(nama, deskripsi, hargaAwal);
    }
}

class JamTangan extends ItemLelang {
    public JamTangan(String nama, String deskripsi, double hargaAwal) {
        super(nama, deskripsi, hargaAwal);
    }
}

class Lelang {
    private List<ItemLelang> daftarBarang;
    private Scanner scanner;

    public Lelang() {
        daftarBarang = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void tambahBarang(ItemLelang barang) {
        daftarBarang.add(barang);
    }

    public void tampilkanBarang() {
        System.out.println("Barang Tersedia untuk Lelang:");
        for (ItemLelang barang : daftarBarang) {
            System.out.println("Nama: " + barang.getNama() + ", Deskripsi: " + barang.getDeskripsi() +
                    ", Harga Awal: $" + barang.getHargaAwal() + ", Harga Tertinggi: $" + barang.getHargaTertinggi() +
                    ", Penawar Tertinggi: " + barang.getPenawarTertinggi());
        }
    }

    public void mulaiLelang() {
        while (true) {
            tampilkanBarang();
            System.out.println("\nMasukkan nama barang yang ingin Anda tawar (atau 'exit' untuk keluar):");
            String namaBarang = scanner.nextLine();
            if (namaBarang.equalsIgnoreCase("exit")) {
                tutupLelang();
                break;
            }
            ItemLelang barang = cariBarang(namaBarang);
            if (barang != null) {
                System.out.println("Masukkan penawaran Anda untuk " + barang.getNama() + ":");
                double jumlahPenawaran = scanner.nextDouble();
                scanner.nextLine(); // Konsumsi newline
                System.out.println("Masukkan nama Anda:");
                String namaPenawar = scanner.nextLine();
                barang.tempatkanPenawaran(jumlahPenawaran, namaPenawar);
            } else {
                System.out.println("Barang tidak ditemukan!");
            }
        }
    }

    private ItemLelang cariBarang(String namaBarang) {
        for (ItemLelang barang : daftarBarang) {
            if (barang.getNama().equalsIgnoreCase(namaBarang)) {
                return barang;
            }
        }
        return null;
    }

    private void tutupLelang() {
        System.out.println("\nHasil Lelang:");
        for (ItemLelang barang : daftarBarang) {
            System.out.println("Nama Barang: " + barang.getNama() + ", Penawar Tertinggi: " + barang.getPenawarTertinggi());
        }
        System.out.println("Lelang telah ditutup.");
    }
}

public class Main {
    public static void main(String[] args) {
        Lelang lelang = new Lelang();

        lelang.tambahBarang(new Lukisan("Lukisan", "Lukisan pemandangan yang indah", 100.0));
        lelang.tambahBarang(new VasAntik("Vas Antik", "Vas porselen antik langka", 200.0));
        lelang.tambahBarang(new JamTangan("Jam Tangan Vintage", "Jam tangan Swiss klasik", 150.0));

        lelang.mulaiLelang();
    }
}
