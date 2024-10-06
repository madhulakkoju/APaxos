package org.cse535;

import org.cse535.configs.GlobalConfigs;
import org.cse535.node.Node;

public class Main {

    public static Node node;

    public static void main(String[] args) throws InterruptedException {
        String serverName = args[0];
        //String serverName = "s2";
        node = new Node( serverName, GlobalConfigs.serversToPortMap.get(serverName));
        node.server.awaitTermination();
    }

}
