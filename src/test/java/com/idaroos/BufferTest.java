package com.idaroos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {
    private MockBuffer buffer;
    private MockProducer producer;

    private MockConsumer consumer;

    @BeforeEach
    public void setUp() {
        buffer = new MockBuffer();
        producer = new MockProducer(buffer);
        consumer = new MockConsumer(buffer);
    }

    // Test methods for producer

    @Test
    @DisplayName("Test that addItem method in producer works for correct item")
    public void testAddItemProducer() {
        MockItem mockItem = new MockItem("test");
        assertEquals(true,  producer.addItem(mockItem));
    }

    @Test
    @DisplayName("Test that item is added correctly to Buffer list")
    public void testAddItemBufferList() {
        MockItem mockItem = new MockItem("test");
        producer.addItem(mockItem);
        assertFalse(buffer.getBufferQueue().isEmpty(), "Buffer list should not be empty after adding an item");
    }

    @Test
    @DisplayName("Test when adding multiple items to Buffer list")
    public void testAddMultipleItems() {

        MockItem mockItem1 = new MockItem("item1");
        MockItem mockItem2 = new MockItem("item2");
        MockItem mockItem3 = new MockItem("item3");
        MockItem mockItem4 = new MockItem("item4");
        MockItem mockItem5 = new MockItem("item5");
        producer.addItem(mockItem1);
        producer.addItem(mockItem2);
        producer.addItem(mockItem3);
        producer.addItem(mockItem4);
        producer.addItem(mockItem5);
        assertEquals(5, buffer.getBufferQueue().size(), "Buffer should contain the correct number of items");
    }



    // Test so that consumer is removing item correctly from buffer list
    @Test
    @DisplayName("Testing that method removeItem removes item correctly")
    public void testRemoveItemFromBuffer() {
        MockItem mockItem = new MockItem("test");
        producer.addItem(mockItem);
        assertFalse(buffer.getBufferQueue().isEmpty(), "Buffer list should not be empty before removing an item");

        assertEquals(mockItem, consumer.removeItem());
        assertTrue(buffer.getBufferQueue().isEmpty(), "Buffer list should be empty after removing the item");

    }


    // Testing so that producer is not adding item when value is null
    @Test
    @DisplayName("Testing addItem if item value is null")
    public void testAddItemWhenNullItem() {
        assertThrows(NullPointerException.class, () -> new MockItem(null));

    }

    // Testing if producer produces item if value is empty string
    @Test
    @DisplayName("Testing addItem if item value is empty string")
    public void testAddItemWhenEmptyStringItem() {
        MockItem mockItem = new MockItem("");
        assertEquals(true,  producer.addItem(mockItem));
        assertFalse(buffer.getBufferQueue().isEmpty(), "Buffer list should not be empty after adding an item");
    }

    @Test
    void testRemoveBlocksWhenEmptyAndResumesWhenNotified() throws InterruptedException {
        Thread consumerThread = new Thread(() -> {
            buffer.remove(); // This should block initially
        });

        consumerThread.start();

        // Give some time for the consumer thread to potentially block on empty buffer
        Thread.sleep(500);

        Thread.State consumerState = consumerThread.getState();
        assertTrue(consumerState == Thread.State.WAITING || consumerState == Thread.State.TIMED_WAITING,
                "Consumer thread should be in WAITING or TIMED_WAITING state");

        // Now, simulate a producer adding an item, which should notify the consumer
        buffer.add(new Item("test"));

        // Wait a bit to give the consumer thread time to be notified and resume
        Thread.sleep(500);

        consumerState = consumerThread.getState();
        assertFalse(consumerState == Thread.State.WAITING || consumerState == Thread.State.TIMED_WAITING,
                "Consumer thread should no longer be in WAITING or TIMED_WAITING state");

        consumerThread.join(); // Wait for the consumer thread to finish
    }


    @Test
    @DisplayName("Testing that method removeItem removes item from empty buffer list")
    public void testRemoveItemFromEmptyBuffer() {

        Thread pThread = new Thread(() -> assertThrows(InterruptedException.class, () ->
                consumer.removeItem()));

        pThread.start();
        pThread.interrupt();
    }


}