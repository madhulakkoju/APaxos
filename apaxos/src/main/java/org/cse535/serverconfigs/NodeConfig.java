package org.cse535.serverconfigs;

public class NodeConfig{
    public String serverName;
    public int port;
    public String clientName;

    public NodeConfig( String clientName, String serverName, int port){
        this.serverName = serverName;
        this.port = port;
        this.clientName = clientName;
    }
}
