package com.idaroos;

public class MockConsumer implements Consumer {

    private final MockBuffer mockBuffer;

    public MockConsumer(MockBuffer mockBuffer) {
        this.mockBuffer = mockBuffer;
    }

    public Item removeItem()  {
        return mockBuffer.remove();
    }

    @Override
    public void run() {
    }

    @Override
    public void stopRunning() {
    }

}

