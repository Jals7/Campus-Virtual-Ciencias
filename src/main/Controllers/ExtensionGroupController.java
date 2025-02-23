package Controllers;

import Datas.ExtensionGroup;
import Datas.GroupData;
import Datas.Group;
import java.util.List;

public class ExtensionGroupController {
    
    public static void loadGroupsFromFile() {
        GroupData.loadGroupsFromFile();
    }

    public static void saveGroupsToFile() {
        GroupData.saveGroupsToFile();
    }

    public static void addExtensionGroup(String name, String description) {
        List<Group> groups = GroupData.getGroups();
        GroupData.addGroup(new ExtensionGroup(groups.size() + 1, name, description));
    }

    public static List<Group> getExtensionGroups() {
        return GroupData.getGroups().stream()
                .filter(group -> group instanceof ExtensionGroup)
                .toList();
    }
}
