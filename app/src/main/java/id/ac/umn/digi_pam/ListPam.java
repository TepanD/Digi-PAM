package id.ac.umn.digi_pam;

import java.io.Serializable;

public class ListPam implements Serializable {
    private String nomorPam;
    private String jumlahPemakaian;
    private String alamat;
    private int tanggal;
    private int bulan;
    private int tahun;
    private String emailPenginput;
    private String photoPath;

    public ListPam(){}
    public ListPam(String nomorPam, String jumlahPemakaian, String alamat, int tanggal, int bulan, int tahun, String emailPenginput, String photoPath) {
        this.nomorPam = nomorPam;
        this.jumlahPemakaian = jumlahPemakaian;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.emailPenginput = emailPenginput;
        this.photoPath = photoPath;
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

    public String getEmailPenginput() {
        return emailPenginput;
    }

    public void setEmailPenginput(String emailPenginput) {
        this.emailPenginput = emailPenginput;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
