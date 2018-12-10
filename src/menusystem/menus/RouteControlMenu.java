package menusystem.menus;

import com.trainsystem.controllers.RouteController;
import com.trainsystem.controllers.SystemInfoController;
import menusystem.MenuBase;

public class RouteControlMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                RouteController.listRoutes();
                break;
            case 2:
                RouteController.listRouteTimeTable();
                break;
            case 3:
                RouteController.createRoute();
                break;
            case 4:
                RouteController.createTimeTable();
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Útvonalak listázása", "Útvonal menetrend", "Útvonal hozzáadása", "Indulás-érkezés hozzáadása", "Vissza"};
    }


}
