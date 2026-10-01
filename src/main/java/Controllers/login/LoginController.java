package Controllers.login;

public class LoginController {
    public boolean checkUserNameAndPassword(String username, String password) {
            if (username.equals("Menoly") && password.equals("12345")) {
                return true;
            }
            return false;
        }

}

