package Controllers;

import Datas.ExtensionGroup;
import Datas.Group;
import Datas.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExtensionGroupControllerTest {

    @BeforeEach
    void setUp() {
        // Limpia los grupos antes de cada test
        GroupData.getGroups().clear();
    }

    @Test
    void testAddExtensionGroup() {
        // Agregar un nuevo grupo
        ExtensionGroupController.addExtensionGroup("Grupo de Prueba", "Descripción de prueba");

        // Obtener lista de grupos
        List<Group> groups = ExtensionGroupController.getExtensionGroups();

        // Verificar que se agregó correctamente
        assertEquals(1, groups.size());
        assertTrue(groups.get(0) instanceof ExtensionGroup);
        assertEquals("Grupo de Prueba", groups.get(0).getName());
    }

    @Test
    void testGetExtensionGroups() {
        // Agregar dos grupos de extensión y uno genérico
        ExtensionGroupController.addExtensionGroup("Grupo 1", "Desc 1");
        ExtensionGroupController.addExtensionGroup("Grupo 2", "Desc 2");
        GroupData.addGroup(new Group(3, "Grupo Genérico", "No es de extensión"));

        // Obtener solo los grupos de extensión
        List<Group> extensionGroups = ExtensionGroupController.getExtensionGroups();

        // Verificar que solo hay 2 grupos de extensión
        assertEquals(2, extensionGroups.size());
        assertEquals("Grupo 1", extensionGroups.get(0).getName());
        assertEquals("Grupo 2", extensionGroups.get(1).getName());
    }

    @Test
    void testSaveAndLoadGroupsFromFile() {
        // Agregar y guardar grupos
        ExtensionGroupController.addExtensionGroup("Grupo Persistente", "Descripción persistente");
        ExtensionGroupController.saveGroupsToFile();

        // Limpiar y recargar desde el archivo
        GroupData.getGroups().clear();
        ExtensionGroupController.loadGroupsFromFile();

        // Verificar que los datos se cargaron correctamente
        List<Group> groups = ExtensionGroupController.getExtensionGroups();
        assertEquals(1, groups.size());
        assertEquals("Grupo Persistente", groups.get(0).getName());
    }
}
