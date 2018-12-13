package menusystem.menus;

import com.trainsystem.controllers.RouteController;
import menusystem.MenuBase;

public class WorkerMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                RouteController.createRoute();
                break;
            case 2:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Indulás-érkezés hozzáadása", "Kijelentkezés", "Kilépés"};
    }


}
