package id.ac.umn.digi_pam;

public class CustomDataPamIdGenerator {
    public static String generateDataPamId(int tahun, int bulan, String noPam) {
        return tahun + "-" + bulan + "-" + noPam;
    }
}
