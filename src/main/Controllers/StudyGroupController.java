package Controllers;

import Datas.Group;
import Datas.GroupData;
import Datas.StudyGroup;
import java.util.List;

public class StudyGroupController {

    public static void loadGroupsFromFile() {
        GroupData.loadGroupsFromFile();
    }

    public static void saveGroupsToFile() {
        GroupData.saveGroupsToFile();
    }

    public static void addStudyGroup(String name, String description) {
        List<Group> groups = GroupData.getGroups();
        GroupData.addGroup(new StudyGroup(groups.size() + 1, name, description));
    }

    public static List<Group> getStudyGroups() {
        return GroupData.getGroups().stream()
                .filter(group -> group instanceof StudyGroup)
                .toList();
    }
}


