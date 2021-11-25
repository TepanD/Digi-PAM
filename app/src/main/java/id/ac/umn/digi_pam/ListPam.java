package id.ac.umn.digi_pam;

import java.io.Serializable;

public class ListPam implements Serializable {
    private String nomorPam;
    private String jumlahPemakaian;
    private String alamat;
    private int tanggal;
    private int bulan;
    private int tahun;
    public ListPam(){}
    public ListPam(String nomorPam, String jumlahPemakaian, String alamat, int tanggal, int bulan, int tahun) {
        this.nomorPam = nomorPam;
        this.jumlahPemakaian = jumlahPemakaian;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
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
        this.jumlahPemakaian = jumlahPemakaian;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getTanggal() {
        return tanggal;
    }

    public void setTanggal(int tanggal) {
        this.tanggal = tanggal;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
}
