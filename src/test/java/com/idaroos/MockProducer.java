package com.idaroos;

public class MockProducer implements Producer{
    private final MockBuffer mockBuffer;

    public MockProducer(MockBuffer mockBuffer) {
        this.mockBuffer = mockBuffer;
    }

    public boolean addItem(MockItem mockItem) {
        return mockBuffer.add(mockItem);
    }
    @Override
    public void run()  {
    }

    @Override
    public void stopRunning() {
    }

}
