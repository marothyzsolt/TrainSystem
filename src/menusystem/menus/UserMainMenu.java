package menusystem.menus;

import com.trainsystem.controllers.LoginController;
import menusystem.MenuBase;

public class UserMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:

                break;
            case 2:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Kijelentkezés", "Kilépés"};
    }


}
