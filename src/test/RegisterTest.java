package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.RegisterController;

public class RegisterTest{
    
    @Test
    public void testRegistroUser(){
        //Arrange
        boolean result = false;

        //Act
        //Prueba 1, Prueba Correcta.
        result = RegisterController.Register("1","Jals","Salcedo","07/05/2003","21","M","29595489",
        "correo@prueba.com","777","Computacion", "2021", "Computacion", "", "");

        
        //Assert
        assertEquals(true, result);
    }

    @Test
    public void testRegistroProfesor(){
        //Arrange
        boolean result = false;

        //Act
        //Prueba 1, Prueba Correcta.
        result = RegisterController.Register("2", "Cesar", "Paredes", "15/05/95", "29", "Masculino", "24309195", "C@gmail.com",
        "c","", "2012", "computacion", "Ingenierio de Software", "Profesor 1");
        
        //Assert
        assertEquals(true, result);
    }
        
    @Test
    public void testRegistroUAdmin(){
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


