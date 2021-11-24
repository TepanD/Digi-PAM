package id.ac.umn.digi_pam;

public class ListPam {
    private String nomorPam;
    private String JumlahPemakaian;
    private String NamaAlamat;
    private String TanggalMasuk;
    public ListPam(){}
    public ListPam(String nomorPam, String jumlahPemakaian, String namaAlamat, String tanggalMasuk) {
        this.nomorPam = nomorPam;
        this.JumlahPemakaian = jumlahPemakaian;
        this.NamaAlamat = namaAlamat;
        this.TanggalMasuk = tanggalMasuk;

    }

    public String getNomorPam() {
        return nomorPam;
    }

    public void setNomorPam(String nomorPam) {
        this.nomorPam = nomorPam;
    }

    public String getJumlahPemakaian() {
        return JumlahPemakaian;
    }

    public void setJumlahPemakaian(String jumlahPemakaian) {
        JumlahPemakaian = jumlahPemakaian;
    }

    public String getNamaAlamat() {
        return NamaAlamat;
    }

    public void setNamaAlamat(String namaAlamat) {
        NamaAlamat = namaAlamat;
    }

    public String getTanggalMasuk() {
        return TanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        TanggalMasuk = tanggalMasuk;
    }
}
