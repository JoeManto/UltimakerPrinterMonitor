package com.OITHelpdesk;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class UltimakerRequest {

    private HashMap<String, String> results;

    UltimakerRequest() {
        this.results = new HashMap<>();
    }

    public HashMap<String, String> generateUpdateRequest(String printerIP) {
        Scanner s;
        try {
            URL url = new URL("http://" + printerIP + "/api/v1/print_job");
            s = new Scanner(url.openStream());
        } catch (IOException e) {
            //System.out.printf("Could'nt fetch from printer ip:%s",printerIP);
            HashMap<String,String> temp = new HashMap<>();
            temp.put("state:","Nothing Printing");
            temp.put("name:"," ");
            return temp;
        }
        return processRequest(s);

    }

    private HashMap<String, String> processRequest(Scanner s) {
        this.results = new HashMap<>();

        int c = 0;
        String results = null;
        String header = null;

        while (s.hasNext()) {
            String output = s.next();
            if (output.equals("\"source_application\":")) {
                output = s.next();
                output = s.next();
                output = s.next();
            }
            output = output.replace("{", "");
            output = output.replace("}", "");
            output = output.replace("\"", "");
            output = output.replace(",", "");

            if (c % 2 == 0) {
                header = output;
            } else {
                results = output;
            }

            if (results != null && header != null && c % 2 != 0) {
                if (results.equals(""))
                    results = "NONE";
                this.results.put(header, results);
            }
            c++;
        }
        return this.results;
    }

    public int getPrinterState() {
        String state = results.get("state:");
        if (state == null)
            return -1;

        return DEFINE_PRINTERS_.getStateID(state);
    }

    public long getElapsedTime() {
        return Long.parseLong(this.results.get("time_elapsed:"));
    }

    public long getTotalTime() {
        return Long.parseLong(this.results.get("time_total:"));
    }

    public double getProgress() {
        return Double.parseDouble(this.results.get("progress:"));
    }

    public String getName() {
        return this.results.get("name:");
    }

    public HashMap<String, String> getResults() {
        return this.results;
    }

    public void setResults(HashMap<String, String> results) {
        this.results = results;
    }
}
