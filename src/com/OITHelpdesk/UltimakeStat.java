package com.OITHelpdesk;

import java.io.*;
import java.time.*;

public class UltimakeStat {

    private int printerID;

    public UltimakeStat(int printerID) {
        this.printerID = printerID;
    }

    public void createRecordForPrintCompletion(UltimakerRequest values, LocalDate d) {
        try {
            String dirName = String.format("%s_%s", d.getMonth(), d.getYear());
            File dir = new File(dirName);
            dir.mkdir();

            String fileName = String.format("%s/%s.csv", dirName, d.getDayOfMonth());
            File f = new File(fileName);

            if (f.exists() && !f.isDirectory()) {
                File file = new File(fileName);
                FileWriter fr = new FileWriter(file, true);
                fr.write(getRecordLine(values));
                fr.close();
            } else {
                PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                writer.printf(getRecordLine(values));
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("ERROR: CREATING NEW RECORD");
            System.exit(0);
        }
    }

    public void appendDayStats(UltimakerPrinter p, LocalDate d) {
        try {
            String fileName = String.format("%s_%s/%s.csv", d.getMonth(), d.getYear(), d.getDayOfMonth());
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                File file = new File(fileName);
                FileWriter fr = new FileWriter(file, true);
                fr.write(String.format("PRINTSCOMPLETED:%d,TIMESPENTRUNNING:%d\n", p.getPrintsCompleted(), p.getUsageTime()));
                fr.close();
            } else {

            }
        } catch (IOException e) {
            System.out.println("ERROR: Appending Day stats");
            System.exit(0);
        }
    }

    private String getRecordLine(UltimakerRequest values) {
        return String.format("%d,%d,%s,%s\n", printerID, values.getTotalTime(), values.getName(), values.getResults().get("uuid:"));
    }
}
