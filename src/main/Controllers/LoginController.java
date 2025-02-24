package Controllers;

import java.io.*;

public class LoginController{
    public static boolean validateLogin(String email, String password){
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 3){
                    String dataEmail = data[1].trim();
                    String dataPassword = data[2].trim();

                    if(dataEmail.equals(email) && dataPassword.equals(password)){
                        return true;
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
