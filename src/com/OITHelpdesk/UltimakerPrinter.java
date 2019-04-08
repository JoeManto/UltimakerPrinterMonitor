package com.OITHelpdesk;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UltimakerPrinter {

    private String ip;
    private int printerID;
    private UltimakerPrint_Job job;
    private UltimakeStat stats;

    private int printsCompleted = 0;
    private long usageTime = 0;

    private LocalDate d;

    UltimakerPrinter(int id, String ip) {
        this.printerID = id;
        this.ip = ip;
        this.job = new UltimakerPrint_Job(id);
        this.stats = new UltimakeStat(this.printerID);
        d = updateDate();
    }

    public void requestJobUpdate() {
        job.updateJob(this.ip);
        processUpdate();
    }

    private void processUpdate() {
        if (job.isComplete()) {
            usageTime += job.getStatus().getTotalTime();
            printsCompleted++;
            stats.createRecordForPrintCompletion(job.getStatus(),d);
            this.job = new UltimakerPrint_Job(printerID);
        }

        LocalDate newDate = updateDate();
        if (d.getDayOfMonth() != newDate.getDayOfMonth()) {
            stats.appendDayStats(this,d);
        }
        d = newDate;
    }

    public UltimakerPrint_Job getJob() {
        return job;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public int getPrintsCompleted() {
        return printsCompleted;
    }

    private LocalDate updateDate() {
        Date date = new Date();
        LocalDate newDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return newDate;
    }

    @Override
    public String toString() {
        return String.format("Printer %d %s", printerID, job);
    }

}
