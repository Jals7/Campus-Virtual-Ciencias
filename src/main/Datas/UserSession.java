package Datas;

public class UserSession {
    private static UserSession instance;
    private Persona currentUser;

    private UserSession() {}

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public Persona getCurrentUser() {
        return currentUser;
    }

    public void setPersona(Persona currentUser) {
        this.currentUser = currentUser;
    }

    public void clearSession() {
        currentUser = null;
    }
}
