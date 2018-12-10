package menusystem.menus;

import menusystem.MenuBase;
import menusystem.MenuController;
import menusystem.MenuHelper;

public class Test extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                String name = MenuHelper.getInstance().readLine("Varom az inputot: ");
                break;
            case 2:
                MenuController.create(new Test()).execute();
                break;
            case 3:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Menu1", "Menu2", "Exit"};
    }


}
