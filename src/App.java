import business.UserController;

import core.Helper;
import entity.User;
import view.DashbordUI;


public class App {
    public static void main(String[] args) {

        Helper.setTheme();
        //LoginUI loginUI = new LoginUI();
        UserController userController = new UserController();
        User user = userController.findByLogin("Huseyin@gmail.com","123123");
        DashbordUI dashbordUI = new DashbordUI(user);

    }
}
