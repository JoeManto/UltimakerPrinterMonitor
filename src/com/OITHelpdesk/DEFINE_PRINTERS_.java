package com.OITHelpdesk;

public class DEFINE_PRINTERS_ {
    public static int TIME_TILL_UPDATE = 20;
    public static final String PRINTER_1_IP = "141.218.25.84";
    public static final String PRINTER_2_IP = "141.218.25.81";
    public static final String PRINTER_3_IP = "141.218.25.83";
    public static final String PRINTER_4_IP = "141.218.25.82";
    public static final String PRINTER_5_IP = "141.218.25.23";
    public static final String PRINTER_6_IP = "141.218.25.26";
    public static final String PRINTER_7_IP = "141.218.25.77";
    public static final String PRINTER_8_IP = "141.218.25.78";

    public static final int CLEANUP = 0;
    public static final int POST = 1;
    public static final int PRINTING = 2;

    public static String getStateName(int stateID) {
        switch (stateID) {
            case 0:
                return "wait_cleanup";
            case 1:
                return "post_print";
            case 2:
                return "printing";
        }
        return "Nothing Printing";
    }

    public static int getStateID(String state) {
        switch (state) {
            case "wait_cleanup":
                return 0;
            case "post_print":
                return 1;
            case "printing":
                return 2;
        }
        return -1;
    }
}
