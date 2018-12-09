package menusystem.menus;

import com.trainsystem.controllers.UserController;
import menusystem.MenuBase;
import menusystem.MenuController;

public class UserControlMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                UserController.createUser();
                break;
            case 2:

                break;
            case 3:

                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Felhasználó hozzáadása", "Vissza"};
    }


}
