package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Controllers.EventController;

public class EventControllerTest {
    
    @Test
    public void testAddEvent() {
        // Arrange
        EventController eventController = new EventController();
        int initialSize = eventController.getEvents().size();

        // Act
        eventController.addEvent("Test Event", "Test Description", "Test Organizer");
        int newSize = eventController.getEvents().size();
        
        // Assert
        assertEquals(initialSize + 1, newSize);
    }
}
