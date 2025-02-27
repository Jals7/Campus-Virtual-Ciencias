package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.RegisterController;

public class RegisterTest{
    
    @Test
    public void testRegistro(){
        //Arrange
        boolean result = false;

        //Act
        //Prueba 1, Prueba Correcta.
        result = RegisterController.Register("1","Jals","Salcedo","07/05/2003","21","M","29595489",
        "correo@prueba.com","777","Computacion", "2021", "Computacion", "", "");

        
        //Assert
        assertEquals(true, result);
    }
}
