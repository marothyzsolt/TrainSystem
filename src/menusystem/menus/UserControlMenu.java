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
                UserController.showUsers();
                break;
            case 2:
                UserController.createUser();
                break;
            case 3:
                UserController.deleteUser();
                break;
            case 4:

                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Felhasználók listázása", "Felhasználó hozzáadása", "Felhasználó törlése", "Vissza"};
    }


}
