package com.idaroos;
/**
 * Needs buffer to put Item in.
 * run starts Producer
 * stopRunning stops Producer
 */
public interface Producer{
    public void run();
    public void stopRunning();
}

