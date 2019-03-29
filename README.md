<img height = "200" src = "https://github.com/JoeManto/UltimakerPrinterMonitor/blob/master/res/3dpringingimage.png"/>

[![forthebadge](https://forthebadge.com/images/badges/made-with-crayons.svg)](http://forthebadge.com)
[![wercker status](https://app.wercker.com/status/9e0bc54c43f48d220aec684fffb2b110/s/master "wercker status")](https://app.wercker.com/project/bykey/9e0bc54c43f48d220aec684fffb2b110)
[![Open Source Love](https://badges.frapsoft.com/os/mit/mit.svg?v=102)](https://github.com/ellerbrock/open-source-badge/)

### Monitor and track printing statistics on your Ultimaker 3Dprinters

## The Tracking
This program will create directories and csv files when a print is completed.
New directories are created when a print is completed on a new month
and a new csv file is created when a print is completed on a new day of that month.
The data that is included in the csv files consists of print's total time taken, UID, print name ..etc 

## Adding Printers
Adding printers is super simple just make sure your printers are connected to a lan or wifi connection to your local network.

- Modify for the number of printers that you want to track in **main.java**

- Modify the ip's of the printers to your correct printers ip's (see below)


***DEFINE_PRINTERS.java*** 

`Change how many printer you want to track and the IP's that go along with those printers.`

```java
    public static final String PRINTER_1_IP = "XXX.XXX.25.84";
    public static final String PRINTER_2_IP = "XXX.XXX.25.81";
    ...
```
`Change the printer status messages as you see fit.`.
```java
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
```

## Monitoring Preview
java console works but,
terminal output is currently only supported for UNIX stdout 

```
Printer 1 [X X X X X X X X - - ] [h0, m33] [   printing] UM3_08_Base_Tray_1_Box

Printer 2 [X X X X X X X X - - ] [h2, m46] [   printing] UM3_Golf_ball_koozie_1

Printer 3 [- - - - - - - - - - ] [------] [wait_cleanup] UM3_senko-mold2

Printer 4 [X X X - - - - - - - ] [h7, m15] [   printing] UM3_Neladi_Skull_CompleteV3_2

Printer 5 [- - - - - - - - - - ] [------] [wait_cleanup] UM3_Ski_Boot_front

Printer 6 [- - - - - - - - - - ] [------] [wait_cleanup] UM3_Trophy

Printer 7 [- - - - - - - - - - ] [------] [wait_cleanup] UM3_romanset-v3

Printer 8 [- - - - - - - - - - ] [------] [wait_cleanup] UM3_Big_Frame_mm```
