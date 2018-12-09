package com.trainsystem.views;

import com.trainsystem.models.SystemInfo;
import menusystem.MenuHelper;

public class SystemView {

    public static void showKmPrice(SystemInfo systemInfo) {
        System.out.println("Jelenlegi Jegy Ár / km: " + systemInfo.getKmPrice() + " Ft / km");
    }

    public static int changeKmPrice(SystemInfo systemInfo)
    {
        showKmPrice(systemInfo);
        return MenuHelper.readInt("Új km/ár forintban meghatározva: ");
    }
}
