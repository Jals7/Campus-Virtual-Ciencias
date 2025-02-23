package Datas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupData {
    private static final String FILE_PATH = "Datas/groups.txt";
    private static List<Group> groups = new ArrayList<>();

    public static List<Group> getGroups() {
        return groups;
    }

    public static void addGroup(Group group) {
        groups.add(group);
        saveGroupsToFile();
    }

    public static void loadGroupsFromFile() {
        groups.clear();
        File file = new File(FILE_PATH);

        // Verificar si el archivo existe antes de intentar cargarlo
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            groups = (List<Group>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            groups = new ArrayList<>();
        }
    }

    public static void saveGroupsToFile() {
        File file = new File(FILE_PATH);

        // Asegurar que la carpeta "Datas" exista
        file.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


