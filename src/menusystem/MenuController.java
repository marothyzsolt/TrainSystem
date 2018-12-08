package menusystem;

import java.util.ArrayList;

public class MenuController {
    private static ArrayList<MenuBase> menus = new ArrayList<>();

    public static MenuBase create(MenuBase menu, String... items)
    {
        for(int i = 0; i < items.length; i++)
        {
           menu.addOption(i, items[i]);
        }
        menus.add(menu);

        return menu;
    }

    public static MenuBase create(MenuBase menu)
    {
        String[] items = menu.getOptions();
        return create(menu, items);
    }
}
