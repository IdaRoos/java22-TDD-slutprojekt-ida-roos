package com.idaroos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private MockItem item;

    @BeforeEach
    public void setUp() {
        item = new MockItem("testid");
    }


    // Test default item id
    @Test
    @DisplayName("Test if default item id is set correctly")
    public void testIdIsSet() {
        assertEquals("testid", item.getId());
    }

    // Test methods for constructor
    @Test
    @DisplayName("Test constructor when item is empty string")
    public void testConstructorWithEmptyId() {
        MockItem emptyIdItem = new MockItem("");
        assertEquals("", emptyIdItem.getId());
    }

    @Test
    @DisplayName("Test constructor when id is mixed cases")
    public void testConstructorWithMixedCaseId() {
        MockItem testItem = new MockItem("TeStCaSe");
        assertEquals("testcase", testItem.getId());
    }


    // Test methods for setId method
    @Test
    @DisplayName("Test so that the setId updates the id correctly")
    public void testSetId() {
        item.setId("NewID");
        assertEquals("newid", item.getId());
    }

    @Test
    @DisplayName("Test setId when item is empty string")
    public void testSetIdWithEmptyId() {
        item.setId("");
        assertEquals("", item.getId());
    }
    @Test
    @DisplayName("Test setId when id is mixed cases")
    public void testSetIdMixedCaseId() {
        item.setId("TeStCaSe");
        assertEquals("testcase", item.getId());
    }


    @Test
    @DisplayName("Test setId when id is upper cases")
    public void testSetIdMUpperCaseId() {
        item.setId("TESTITEM");
        assertEquals("testitem", item.getId());
    }

    @Test
    @DisplayName("Test setId when id is upper cases")
    public void testIdConstructorUpperCaseId() {
        MockItem testItem = new MockItem("TESTITEM");
        assertEquals("testitem", testItem.getId());
    }


    @Test
    @DisplayName("Test if item id is very long")
    public void testVeryLongItemId() {
        String longItemId = "a".repeat(10000);
        item.setId(longItemId);
        assertEquals(longItemId, item.getId());
    }

    // Test constructor and setId if item is null
    @Test
    @DisplayName("Test constructor if item is null")
    public void testNullItemIdConstructor() {

        assertThrows(NullPointerException.class, () -> new MockItem(null));

    }

    @Test
    @DisplayName("Test setId when item is null")
    public void testNullItemId() {
        assertThrows(NullPointerException.class, () -> item.setId(null));
    }

    // Test methods for toString method

    @Test
    @DisplayName("Test so that the toString method return correct id")
    public void testToStringReturnsCorrectId() {
        assertEquals("testid", item.toString());
    }

    @Test
    @DisplayName("Test so that the toString method return correct id uppercase")
    public void testToStringUpperCaseId() {
        item.setId("TESTID");
        assertEquals("testid", item.toString());
    }

    @Test
    @DisplayName("Test so that the toString method return correct id mixed cases")
    public void testToStringMixedCaseId() {
        item.setId("TesTId");
        assertEquals("testid", item.toString());
    }

    @Test
    @DisplayName("Test so that the toString method return correct id mixed cases")
    public void testToStringEmptyString() {
        item.setId("");
        assertEquals("", item.toString());
    }


    @Test
    @DisplayName("Test if item id is very long")
    public void testToStringLongId() {
        String longItemId = "a".repeat(10000);
        item.setId(longItemId);
        assertEquals(longItemId, item.toString());
    }

}