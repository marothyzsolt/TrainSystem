package menusystem.menus;

import com.trainsystem.controllers.LoginController;
import menusystem.MenuBase;
import menusystem.MenuController;

public class MainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                LoginController.loginUser();
                break;
            case 2:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Bejelentkezés", "Kilépés"};
    }


}
