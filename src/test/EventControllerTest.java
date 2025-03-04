package Controllers;

import Datas.EventData;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventControllerTest {
    private EventController eventController;
    private final String testFilePath = "src/main/Datas/events_test.txt";

    @BeforeEach
    void setUp() {
        eventController = new EventController();
    }

    @Test
    void testAddEvent() {
        eventController.addEvent("Conferencia", "Tecnología avanzada", "TechCorp");
        List<EventData> events = eventController.getEvents();
        assertEquals(1, events.size());
        assertEquals("Conferencia", events.get(0).getName());
    }

    @Test
    void testUpdateEvent() {
        eventController.addEvent("Workshop", "Aprendizaje interactivo", "Educate");
        eventController.updateEvent(0, "Taller", "Formación técnica", "LearnInc");
        List<EventData> events = eventController.getEvents();
        assertEquals("Taller", events.get(0).getName());
        assertEquals("Formación técnica", events.get(0).getDescription());
    }

    @Test
    void testGetEventsEmpty() {
        assertTrue(eventController.getEvents().isEmpty());
    }
}

