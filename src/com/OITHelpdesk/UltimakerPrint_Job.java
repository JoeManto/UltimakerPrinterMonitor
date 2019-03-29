package com.OITHelpdesk;

public class UltimakerPrint_Job {

    private int owner;
    private int state;
    private UltimakerRequest status;

    public UltimakerPrint_Job(int owner) {
        this.owner = owner;
        this.status = new UltimakerRequest();
    }

    public void updateJob(String ip) {
        state = status.getPrinterState();
        status.generateUpdateRequest(ip);

    }

    public boolean isComplete() {
        int newState = status.getPrinterState();
        if (state == DEFINE_PRINTERS_.PRINTING && (newState == DEFINE_PRINTERS_.POST || newState == DEFINE_PRINTERS_.CLEANUP)) {
            return true;
        }
        return false;
    }

    public int getOwner() {
        return owner;
    }

    public UltimakerRequest getStatus() {
        return status;
    }

    public String getProgString() {
        StringBuilder out = new StringBuilder();
        int prog = 0;
        if (state == DEFINE_PRINTERS_.PRINTING) {
            prog = (int) (status.getProgress() * 100);
            while (prog >= 10.0) {
                prog /= 10;
            }
        }
        out.append("[");
        for (int i = 0; i < prog; i++) {
            out.append("X ");
        }
        for (int i = prog; i < 10; i++) {
            out.append("- ");
        }
        out.append("]");
        return out.toString();
    }

    public String getTimeLeftString() {
        if (state == DEFINE_PRINTERS_.PRINTING) {
            long hours = 0;
            long mins = 0;
            long timeleft = (status.getTotalTime() - status.getElapsedTime());
            hours = timeleft / 3600;
            mins = timeleft / 60 - (hours * 60);
            return String.format("%dh, %dm", hours, mins);
        }
        return "------";
    }

    @Override
    public String toString() {
        return String.format("%s [%8s] [%16s] %s", getProgString(), getTimeLeftString(), DEFINE_PRINTERS_.getStateName(state), status.getName());
    }

}
