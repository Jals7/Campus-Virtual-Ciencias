package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Controllers.ExtensionGroupController;

public class ExtensionGroupControllerTest {

    @Before
    public void setUp() {
        // Asegurar que la lista de grupos esté vacía antes de cada prueba
        ExtensionGroupController.saveGroupsToFile(); 
    }

    @Test
    public void testAddExtensionGroup() {
        // Arrange
        int initialSize = ExtensionGroupController.getExtensionGroups().size();

        // Act
        ExtensionGroupController.addExtensionGroup("Grupo Test", "Descripción de prueba");
        int newSize = ExtensionGroupController.getExtensionGroups().size();

        // Assert
        assertEquals(initialSize + 1, newSize);
    }
}
