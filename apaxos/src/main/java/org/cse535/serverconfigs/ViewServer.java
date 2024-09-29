package org.cse535.serverconfigs;

import org.cse535.loggers.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewServer {

    LogUtils logger = new LogUtils("ViewServer", 8000);

    public static void main(String[] args) {
        System.out.println("View Server started");

        List<NodeConfig> nodes = new ArrayList<>();

        nodes.add(new NodeConfig("A", "S1", 8001));
        nodes.add(new NodeConfig("B", "S2", 8002));
        nodes.add(new NodeConfig("C", "S3", 8003));
        nodes.add(new NodeConfig("D", "S4", 8004));
        nodes.add(new NodeConfig("E", "S5", 8005));

        for (NodeConfig node : nodes) {
            System.out.println(
                    " Node: " + node.clientName +
                    " Server: " + node.serverName +
                    " Port: " + node.port
            );

        }






    }
}
