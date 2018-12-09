package menusystem.menus;

import com.trainsystem.controllers.LoginController;
import menusystem.MenuBase;
import menusystem.MenuController;

public class AdminMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                MenuController.create(new UserControlMenu()).execute();
                break;
            case 2:
                LoginController.logout();
                break;
            case 3:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Felhasználók kezelése", "Kijelentkezés", "Kilépés"};
    }


}
