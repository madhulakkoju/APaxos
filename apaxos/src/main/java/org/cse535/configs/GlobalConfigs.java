package org.cse535.configs;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GlobalConfigs {

    public static HashMap<String, Integer> serversToPortMap = new HashMap<String, Integer>(){{
        put("S1", 8001);
        put("S2", 8002);
        put("S3", 8003);
        put("S4", 8004);
        put("S5", 8005);
    }};



    public static List<String> allServers = Arrays.asList("S1", "S2", "S3", "S4", "S5");

    public static String viewServerName = "vs";
    public static int viewServerPort = 8000;

    public static String databaseServerName = "ds";
    public static int databaseServerPort = 7000;


    public static int PHASE_TIMEOUT = 100; // ms

    public static int INIT_BALANCE = 100;




    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd -- HH-mm-ss-nnnnnnnnn");

}
