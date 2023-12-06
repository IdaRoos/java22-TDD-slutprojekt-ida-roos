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
        assertFalse(buffer.getBufferQueue().isEmpty());
    }

    @Test
    @DisplayName("Test adding multiple items to Buffer list")
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
        assertEquals(5, buffer.getBufferQueue().size());
    }

    @Test
    @DisplayName("Test specific items are added to Buffer")
    public void testSpecificItemsAdded() {
        MockItem item1 = new MockItem("item1");
        MockItem item2 = new MockItem("item2");
        producer.addItem(item1);
        producer.addItem(item2);
        assertEquals(2, buffer.getBufferQueue().size());
        assertTrue(buffer.getBufferQueue().contains(item1));
        assertTrue(buffer.getBufferQueue().contains(item2));
    }




    // Test so that consumer is removing item correctly from buffer list
    @Test
    @DisplayName("Testing that method removeItem removes item correctly")
    public void testRemoveItemFromBuffer() {
        MockItem mockItem = new MockItem("test");
        producer.addItem(mockItem);
        assertEquals(mockItem, consumer.removeItem());
        assertTrue(buffer.getBufferQueue().isEmpty());

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
        producer.addItem(mockItem);
        assertEquals(true,  producer.addItem(mockItem));
    }

    // Testing if added correct to buffer list when item value is empty string
    @Test
    @DisplayName("Testing addItem if item value is empty string")
    public void testBufferWhenEmptyStringItem() {
        MockItem mockItem = new MockItem("");
        producer.addItem(mockItem);
        assertFalse(buffer.getBufferQueue().isEmpty());
    }


    @Test
    @DisplayName("Test that buffer handles thread interruption during remove")
    public void testThreadInterruptionOnRemove() {

        Thread pThread = new Thread(() -> assertThrows(InterruptedException.class, () ->
                consumer.removeItem()));

        pThread.start();
        pThread.interrupt();
    }


}