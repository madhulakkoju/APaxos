package org.cse535;

import org.cse535.node.Node;
import org.cse535.node.NodePool;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static Node node;


    public static void main(String[] args) throws InterruptedException {

        node = new Node( "A", "S1", 8080 );


        NodePool.ports = new int[]{8080, 8081, 8082, 8083, 8084};
        NodePool.initChannels();

        System.out.println("Server started with Node:" + node.serverName + " on port: " + node.port);
        node.server.awaitTermination();
        System.out.println("Server stopped");

    }
}