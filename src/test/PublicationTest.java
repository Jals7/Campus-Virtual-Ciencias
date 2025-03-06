package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.PublicationController;

public class PublicationTest{
    
    @Test
    public void testPublicationController(){
        //Arrange
        int result = 0;
        PublicationController publicationController = new PublicationController();

        //Act
        result = publicationController.writeToData("src/main/Datas/images/selfie.jpg", "TestUnitario");
        
        //Assert
        assertEquals(1, result);
    }
}
