package id.ac.umn.digi_pam;

import java.util.Calendar;

public class CustomDateConvertor {
    public static String dayOfWeekConvertor(int hari)
    {
        if (hari == Calendar.SUNDAY) {
            return "Minggu";
        }
        else if (hari == Calendar.MONDAY) {
            return "Senin";
        }
        else if (hari == Calendar.TUESDAY) {
            return "Selasa";
        }
        else if (hari == Calendar.WEDNESDAY) {
            return "Rabu";
        }
        else if (hari == Calendar.THURSDAY) {
            return "Kamis";
        }
        else if (hari == Calendar.FRIDAY) {
            return "Jumat";
        }
        else if (hari == Calendar.SATURDAY) {
            return "Sabtu";
        }
        else return null;
    }

    public static String monthConvertor(int month)
    {
        if (month == Calendar.JANUARY) {
            return "Januari";
        }
        else if (month == Calendar.FEBRUARY) {
            return "Februari";
        }
        else if (month == Calendar.MARCH) {
            return "Maret";
        }
        else if (month == Calendar.APRIL) {
            return "April";
        }
        else if (month == Calendar.MAY) {
            return "Mei";
        }
        else if (month == Calendar.JUNE) {
            return "Juni";
        }
        else if (month == Calendar.JULY) {
            return "Juli";
        }
        else if (month == Calendar.AUGUST) {
            return "Agustus";
        }
        else if (month == Calendar.SEPTEMBER) {
            return "September";
        }
        else if (month == Calendar.OCTOBER) {
            return "Oktober";
        }
        else if (month == Calendar.NOVEMBER) {
            return "November";
        }
        else if (month == Calendar.DECEMBER) {
            return "Desember";
        }
        else {
            return null;
        }
    }
}
