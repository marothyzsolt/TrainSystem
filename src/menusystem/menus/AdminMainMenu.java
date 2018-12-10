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
                MenuController.create(new RouteControlMenu()).execute();
                break;
            case 2:
                SystemView.showKmPrice(SystemInfo.getInstance());
                MenuController.create(new SystemInfoMenu()).execute();
                break;
            case 3:
                MenuController.create(new UserControlMenu()).execute();
                break;
            case 4:
                LoginController.logout();
                break;
            case 5:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Útvonalak kezelése", "Jegyárak módosítása", "Felhasználók kezelése", "Kijelentkezés", "Kilépés"};
    }


}
