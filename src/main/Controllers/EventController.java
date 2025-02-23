package Controllers;

import Datas.EventData;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventController {
    private final String filePath = "src/main/Datas/events.txt";
    private List<EventData> events;

    public EventController() {
        events = new ArrayList<>();
        ensureFileExists();
        loadEventsFromFile();
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        File directory = new File("src/main/Datas"); // Asegurar la carpeta correcta

        if (!directory.exists()) {
            directory.mkdirs(); // Crear la carpeta si no existe
        }

        if (!file.exists()) {
            try {
                file.createNewFile(); // Crear el archivo vacío
                saveEventsToFile();   // Guardar una lista vacía para evitar errores de lectura
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEvent(String name, String description, String organizer) {
        int id = events.size() + 1;
        EventData newEvent = new EventData(id, name, description, organizer);
        events.add(newEvent);
        saveEventsToFile();
    }

    public void updateEvent(int index, String name, String description, String organizer) {
        if (index >= 0 && index < events.size()) {
            EventData event = events.get(index);
            event.setName(name);
            event.setDescription(description);
            event.setOrganizer(organizer);
            saveEventsToFile();
        }
    }

    public List<EventData> getEvents() {
        return events;
    }

    private void saveEventsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (EventData event : events) {
                writer.write(event.getId() + "," + event.getName() + "," + event.getDescription() + "," + event.getOrganizer());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEventsFromFile() {
        events.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String description = parts[2];
                    String organizer = parts[3];
                    events.add(new EventData(id, name, description, organizer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
