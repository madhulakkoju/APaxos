package org.cse535.configs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GlobalConfigs {

    public static HashMap<String, Integer> serversToPortMap = new HashMap<String, Integer>(){{
        put("s1", 8001);
        put("s2", 8002);
        put("s3", 8003);
        put("s4", 8004);
        put("s5", 8005);
    }};

    public static List<String> allServers = Arrays.asList("s1", "s2", "s3", "s4", "s5");

    public static String viewServerName = "vs";
    public static int viewServerPort = 8000;

    public static int PHASE_TIMEOUT = 10; // ms

    public static int INIT_BALANCE = 100;

}
