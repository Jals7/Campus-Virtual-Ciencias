package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.EditProfileController;
import Controllers.LoginController;
import Datas.Persona;
import Datas.UserSession;

public class EditProfileTest {
    
    @Test
    public void testUpdateProfile() {
        // Arrange
        boolean result = false;
        Persona currentUser = null; 

        // Act
        if (LoginController.validateLogin("admin@admin.com", "admin123*")) {
            currentUser = UserSession.getInstance().getCurrentUser();
            currentUser.setCorreo("nuevocorreo@gmail.com");
            currentUser.setContrasenia("1234");
            currentUser.setNumTlf("04241637764");
        }

        if (currentUser != null) { // Verifica que no sea null
            result = EditProfileController.updateProfile(currentUser);
        }

        // Assert
        assertTrue("La actualización del perfil falló", result);
    }
}
