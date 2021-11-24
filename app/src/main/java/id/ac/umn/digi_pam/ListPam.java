package id.ac.umn.digi_pam;

public class ListPam {
    private String nomorPam;
    private String jumlahPemakaian;
    private String namaAlamat;
    private String tanggalMasuk;
    public ListPam(){}
    public ListPam(String nomorPam, String jumlahPemakaian, String namaAlamat, String tanggalMasuk) {
        this.nomorPam = nomorPam;
        this.jumlahPemakaian = jumlahPemakaian;
        this.namaAlamat = namaAlamat;
        this.tanggalMasuk = tanggalMasuk;

    }

    public String getNomorPam() {
        return nomorPam;
    }

    public void setNomorPam(String nomorPam) {
        this.nomorPam = nomorPam;
    }

    public String getJumlahPemakaian() {
        return jumlahPemakaian;
    }

    public void setJumlahPemakaian(String jumlahPemakaian) {
        jumlahPemakaian = jumlahPemakaian;
    }

    public String getNamaAlamat() {
        return namaAlamat;
    }

    public void setNamaAlamat(String namaAlamat) {
        namaAlamat = namaAlamat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        tanggalMasuk = tanggalMasuk;
    }
}
