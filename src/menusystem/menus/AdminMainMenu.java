package menusystem.menus;

import com.trainsystem.controllers.LoginController;
import com.trainsystem.models.SystemInfo;
import com.trainsystem.views.SystemView;
import menusystem.MenuBase;
import menusystem.MenuController;

public class AdminMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                SystemView.showKmPrice(SystemInfo.getInstance());
                MenuController.create(new SystemInfoMenu()).execute();
                break;
            case 2:
                MenuController.create(new UserControlMenu()).execute();
                break;
            case 3:
                LoginController.logout();
                break;
            case 4:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Jegyárak módosítása", "Felhasználók kezelése", "Kijelentkezés", "Kilépés"};
    }


}
