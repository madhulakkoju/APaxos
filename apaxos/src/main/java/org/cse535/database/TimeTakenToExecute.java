package org.cse535.database;

public class TimeTakenToExecute {
    public long startTime;
    public long endTime;

    public TimeTakenToExecute() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getTimeTaken() {
        return this.endTime - this.startTime;
    }
}
