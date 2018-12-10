package com.trainsystem.controllers;

import com.trainsystem.models.SystemInfo;
import com.trainsystem.views.SystemView;

public class SystemInfoController {


    public static void changeKmPrice()
    {
        int newPrice = SystemView.changeKmPrice(SystemInfo.getInstance());

        SystemInfo.getInstance().setKmPrice(newPrice);
        SystemInfo.getInstance().store();
    }



}
