package menusystem.menus;

import com.trainsystem.controllers.SystemInfoController;
import menusystem.MenuBase;
import menusystem.MenuController;

public class SystemInfoMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                SystemInfoController.changeKmPrice();
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Jegyár módosítása", "Vissza"};
    }


}
