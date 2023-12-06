package com.idaroos;

import java.util.Queue;

public class MockBuffer extends Buffer{

    public Queue<Item> getBufferQueue() {
        return super.buffer;
    }

}
