
package com.OITHelpdesk;

public class Main {

    private static UltimakerPrinter[] printers = new UltimakerPrinter[8];

    public static void main(String[] args) throws InterruptedException {
        configAllPrinters();
        for (; ; ) {
            updateAllPrinters();
            showAllPrintStatuses();
            Thread.sleep(DEFINE_PRINTERS_.TIME_TILL_UPDATE * 1000);
        }
    }

    private static void configAllPrinters() {
        printers[0] = new UltimakerPrinter(1, DEFINE_PRINTERS_.PRINTER_1_IP);
        printers[1] = new UltimakerPrinter(2, DEFINE_PRINTERS_.PRINTER_2_IP);
        printers[2] = new UltimakerPrinter(3, DEFINE_PRINTERS_.PRINTER_3_IP);
        printers[3] = new UltimakerPrinter(4, DEFINE_PRINTERS_.PRINTER_4_IP);
        printers[4] = new UltimakerPrinter(5, DEFINE_PRINTERS_.PRINTER_5_IP);
        printers[5] = new UltimakerPrinter(6, DEFINE_PRINTERS_.PRINTER_6_IP);
        printers[6] = new UltimakerPrinter(7, DEFINE_PRINTERS_.PRINTER_7_IP);
        printers[7] = new UltimakerPrinter(8, DEFINE_PRINTERS_.PRINTER_8_IP);
    }

    private static void updateAllPrinters() {
        for (UltimakerPrinter p : printers) {
            p.requestJobUpdate();
        }
    }

    private static void showAllPrintStatuses() {
        for (UltimakerPrinter p : printers) {
            System.out.println(p);
        }
    }
}
