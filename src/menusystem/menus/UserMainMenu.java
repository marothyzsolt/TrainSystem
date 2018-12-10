package menusystem.menus;

import com.trainsystem.controllers.CostumerController;
import com.trainsystem.controllers.LoginController;
import menusystem.MenuBase;
import menusystem.MenuController;

public class UserMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                MenuController.create(new CostumerCardEditorMenu()).execute();
                break;
            case 2:
                MenuController.create(new CostumerPurchaseMenu()).execute();
                break;
            case 3:
                CostumerController.search();
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
        return new String[]{
                "Bankkártya kezelés",
                "Bérletvásárlás",
                "Keresés",
                "Kijelentkezés",
                "Kilépés",
        };
    }


}
